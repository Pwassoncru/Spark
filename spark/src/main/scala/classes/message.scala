import java.time

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
}
