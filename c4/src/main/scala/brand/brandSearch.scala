import utils._
import org.apache.spark.rdd._
import classes.UserUtils._
import classes.PostUtils._
import classes.LinkedPost
import java.time.Instant


object BrandSearch {

  def main (args : Array[String]) : Any = {
    println("Type brand to search for: \n");
    val brand = StdIn.readLine();
    println("Type number of days/months: \n");
    val period = StdIn.readLine();

    val w = parsePeriod(period)
    if (w.isEmpty)
      println("Error occured in function parsePeriod\n")
    else {
      val n = Instant.now().minus(Duration.ofDays(w.get));
      countBrandOccurences(brand, n);
    }
  }

  val fileToReadFrom = "data/posts/saved-*"

  def loadData(): RDD[String] = {
    // create spark configuration and spark context
    val conf = new SparkConf()
      .setAppName("Brand searching")
      .setMaster("local[*]")

    //  un rdd
    val sc = SparkContext.getOrCreate(conf)

    val test1 = sc.parallelize(List(
      ("User0", "Post0, 2017-20-07, User0, https://facebook.com, le texte étoilé.! :O")))


    test1.saveAsTextFile ("data/posts/saved");

    // load the data and parse it into i don't know what
    sc.textFile(fileToReadFrom).flatMap(StringToLinkedPost)
  }


  def splitPeriod(period : String) : (String, String) = {
    val s = period.split(" ")
    (s(1), s(2).toLowerCase)
  }


  /**
   * Parse the user input for period
   */
  def parsePeriod(period : String) : Option[Int] = {
    val p = splitPeriod(period)
    try{
      if (p._2 == "month" || p._2 == "mois" || p._2 == "months"
        Some(p._1.toInt) * 30
      else if (p._2 == "days" || p._2 == "day" || p._2 == "jour" || p._2 == "jours")
        Some(p._1.toInt)
      else
        None;
    }
    catch {
      None;
    }
  }

  /**
   * Count number of brand occurences
   */
  def countBrandOccurences(brand : String, period : Instant) : RDD[(String, Int)] = {
    loadData().filter(x => x.data.isAfter(period))
              .map(x => x._2.split(" "))
              .flatMap(identity)
              .filter(x => x == brand)
              .map(x => (x, 1))
              .reduceByKey((a, b) => b + a)
  }
}
