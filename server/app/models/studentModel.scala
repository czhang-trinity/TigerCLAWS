package models

import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future
import org.mindrot.jbcrypt.BCrypt
import slick.jdbc.PostgresProfile.api._


class studentModel(db:Database)(implicit ec:ExecutionContext){

    // validate user if true return user id else return None
    def validateStudent(username:String, password: String):Future[Option[Int]] = {
        val matches = db.run(Student.filter(studentRow => studentRow.username === username).result)
        matches.map{studentRow => studentRow.headOption.flatMap{
            studentRow => if(BCrypt.checkpw(password, studentRow.password)) Some(studentRow.id) else None
        }}
    }

    def createStudent(first_name:String, middle_name:String, last_name:String, password: String):Future[Option[Int]] = {
        var username = last_name(0) + first_name
        val num_same_username = db.run(Student.filter(student => student.username.substring(0,username.length()) === username).result)
        num_same_username.flatMap{ same_usernames =>
            if(same_usernames.length != 0){
                username = username + same_usernames.length.toString()
            }
            val student = StudentRow(-1,username,BCrypt.hashpw(password,BCrypt.gensalt()),first_name,middle_name,last_name,username+"@trinity.edu",false)
            db.run(Student += student).flatMap{ addCount =>
                if(addCount > 0) db.run(Student.filter(student => student.username === username).result).map(_.headOption.map(_.id))
                else Future.successful(None)
            }
        }
    }

    def showMajor(student_id:Int):Future[Seq[String]] = {
        db.run(
            (for{
                major <- Major
                studentMajor <- Studentmajor if studentMajor.majorId === major.id
            }yield{
                major.description
            }).result
        )
    }

    def showMinor(student_id:Int):Future[Seq[String]] = {
        db.run(
            (for{
                minor <- Minor
                studentMinor <- Studentminor if studentMinor.minorId === minor.id
            }yield{
                minor.description
            }).result
        )
    }

    def addMajor(student_id:Int, major_id:Int):Future[Boolean] = {
        val studentMajor = StudentmajorRow(-1,student_id,major_id,false)
        db.run(Studentmajor += studentMajor).map{addCount =>
            if(addCount > 0) true else false
        }
    } 

    def addMinor(student_id:Int, minor_id:Int):Future[Boolean] = {
        val studentMinor = StudentminorRow(-1,student_id,minor_id,false)
        db.run(Studentminor += studentMinor).map{addCount => 
            if(addCount > 0) true else false
        }
    }

    

}