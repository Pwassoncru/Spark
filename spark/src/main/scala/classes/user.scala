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
}
