object API {
  val config = new Properties()
  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BootstrapServers)
  val userProducer = Producer[User](config)
  val postProducer = Producer[Post](config)

  def create_user(nickname : String) = {
    val user = ...
    userProducer.send(user)
  }

  def create_post(userId : String, text : String) = {
    val post = ...
    postProducer.send(post)
  }

  def create_message(userId : String, targetId : String, text : String) = {
    ...
  }
}
