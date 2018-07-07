import java.time.Instant
import java.net.URI

case class User(id : String, updatedOn : Instant, image : URI, nickname : String, verified : Boolean, deleted : Boolean)

object User
{

}
