import java.util.Properties
import org.apache.kafka.streams.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import org.apache.kafka.common.serialization.Serdes

import PostUtils._
import MessageUtils._
import UserUtils._

object Streams {

  println("Streams are starting")

  val config = new Properties()
  config.put("bootstrap.servers", "localhost:9092")
  config.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-application")
  config.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName())
  config.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName())

  val streamBuilder = new StreamsBuilder()
  val usersStream = streamBuilder.stream[String, String]("users")
  val postsStream = streamBuilder.stream[String, String]("posts")
  val messagesStream = streamBuilder.stream[String, String]("messages")

  def UsersByKey = usersStream
    .groupByKey
    .reduce(new Reducer[String] {
      override def apply(first : String, second : String) =
        if (StringToUser(first).date.isAfter(StringToUser(second).date)) first else second
    })

  def PostByKey = postsStream
    .groupByKey
    .reduce(new Reducer[String] {
      override def apply(first : String, second : String) =
        if (StringToPost(first).date.isAfter(StringToPost(second).date)) first else second
    })

  def MessagesByKey = messagesStream
    .groupByKey
    .reduce(new Reducer[String] {
      override def apply(first : String, second : String) =
        if (StringToMessage(first).date.isAfter(StringToMessage(second).date)) first else second
    })

  /*
  PostByAuthor
    .join(UsersByKey, (posts : Set[String], author : String) => posts.map(LinkedPost(_, author)))
    .toStream
    .flatMapValues(PostToCsv)
    .to("linked-posts")*/

  UsersByKey.to("sinkUsers")
  PostByKey.to("sinkPosts")
  MessagesByKey.to("sinkMessages")

  val streams = new KafkaStreams(streamBuilder.build(), config)
  streams.start()
}
