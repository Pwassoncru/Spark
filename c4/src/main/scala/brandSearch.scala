import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd._
import java.time.{Instant, Duration}
import scala.io.StdIn
import PostUtils._


object BrandSearch {

  def main (args : Array[String]) : Unit = {
    println("Type brand to search for:");
    val brand = StdIn.readLine();
    println("Type number of days/months:");
    val period = StdIn.readLine();
    println("Type in the directory containing all the \"part\" files");
    val fileToReadFrom = StdIn.readLine() + "/part-*";

    val w = parsePeriod(period)
    if (w.isEmpty)
      println("Error occured in function parsePeriod")
    else {
      val duration = Instant.now().minus(Duration.ofDays(w.get));
      val count = countBrandOccurences(brand, duration, fileToReadFrom);
      println("The brand " + brand + " appeared " + count + " times!")
    }
  }

  def loadData(fileToReadFrom : String): RDD[Post] = {
    // create spark configuration and spark context
    val conf = new SparkConf()
      .setAppName("Brand searching")
      .setMaster("local[*]")

    //  un rdd
    val sc = SparkContext.getOrCreate(conf)

    //val test1 = sc.parallelize(List(
    //  ("Post0,2017-10-07T10:15:30.00Z,User0,https://facebook.com,le texte étoilé.! :O,False")))


    //test1.saveAsTextFile ("data/posts/")

    // load the data and parse it into i don't know what
    sc.textFile(fileToReadFrom).map(StringToPost)
  }

  /**
   * Parse the user input for period
   */
  def parsePeriod(period : String) : Option[Int] = {
    try{
      val splitted = period.split(" ")
      val p = (splitted(0), splitted(1).toLowerCase)
      if (p._2 == "month" || p._2 == "mois" || p._2 == "months")
        Some(p._1.toInt * 30)
      else if (p._2 == "days" || p._2 == "day" || p._2 == "jour" || p._2 == "jours")
        Some(p._1.toInt)
      else
        None
    }
    catch {
      case _ : Throwable => None
    }
  }

  /**
   * Count number of brand occurences
   */
  def countBrandOccurences(brand : String, period : Instant, file : String) : Int = {
    val arr = loadData(file).filter(x => x.date.isAfter(period))
                        .map(x => x.text.split(" "))
                        .flatMap(x => x)
                        .filter(x => x == brand)
                        .map(x => (x, 1))
                        .reduceByKey((a, b) => b + a)
                        .take(1)
    if (arr.length == 0)
      0
    else
      arr.head._2
  }
}
