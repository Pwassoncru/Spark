object PostUtils {
  case class Post(
    id : String,
    date : Instant,
    authorId : String,
    image : String,
    text : String,
    deleted : Boolean)

  def PostToCsv(post : Post) =
    post.id + "," +
    post.date.toString + ","
    post.authorId + "," +
    post.image + "," +
    post.test + "," +
    post.deleted

  def StringToPost(str : String) = {
    val list = str.split(",")
    Post(list(0), Instant.parse(list(1)), list(2), list(3), list(4), list(5))
  }
}
