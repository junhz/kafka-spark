package junhz.home.spark.app

import junhz.home.spark.func.IsDivisorOf
import org.apache.spark.SparkContext

object IsPrime {
  
  def apply(sc: SparkContext, i: Int): Boolean = {
    val ns = sc.parallelize(2 until i)
    ns.filter(IsDivisorOf(i)).count() == 0
  }

}