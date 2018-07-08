import java.time.Instant

object MessageUtils {
  case class Message(
    id : String,
    date : Instant,
    userId : String,
    targetId : String,
    text : String)

  def MessageToCsv(message : Message) = 
    message.id + "," +
    message.date.toString + "," +
    message.userId + "," +
    message.targetId + "," +
    message.text

  def StringToMessage(str : String) = {
    val list = str.split(",")
    Message(
      list(0),
      Instant.parse(list(1)),
      list(2),
      list(3),
      list(4))
  }
}
