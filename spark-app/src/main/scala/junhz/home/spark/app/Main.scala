package junhz.home.spark.app


import org.apache.spark.{ SparkContext, SparkConf }
import java.net.InetAddress

object Main {
  val infra = new Infrastructure {
    def sparkContext(appName: String) = {
	  val ctx = new SparkContext(new SparkConf().setAppName(appName).setMaster(s"spark://${InetAddress.getLocalHost().getHostName()}:7077"))
	  ctx addJar s"${System.getProperty("user.home")}/.ivy2/local/junhz.home/spark-func_2.11/0.0.1/jars/spark-func_2.11.jar"
	  sys addShutdownHook {
        ctx.stop()
      }
      ctx
	}
  }

  def main(args: Array[String]): Unit = {
    println(IsPrime(infra.sparkContext("isPrime"), args(0).toInt))
  }

}