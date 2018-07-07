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
}
