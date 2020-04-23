package models

import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._


class majorModel(db:Database)(implicit ec:ExecutionContext){
    def addMajor(description:String):Future[Boolean] = {
        db.run(Major += MajorRow(-1,description,false)).map{addCount =>
            if(addCount > 0) true else false
        }
    }

    def showAllMajor():Future[Seq[String]] = {
        db.run(Major.map(_.description).result)
    }

    def getMajorId(description:String):Future[Int] = {
        db.run(Major.filter(major => major.description === description).result).map(major => major.head.id)
    }

    
}


class minorModel(db:Database)(implicit ec:ExecutionContext){
    def addMinor(description:String):Future[Boolean] = {
        db.run(Minor += MinorRow(-1,description,false)).map{addCount =>
            if(addCount > 0) true else false
        }
    }

    def getMinorId(description:String):Future[Int] = {
        db.run(Minor.filter(minor => minor.description === description).result).map(minor => minor.head.id)
    }

    
}