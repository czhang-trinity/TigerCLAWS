package utility

case class Semester(semester: String) {
  def doubleValue: Double = {
    semester.substring(1, 3).toInt + (if (semester(0) == 'F') 0.5 else 0.0)
  }

  def <(other: Semester): Boolean = doubleValue < other.doubleValue
  def >(other: Semester): Boolean = doubleValue > other.doubleValue
  def <=(other: Semester): Boolean = doubleValue <= other.doubleValue
  def >=(other: Semester): Boolean = doubleValue >= other.doubleValue
}
