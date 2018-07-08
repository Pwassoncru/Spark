import API._
import Streams._
import PostUtils._

object Main {
  def main(args : Array[String]) : Unit = {
    val api = API
    val streams = Streams
    val hdfsWriter = RDDStreams

    API.create_user("user0", "Gebb")
    API.create_user("user1", "ln")
    API.create_user("user2", "Pwassoncru")
    API.create_user("user3", "Flo Helene")
    API.create_user("user2", "latest user2")

    API.create_post("post0", "user0", "Yo les gens")
    API.create_post("post1", "user0", "Coucou")
    API.create_post("post2", "user2", "Pourquoi ?")
    API.create_post("post3", "user0", "Spark > All")
    API.create_post("post1", "user0", "latest post1")

    API.create_message("message0", "user0", "user1", "Yooooo")
    API.create_message("message1", "user0", "user1", "Ca va ?")
    API.create_message("message2", "user1", "user0", "Ouep et toi ?")
    API.create_message("message3", "user3", "user2", "regarde ca: https://youtube.com")

    scala.io.StdIn.readLine()
      
    API.producer.close()
    Streams.streams.close()
  }
}
