package junhz.home.spark.app

import org.apache.spark.SparkContext

trait Infrastructure {
  def sparkContext(appName: String): SparkContext
}