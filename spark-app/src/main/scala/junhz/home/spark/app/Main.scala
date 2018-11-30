package junhz.home.spark.app


import org.apache.spark.{ SparkContext, SparkConf }
import java.net.InetAddress

object Main {

  def main(args: Array[String]): Unit = {
    val infra = new Infrastructure {
      def sparkContext(appName: String) = {
	    val ctx = new SparkContext(new SparkConf().setAppName(appName).setMaster(s"spark://${InetAddress.getLocalHost().getHostName()}:7077"))
	    sys addShutdownHook {
          ctx.stop()
        }
        ctx
	  }
    }
    println(IsPrime(infra.sparkContext("isPrime"), args(0).toInt))
  }

}