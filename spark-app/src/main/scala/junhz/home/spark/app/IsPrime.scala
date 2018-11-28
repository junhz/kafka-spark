package junhz.home.spark.app


import org.apache.spark.{ SparkContext, SparkConf }

object IsPrime {
  val sc = {
    val ctx = new SparkContext(new SparkConf().setAppName("isPrime").setMaster("spark://localhost:7077"))
	sys addShutdownHook {
	  ctx.stop()
	}
	ctx
  }
  
  def apply(i: Int): Boolean = {
    val ns = sc.parallelize(2 until i)
	ns.filter(j => i % j == 0).count() == 0
  }

  def main(args: Array[String]): Unit = {
    print(apply(args(0).toInt))
  }

}