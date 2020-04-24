package utility

case class DaysAndTime(days: String, startTime: String, endTime: String) {
  /**
   * Length of class meeting in minutes.
   */
  lazy val duration: Option[Int] = (startTime, endTime) match {
    case (DaysAndTime.regex(sh, sm, sap), DaysAndTime.regex(eh, em, eap)) =>
      val startMin = (sh.toInt % 12 + (if (sap == "P") 12 else 0)) * 60 + sm.toInt
      val endMin = (eh.toInt % 12 + (if (eap == "P") 12 else 0)) * 60 + em.toInt
      Some(days.length * (endMin - startMin))
    case _ => None
  }
}

object DaysAndTime {
  val regex = """(\d\d):(\d\d)([AP])M""".r
}

case class BuildingRoom(building: String, room: String)

object BuildingRoom {
  def parse(line: String): Option[BuildingRoom] = {
    if(line.length < 50) None else {
      val s = line.substring(50, 63 min line.length).trim
      if(s.isEmpty) None else {
        val p = s.split(" ")
        if(p.length < 2) None else Some(BuildingRoom(p(0), p(1)))
      }
    }
  }
}

//TODO: add rooms
case class Course(
  dept:        String,
  num:         String,
  section:     String,
  title:       String,
  dayTimes:    List[DaysAndTime],
  credits:     Double,
  rooms:       List[BuildingRoom],
  roomCap:     Option[Int],
  sectionCap:  Int,
  enrolled:    Int,
  facultyData: List[FacultyInfo]) {

  lazy val duration = dayTimes.map(_.duration).foldLeft(Option(0))((acc, d) => for (accv <- acc; dv <- d) yield accv + dv).map(_ / facultyData.length)

  def codeCredits: Int = num(1) - '0'
  def acdemicLevel: Int = num(0) - '0'
  def level = num(0) - '0'

}

object Course {
  def fromStrings(iter: Iterator[String]): Option[Course] = {
    if (iter.hasNext) {
      var firstLine = iter.next
      //println("first", firstLine)
      while (iter.hasNext && (firstLine.length < 12 || courseRegex.findFirstIn(firstLine.substring(0, 11).trim).isEmpty)) {
        firstLine = iter.next
      }
      if (!iter.hasNext) {
        None
      } else {
        //      println(firstLine)
        val otherLines = {
          val firstTry = iter.takeWhile(_.trim.nonEmpty).toList
          if (firstTry.isEmpty) iter.takeWhile(_.trim.nonEmpty).toList else firstTry
        }
        //if (firstLine.startsWith("PHED-2101-13")) println(firstLine, otherLines)
        //      require(otherLines.length % 2 == 1, s"Course entry needs even number of lines in $firstLine.")
        val courseRegex(dept, num, section) = firstLine.substring(0, 11).trim
        val title = firstLine.substring(22, 50).trim
        val dayTimes = (firstLine :: otherLines).grouped(2).flatMap {
          case List(start, end) =>
            //          println(end)
            val days = if (start.length < 68) "" else start.substring(64, 68).trim
            if (days.isEmpty) None else {
              val startTime = start.substring(72, 80).trim
              val endTime = end.substring(72, 80).trim
              Some(DaysAndTime(days, startTime, endTime))
            }
          case _ => None
        }.toList
        val credits = firstLine.substring(98, 104).trim.toDouble
        val rooms = (firstLine :: otherLines).flatMap(BuildingRoom.parse)
        val roomCapString = firstLine.substring(91, 96).trim
        val roomCap = if (roomCapString.isEmpty()) None else Some(roomCapString.toInt)
        val sectionCapString = firstLine.substring(111, 116).trim
        val sectionCap = if (sectionCapString.isEmpty) 0 else sectionCapString.toInt
        val enrolledString = firstLine.substring(117, 121).trim
        val enrolled = if (enrolledString.isEmpty) 0 else enrolledString.toInt
        val facultyData = otherLines.flatMap { line =>
          if (line.length < 29) None
          else {
            val id = line.substring(22, 29).trim
            val name = if (line.length < 32) "" else line.substring(30, 50 min line.length).trim
            if (id.isEmpty) None else Some(FacultyInfo(id, name))
          }
        }
        Some(Course(dept, num, section, title, dayTimes, credits, rooms, roomCap, sectionCap, enrolled, facultyData))
      }
    } else None
  }

  val courseRegex = """(\w\w\w\w?)-(\d\d\d\d)-(\d\d?)""".r
}