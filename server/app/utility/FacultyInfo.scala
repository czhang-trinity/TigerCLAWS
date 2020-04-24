package utility

case class FacultyInfo(id: String, name: String) {
    val lastName = name.split(" ").last
}