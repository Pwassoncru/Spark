object Streams {

  println("Streams are starting")

  val streamBuilder = new streamBuilder()
  val usersStream = streamBuilder.stream[String, String]("users")
  val postsStream = streamBuilder.stream[String, String]("posts")
  val messagesStream = streamBuilder.stream[String, String]("messages")

  // Removes the duplicates users by keeping the new ones (removing old version of user profile)
  def UsersByKey = usersStream
    .groupByKey
    .mapValues(CsvToUsers)
    .reduce((first, second) => if (first.date.isAfter(second.date)) first else second)

  def PostByAuthors = postsStream
    .groupByKey
    .mapValues(CsvToPosts)
    .reduce((first, second) => if (first.date.isAfter(second.date)) first else second)

  PostByAuthors
    .join(usersByKey, (posts, authors) => posts.map(LinkedPost(_, authors)))
    .toStream
    .flatMapValues(PostToCsv)
    .to("linked-posts")

  val config = new Properties()
  config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092")
  config.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream0")
  val streams = new KafkaStreams(streamsBuilder.build(), config)
  Runtime.getRuntime.addShutdownHook(new Thread(() => streams.close()))
  streams.start()
}
