
import org.apache.kafka.clients.producer


object API {
  val config = new Properties()
  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092")
  //config.put("key.serializer", )
  //config.put("value.serializer", )
  val producer = new KafkaProducer(config)

  def create_user(id : String, nickname : String) = {
    val user = User(
      id,
      "https://test",
      nickname,
      false,
      false)

    val userCsv = UserToCsv(user)

    producer.send(new ProducerRecord("users", null, user.id, userCsv)).get()
  }

  def create_post(id : String, userId : String, text : String) = {
    val post = Post(
      id,
      userId,
      "https://test",
      text,
      deleted = false)

    val postCsv = PostToCsv(post)

    producer.send(new ProducerRecord("posts", null, post.id, postCsv)).get()
  }

  def create_message(userId : String, targetId : String, text : String) = {
  }
}
