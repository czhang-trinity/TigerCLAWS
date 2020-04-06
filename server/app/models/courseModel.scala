package models

import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._

class courseModel(db:Database)(implicit ec:ExecutionContext){
    def addCourse(description:String, title: String, creditHour: Int, academicLevel: Int, courseNumber: String, departmentId: Int):Future[Boolean] = {
        val course = CourseRow(-1,description,title,creditHour,academicLevel,courseNumber,departmentId,false)
        db.run(Course += course).map(addCount => if(addCount > 0) true else false)
    }
}
