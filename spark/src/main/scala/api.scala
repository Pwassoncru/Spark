object API {
  val config = new Properties()
  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)
  val userProducer = Producer[User](config)
  val postProducer = Producer[Post](config)

  def create_user(id : String, nickname : String) = {
    val user = User(
      id,
      URI.create("https://test"),
      nickname,
      verified = false,
      deleted = false)
    userProducer.send(user)
  }

  def create_post(id : String, userId : String, text : String) = {
    val post = Post(
      id,
      userId,
      URI.create("https://test"),
      "This is a post",
      deleted = false)
    postProducer.send(post)
  }

  def create_message(userId : String, targetId : String, text : String) = {
    ...
  }
}
