
import java.time.Instant
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, ProducerConfig}
import java.util.Properties

import PostUtils._
import MessageUtils._
import UserUtils._


object API {

  println("producer are starting")

  val config = new Properties()
  config.put("bootstrap.servers", "localhost:9092")
  config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  val producer = new KafkaProducer[String,String](config)

  def create_user(id : String, nickname : String) = {
    val user = User(
      id,
      Instant.now(),
      "https://test",
      nickname,
      false,
      false)

    val userCsv = UserToCsv(user)

    producer.send(new ProducerRecord[String,String]("users", user.id, userCsv)).get
  }

  def create_post(id : String, userId : String, text : String) = {
    val post = Post(
      id,
      Instant.now(),
      userId,
      "https://test",
      text,
      deleted = false)

    val postCsv = PostToCsv(post)

    producer.send(new ProducerRecord[String,String]("posts", null, post.id, postCsv)).get()
  }

  def create_message(id : String, userId : String, targetId : String, text : String) = {
    val message = Message(
      id,
      Instant.now(),
      userId,
      targetId,
      text)

    val messageCsv = MessageToCsv(message)

    producer.send(new ProducerRecord[String,String]("messages", null, message.id, messageCsv))
  }
}
