import java.time

object UserUtils {
  case class User(
    id : String,
    date : Instant,
    image : String,
    nickname : String,
    verified : Boolean,
    deleted : Boolean)

  def UserToCsv(user : String) = 
    user.id + "," +
    user.date.toString + ","
    user.image + "," +
    user.nickname + "," +
    user.verified + "," +
    user.deleted

  def StringToUser(str : String) = {
    val list = str.split(",")
    User(list(0), Instant.parse(list(1)), list(2), list(3), list(4), list(5))
  }
}
