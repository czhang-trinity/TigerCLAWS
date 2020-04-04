package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Course.schema, Coursemajor.schema, Courseminor.schema, Coursesection.schema, Deparment.schema, Grade.schema, Major.schema, Minor.schema, Professor.schema, Professorsection.schema, Registration.schema, Section.schema, Semester.schema, Status.schema, Student.schema, Studentmajor.schema, Studentminor.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Course
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(varchar), Length(1000,true)
   *  @param title Database column title SqlType(varchar), Length(100,true)
   *  @param creditHour Database column credit_hour SqlType(int4)
   *  @param academicLevel Database column academic_level SqlType(int4)
   *  @param courseNumber Database column course_number SqlType(bpchar), Length(4,false)
   *  @param departmentId Database column department_id SqlType(int4), Default(None)
   *  @param deleted Database column deleted SqlType(bool) */
  case class CourseRow(id: Int, description: String, title: String, creditHour: Int, academicLevel: Int, courseNumber: String, departmentId: Option[Int] = None, deleted: Boolean)
  /** GetResult implicit for fetching CourseRow objects using plain SQL queries */
  implicit def GetResultCourseRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]], e3: GR[Boolean]): GR[CourseRow] = GR{
    prs => import prs._
    CourseRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[Int], <<[String], <<?[Int], <<[Boolean]))
  }
  /** Table description of table course. Objects of this class serve as prototypes for rows in queries. */
  class Course(_tableTag: Tag) extends profile.api.Table[CourseRow](_tableTag, "course") {
    def * = (id, description, title, creditHour, academicLevel, courseNumber, departmentId, deleted) <> (CourseRow.tupled, CourseRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(description), Rep.Some(title), Rep.Some(creditHour), Rep.Some(academicLevel), Rep.Some(courseNumber), departmentId, Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> CourseRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description SqlType(varchar), Length(1000,true) */
    val description: Rep[String] = column[String]("description", O.Length(1000,varying=true))
    /** Database column title SqlType(varchar), Length(100,true) */
    val title: Rep[String] = column[String]("title", O.Length(100,varying=true))
    /** Database column credit_hour SqlType(int4) */
    val creditHour: Rep[Int] = column[Int]("credit_hour")
    /** Database column academic_level SqlType(int4) */
    val academicLevel: Rep[Int] = column[Int]("academic_level")
    /** Database column course_number SqlType(bpchar), Length(4,false) */
    val courseNumber: Rep[String] = column[String]("course_number", O.Length(4,varying=false))
    /** Database column department_id SqlType(int4), Default(None) */
    val departmentId: Rep[Option[Int]] = column[Option[Int]]("department_id", O.Default(None))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Deparment (database name course_department_id_fkey) */
    lazy val deparmentFk = foreignKey("course_department_id_fkey", departmentId, Deparment)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Course */
  lazy val Course = new TableQuery(tag => new Course(tag))

  /** Entity class storing rows of table Coursemajor
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param courseId Database column course_id SqlType(int4)
   *  @param majorId Database column major_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class CoursemajorRow(id: Int, courseId: Int, majorId: Int, deleted: Boolean)
  /** GetResult implicit for fetching CoursemajorRow objects using plain SQL queries */
  implicit def GetResultCoursemajorRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[CoursemajorRow] = GR{
    prs => import prs._
    CoursemajorRow.tupled((<<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table coursemajor. Objects of this class serve as prototypes for rows in queries. */
  class Coursemajor(_tableTag: Tag) extends profile.api.Table[CoursemajorRow](_tableTag, "coursemajor") {
    def * = (id, courseId, majorId, deleted) <> (CoursemajorRow.tupled, CoursemajorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(courseId), Rep.Some(majorId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> CoursemajorRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column course_id SqlType(int4) */
    val courseId: Rep[Int] = column[Int]("course_id")
    /** Database column major_id SqlType(int4) */
    val majorId: Rep[Int] = column[Int]("major_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Course (database name coursemajor_course_id_fkey) */
    lazy val courseFk = foreignKey("coursemajor_course_id_fkey", courseId, Course)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Major (database name coursemajor_major_id_fkey) */
    lazy val majorFk = foreignKey("coursemajor_major_id_fkey", majorId, Major)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Coursemajor */
  lazy val Coursemajor = new TableQuery(tag => new Coursemajor(tag))

  /** Entity class storing rows of table Courseminor
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param courseId Database column course_id SqlType(int4)
   *  @param minorId Database column minor_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class CourseminorRow(id: Int, courseId: Int, minorId: Int, deleted: Boolean)
  /** GetResult implicit for fetching CourseminorRow objects using plain SQL queries */
  implicit def GetResultCourseminorRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[CourseminorRow] = GR{
    prs => import prs._
    CourseminorRow.tupled((<<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table courseminor. Objects of this class serve as prototypes for rows in queries. */
  class Courseminor(_tableTag: Tag) extends profile.api.Table[CourseminorRow](_tableTag, "courseminor") {
    def * = (id, courseId, minorId, deleted) <> (CourseminorRow.tupled, CourseminorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(courseId), Rep.Some(minorId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> CourseminorRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column course_id SqlType(int4) */
    val courseId: Rep[Int] = column[Int]("course_id")
    /** Database column minor_id SqlType(int4) */
    val minorId: Rep[Int] = column[Int]("minor_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Course (database name courseminor_course_id_fkey) */
    lazy val courseFk = foreignKey("courseminor_course_id_fkey", courseId, Course)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Minor (database name courseminor_minor_id_fkey) */
    lazy val minorFk = foreignKey("courseminor_minor_id_fkey", minorId, Minor)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Courseminor */
  lazy val Courseminor = new TableQuery(tag => new Courseminor(tag))

  /** Entity class storing rows of table Coursesection
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param courseId Database column course_id SqlType(int4)
   *  @param sectionId Database column section_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class CoursesectionRow(id: Int, courseId: Int, sectionId: Int, deleted: Boolean)
  /** GetResult implicit for fetching CoursesectionRow objects using plain SQL queries */
  implicit def GetResultCoursesectionRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[CoursesectionRow] = GR{
    prs => import prs._
    CoursesectionRow.tupled((<<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table coursesection. Objects of this class serve as prototypes for rows in queries. */
  class Coursesection(_tableTag: Tag) extends profile.api.Table[CoursesectionRow](_tableTag, "coursesection") {
    def * = (id, courseId, sectionId, deleted) <> (CoursesectionRow.tupled, CoursesectionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(courseId), Rep.Some(sectionId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> CoursesectionRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column course_id SqlType(int4) */
    val courseId: Rep[Int] = column[Int]("course_id")
    /** Database column section_id SqlType(int4) */
    val sectionId: Rep[Int] = column[Int]("section_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Course (database name coursesection_course_id_fkey) */
    lazy val courseFk = foreignKey("coursesection_course_id_fkey", courseId, Course)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Section (database name coursesection_section_id_fkey) */
    lazy val sectionFk = foreignKey("coursesection_section_id_fkey", sectionId, Section)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Coursesection */
  lazy val Coursesection = new TableQuery(tag => new Coursesection(tag))

  /** Entity class storing rows of table Deparment
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(varchar), Length(1000,true)
   *  @param acronym Database column acronym SqlType(varchar), Length(10,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class DeparmentRow(id: Int, description: String, acronym: String, deleted: Boolean)
  /** GetResult implicit for fetching DeparmentRow objects using plain SQL queries */
  implicit def GetResultDeparmentRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[DeparmentRow] = GR{
    prs => import prs._
    DeparmentRow.tupled((<<[Int], <<[String], <<[String], <<[Boolean]))
  }
  /** Table description of table deparment. Objects of this class serve as prototypes for rows in queries. */
  class Deparment(_tableTag: Tag) extends profile.api.Table[DeparmentRow](_tableTag, "deparment") {
    def * = (id, description, acronym, deleted) <> (DeparmentRow.tupled, DeparmentRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(description), Rep.Some(acronym), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> DeparmentRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description SqlType(varchar), Length(1000,true) */
    val description: Rep[String] = column[String]("description", O.Length(1000,varying=true))
    /** Database column acronym SqlType(varchar), Length(10,true) */
    val acronym: Rep[String] = column[String]("acronym", O.Length(10,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Deparment */
  lazy val Deparment = new TableQuery(tag => new Deparment(tag))

  /** Entity class storing rows of table Grade
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param letterGrade Database column letter_grade SqlType(varchar), Length(10,true)
   *  @param notes Database column notes SqlType(varchar), Length(100,true), Default(None)
   *  @param deleted Database column deleted SqlType(bool) */
  case class GradeRow(id: Int, letterGrade: String, notes: Option[String] = None, deleted: Boolean)
  /** GetResult implicit for fetching GradeRow objects using plain SQL queries */
  implicit def GetResultGradeRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Boolean]): GR[GradeRow] = GR{
    prs => import prs._
    GradeRow.tupled((<<[Int], <<[String], <<?[String], <<[Boolean]))
  }
  /** Table description of table grade. Objects of this class serve as prototypes for rows in queries. */
  class Grade(_tableTag: Tag) extends profile.api.Table[GradeRow](_tableTag, "grade") {
    def * = (id, letterGrade, notes, deleted) <> (GradeRow.tupled, GradeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(letterGrade), notes, Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> GradeRow.tupled((_1.get, _2.get, _3, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column letter_grade SqlType(varchar), Length(10,true) */
    val letterGrade: Rep[String] = column[String]("letter_grade", O.Length(10,varying=true))
    /** Database column notes SqlType(varchar), Length(100,true), Default(None) */
    val notes: Rep[Option[String]] = column[Option[String]]("notes", O.Length(100,varying=true), O.Default(None))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Grade */
  lazy val Grade = new TableQuery(tag => new Grade(tag))

  /** Entity class storing rows of table Major
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(varchar), Length(500,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class MajorRow(id: Int, description: String, deleted: Boolean)
  /** GetResult implicit for fetching MajorRow objects using plain SQL queries */
  implicit def GetResultMajorRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[MajorRow] = GR{
    prs => import prs._
    MajorRow.tupled((<<[Int], <<[String], <<[Boolean]))
  }
  /** Table description of table major. Objects of this class serve as prototypes for rows in queries. */
  class Major(_tableTag: Tag) extends profile.api.Table[MajorRow](_tableTag, "major") {
    def * = (id, description, deleted) <> (MajorRow.tupled, MajorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(description), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> MajorRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description SqlType(varchar), Length(500,true) */
    val description: Rep[String] = column[String]("description", O.Length(500,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Major */
  lazy val Major = new TableQuery(tag => new Major(tag))

  /** Entity class storing rows of table Minor
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(varchar), Length(500,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class MinorRow(id: Int, description: String, deleted: Boolean)
  /** GetResult implicit for fetching MinorRow objects using plain SQL queries */
  implicit def GetResultMinorRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[MinorRow] = GR{
    prs => import prs._
    MinorRow.tupled((<<[Int], <<[String], <<[Boolean]))
  }
  /** Table description of table minor. Objects of this class serve as prototypes for rows in queries. */
  class Minor(_tableTag: Tag) extends profile.api.Table[MinorRow](_tableTag, "minor") {
    def * = (id, description, deleted) <> (MinorRow.tupled, MinorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(description), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> MinorRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description SqlType(varchar), Length(500,true) */
    val description: Rep[String] = column[String]("description", O.Length(500,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Minor */
  lazy val Minor = new TableQuery(tag => new Minor(tag))

  /** Entity class storing rows of table Professor
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param firstName Database column first_name SqlType(varchar), Length(20,true)
   *  @param middleName Database column middle_name SqlType(varchar), Length(20,true)
   *  @param lastName Database column last_name SqlType(varchar), Length(20,true)
   *  @param email Database column email SqlType(varchar), Length(20,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class ProfessorRow(id: Int, firstName: String, middleName: String, lastName: String, email: String, deleted: Boolean)
  /** GetResult implicit for fetching ProfessorRow objects using plain SQL queries */
  implicit def GetResultProfessorRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[ProfessorRow] = GR{
    prs => import prs._
    ProfessorRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[Boolean]))
  }
  /** Table description of table professor. Objects of this class serve as prototypes for rows in queries. */
  class Professor(_tableTag: Tag) extends profile.api.Table[ProfessorRow](_tableTag, "professor") {
    def * = (id, firstName, middleName, lastName, email, deleted) <> (ProfessorRow.tupled, ProfessorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(firstName), Rep.Some(middleName), Rep.Some(lastName), Rep.Some(email), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> ProfessorRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first_name SqlType(varchar), Length(20,true) */
    val firstName: Rep[String] = column[String]("first_name", O.Length(20,varying=true))
    /** Database column middle_name SqlType(varchar), Length(20,true) */
    val middleName: Rep[String] = column[String]("middle_name", O.Length(20,varying=true))
    /** Database column last_name SqlType(varchar), Length(20,true) */
    val lastName: Rep[String] = column[String]("last_name", O.Length(20,varying=true))
    /** Database column email SqlType(varchar), Length(20,true) */
    val email: Rep[String] = column[String]("email", O.Length(20,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Professor */
  lazy val Professor = new TableQuery(tag => new Professor(tag))

  /** Entity class storing rows of table Professorsection
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param professorId Database column professor_id SqlType(int4)
   *  @param sectionId Database column section_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class ProfessorsectionRow(id: Int, professorId: Int, sectionId: Int, deleted: Boolean)
  /** GetResult implicit for fetching ProfessorsectionRow objects using plain SQL queries */
  implicit def GetResultProfessorsectionRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[ProfessorsectionRow] = GR{
    prs => import prs._
    ProfessorsectionRow.tupled((<<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table professorsection. Objects of this class serve as prototypes for rows in queries. */
  class Professorsection(_tableTag: Tag) extends profile.api.Table[ProfessorsectionRow](_tableTag, "professorsection") {
    def * = (id, professorId, sectionId, deleted) <> (ProfessorsectionRow.tupled, ProfessorsectionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(professorId), Rep.Some(sectionId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> ProfessorsectionRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column professor_id SqlType(int4) */
    val professorId: Rep[Int] = column[Int]("professor_id")
    /** Database column section_id SqlType(int4) */
    val sectionId: Rep[Int] = column[Int]("section_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Professor (database name professorsection_professor_id_fkey) */
    lazy val professorFk = foreignKey("professorsection_professor_id_fkey", professorId, Professor)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Section (database name professorsection_section_id_fkey) */
    lazy val sectionFk = foreignKey("professorsection_section_id_fkey", sectionId, Section)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Professorsection */
  lazy val Professorsection = new TableQuery(tag => new Professorsection(tag))

  /** Entity class storing rows of table Registration
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param studentId Database column student_id SqlType(int4)
   *  @param coursesectionId Database column coursesection_id SqlType(int4)
   *  @param semesterId Database column semester_id SqlType(int4)
   *  @param gradeId Database column grade_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class RegistrationRow(id: Int, studentId: Int, coursesectionId: Int, semesterId: Int, gradeId: Int, deleted: Boolean)
  /** GetResult implicit for fetching RegistrationRow objects using plain SQL queries */
  implicit def GetResultRegistrationRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[RegistrationRow] = GR{
    prs => import prs._
    RegistrationRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table registration. Objects of this class serve as prototypes for rows in queries. */
  class Registration(_tableTag: Tag) extends profile.api.Table[RegistrationRow](_tableTag, "registration") {
    def * = (id, studentId, coursesectionId, semesterId, gradeId, deleted) <> (RegistrationRow.tupled, RegistrationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(studentId), Rep.Some(coursesectionId), Rep.Some(semesterId), Rep.Some(gradeId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> RegistrationRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column student_id SqlType(int4) */
    val studentId: Rep[Int] = column[Int]("student_id")
    /** Database column coursesection_id SqlType(int4) */
    val coursesectionId: Rep[Int] = column[Int]("coursesection_id")
    /** Database column semester_id SqlType(int4) */
    val semesterId: Rep[Int] = column[Int]("semester_id")
    /** Database column grade_id SqlType(int4) */
    val gradeId: Rep[Int] = column[Int]("grade_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Coursesection (database name registration_coursesection_id_fkey) */
    lazy val coursesectionFk = foreignKey("registration_coursesection_id_fkey", coursesectionId, Coursesection)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Grade (database name registration_grade_id_fkey) */
    lazy val gradeFk = foreignKey("registration_grade_id_fkey", gradeId, Grade)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Semester (database name registration_semester_id_fkey) */
    lazy val semesterFk = foreignKey("registration_semester_id_fkey", semesterId, Semester)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Student (database name registration_student_id_fkey) */
    lazy val studentFk = foreignKey("registration_student_id_fkey", studentId, Student)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Registration */
  lazy val Registration = new TableQuery(tag => new Registration(tag))

  /** Entity class storing rows of table Section
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param capacity Database column capacity SqlType(int4)
   *  @param startTime Database column start_time SqlType(time without time zone)
   *  @param endTime Database column end_time SqlType(time without time zone)
   *  @param dayOfWeek Database column day_of_week SqlType(varchar), Length(20,true)
   *  @param location Database column location SqlType(varchar), Length(20,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class SectionRow(id: Int, capacity: Int, startTime: java.sql.Time, endTime: java.sql.Time, dayOfWeek: String, location: String, deleted: Boolean)
  /** GetResult implicit for fetching SectionRow objects using plain SQL queries */
  implicit def GetResultSectionRow(implicit e0: GR[Int], e1: GR[java.sql.Time], e2: GR[String], e3: GR[Boolean]): GR[SectionRow] = GR{
    prs => import prs._
    SectionRow.tupled((<<[Int], <<[Int], <<[java.sql.Time], <<[java.sql.Time], <<[String], <<[String], <<[Boolean]))
  }
  /** Table description of table section. Objects of this class serve as prototypes for rows in queries. */
  class Section(_tableTag: Tag) extends profile.api.Table[SectionRow](_tableTag, "section") {
    def * = (id, capacity, startTime, endTime, dayOfWeek, location, deleted) <> (SectionRow.tupled, SectionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(capacity), Rep.Some(startTime), Rep.Some(endTime), Rep.Some(dayOfWeek), Rep.Some(location), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> SectionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column capacity SqlType(int4) */
    val capacity: Rep[Int] = column[Int]("capacity")
    /** Database column start_time SqlType(time without time zone) */
    val startTime: Rep[java.sql.Time] = column[java.sql.Time]("start_time")
    /** Database column end_time SqlType(time without time zone) */
    val endTime: Rep[java.sql.Time] = column[java.sql.Time]("end_time")
    /** Database column day_of_week SqlType(varchar), Length(20,true) */
    val dayOfWeek: Rep[String] = column[String]("day_of_week", O.Length(20,varying=true))
    /** Database column location SqlType(varchar), Length(20,true) */
    val location: Rep[String] = column[String]("location", O.Length(20,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Section */
  lazy val Section = new TableQuery(tag => new Section(tag))

  /** Entity class storing rows of table Semester
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param description Database column description SqlType(varchar), Length(10,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class SemesterRow(id: Int, description: String, deleted: Boolean)
  /** GetResult implicit for fetching SemesterRow objects using plain SQL queries */
  implicit def GetResultSemesterRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[SemesterRow] = GR{
    prs => import prs._
    SemesterRow.tupled((<<[Int], <<[String], <<[Boolean]))
  }
  /** Table description of table semester. Objects of this class serve as prototypes for rows in queries. */
  class Semester(_tableTag: Tag) extends profile.api.Table[SemesterRow](_tableTag, "semester") {
    def * = (id, description, deleted) <> (SemesterRow.tupled, SemesterRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(description), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> SemesterRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description SqlType(varchar), Length(10,true) */
    val description: Rep[String] = column[String]("description", O.Length(10,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Semester */
  lazy val Semester = new TableQuery(tag => new Semester(tag))

  /** Entity class storing rows of table Status
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param code Database column code SqlType(varchar), Length(20,true)
   *  @param description Database column description SqlType(varchar), Length(50,true)
   *  @param sectionId Database column section_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class StatusRow(id: Int, code: String, description: String, sectionId: Int, deleted: Boolean)
  /** GetResult implicit for fetching StatusRow objects using plain SQL queries */
  implicit def GetResultStatusRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[StatusRow] = GR{
    prs => import prs._
    StatusRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[Boolean]))
  }
  /** Table description of table status. Objects of this class serve as prototypes for rows in queries. */
  class Status(_tableTag: Tag) extends profile.api.Table[StatusRow](_tableTag, "status") {
    def * = (id, code, description, sectionId, deleted) <> (StatusRow.tupled, StatusRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(code), Rep.Some(description), Rep.Some(sectionId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> StatusRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column code SqlType(varchar), Length(20,true) */
    val code: Rep[String] = column[String]("code", O.Length(20,varying=true))
    /** Database column description SqlType(varchar), Length(50,true) */
    val description: Rep[String] = column[String]("description", O.Length(50,varying=true))
    /** Database column section_id SqlType(int4) */
    val sectionId: Rep[Int] = column[Int]("section_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Section (database name status_section_id_fkey) */
    lazy val sectionFk = foreignKey("status_section_id_fkey", sectionId, Section)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Status */
  lazy val Status = new TableQuery(tag => new Status(tag))

  /** Entity class storing rows of table Student
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true)
   *  @param firstname Database column firstname SqlType(varchar), Length(20,true)
   *  @param middlename Database column middlename SqlType(varchar), Length(20,true)
   *  @param lastname Database column lastname SqlType(varchar), Length(20,true)
   *  @param email Database column email SqlType(varchar), Length(20,true)
   *  @param deleted Database column deleted SqlType(bool) */
  case class StudentRow(id: Int, username: String, password: String, firstname: String, middlename: String, lastname: String, email: String, deleted: Boolean)
  /** GetResult implicit for fetching StudentRow objects using plain SQL queries */
  implicit def GetResultStudentRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean]): GR[StudentRow] = GR{
    prs => import prs._
    StudentRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Boolean]))
  }
  /** Table description of table student. Objects of this class serve as prototypes for rows in queries. */
  class Student(_tableTag: Tag) extends profile.api.Table[StudentRow](_tableTag, "student") {
    def * = (id, username, password, firstname, middlename, lastname, email, deleted) <> (StudentRow.tupled, StudentRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password), Rep.Some(firstname), Rep.Some(middlename), Rep.Some(lastname), Rep.Some(email), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> StudentRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
    /** Database column firstname SqlType(varchar), Length(20,true) */
    val firstname: Rep[String] = column[String]("firstname", O.Length(20,varying=true))
    /** Database column middlename SqlType(varchar), Length(20,true) */
    val middlename: Rep[String] = column[String]("middlename", O.Length(20,varying=true))
    /** Database column lastname SqlType(varchar), Length(20,true) */
    val lastname: Rep[String] = column[String]("lastname", O.Length(20,varying=true))
    /** Database column email SqlType(varchar), Length(20,true) */
    val email: Rep[String] = column[String]("email", O.Length(20,varying=true))
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")
  }
  /** Collection-like TableQuery object for table Student */
  lazy val Student = new TableQuery(tag => new Student(tag))

  /** Entity class storing rows of table Studentmajor
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param studentId Database column student_id SqlType(int4)
   *  @param majorId Database column major_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class StudentmajorRow(id: Int, studentId: Int, majorId: Int, deleted: Boolean)
  /** GetResult implicit for fetching StudentmajorRow objects using plain SQL queries */
  implicit def GetResultStudentmajorRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[StudentmajorRow] = GR{
    prs => import prs._
    StudentmajorRow.tupled((<<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table studentmajor. Objects of this class serve as prototypes for rows in queries. */
  class Studentmajor(_tableTag: Tag) extends profile.api.Table[StudentmajorRow](_tableTag, "studentmajor") {
    def * = (id, studentId, majorId, deleted) <> (StudentmajorRow.tupled, StudentmajorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(studentId), Rep.Some(majorId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> StudentmajorRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column student_id SqlType(int4) */
    val studentId: Rep[Int] = column[Int]("student_id")
    /** Database column major_id SqlType(int4) */
    val majorId: Rep[Int] = column[Int]("major_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Major (database name studentmajor_major_id_fkey) */
    lazy val majorFk = foreignKey("studentmajor_major_id_fkey", majorId, Major)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Student (database name studentmajor_student_id_fkey) */
    lazy val studentFk = foreignKey("studentmajor_student_id_fkey", studentId, Student)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Studentmajor */
  lazy val Studentmajor = new TableQuery(tag => new Studentmajor(tag))

  /** Entity class storing rows of table Studentminor
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param studentId Database column student_id SqlType(int4)
   *  @param minorId Database column minor_id SqlType(int4)
   *  @param deleted Database column deleted SqlType(bool) */
  case class StudentminorRow(id: Int, studentId: Int, minorId: Int, deleted: Boolean)
  /** GetResult implicit for fetching StudentminorRow objects using plain SQL queries */
  implicit def GetResultStudentminorRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[StudentminorRow] = GR{
    prs => import prs._
    StudentminorRow.tupled((<<[Int], <<[Int], <<[Int], <<[Boolean]))
  }
  /** Table description of table studentminor. Objects of this class serve as prototypes for rows in queries. */
  class Studentminor(_tableTag: Tag) extends profile.api.Table[StudentminorRow](_tableTag, "studentminor") {
    def * = (id, studentId, minorId, deleted) <> (StudentminorRow.tupled, StudentminorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(studentId), Rep.Some(minorId), Rep.Some(deleted))).shaped.<>({r=>import r._; _1.map(_=> StudentminorRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column student_id SqlType(int4) */
    val studentId: Rep[Int] = column[Int]("student_id")
    /** Database column minor_id SqlType(int4) */
    val minorId: Rep[Int] = column[Int]("minor_id")
    /** Database column deleted SqlType(bool) */
    val deleted: Rep[Boolean] = column[Boolean]("deleted")

    /** Foreign key referencing Minor (database name studentminor_minor_id_fkey) */
    lazy val minorFk = foreignKey("studentminor_minor_id_fkey", minorId, Minor)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Student (database name studentminor_student_id_fkey) */
    lazy val studentFk = foreignKey("studentminor_student_id_fkey", studentId, Student)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Studentminor */
  lazy val Studentminor = new TableQuery(tag => new Studentminor(tag))
}
