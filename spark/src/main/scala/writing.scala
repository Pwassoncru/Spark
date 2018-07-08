import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010._
import org.apache.kafka.common.serialization.StringDeserializer

import PostUtils._
import MessageUtils._
import UserUtils._

object RDDStreams {

  println("RDD streams are starting")

  val conf = new SparkConf().setAppName("redirectingKafkaStream").setMaster("local[*]")
  val sc = new StreamingContext(conf, Seconds(15))

  val config = Map[String,Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_id_for_each_stream"
  )

  // See how it writes to hdfs => Maybe do 3 streams.
  val topics = List("sinkPosts")

  val stream = KafkaUtils.createDirectStream[String,String](
    sc,
    LocationStrategies.PreferConsistent,
    ConsumerStrategies.Subscribe[String,String](topics, config))

  stream.saveAsTextFiles("data/posts")

  sc.start()
}
