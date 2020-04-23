package models


import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._


class semesterModel(db:Database)(implicit ec:ExecutionContext){
    def addSemester(description:String):Future[Boolean] = {
        db.run(Semester += SemesterRow(-1,description,false)).map{addCount =>
            if(addCount > 0) true else false
        }
    }

    def getSemesterId(description:String):Future[Int] = {
        db.run(Semester.filter(_.description === description).result).map(semesters => semesters.head.id)
    }
}