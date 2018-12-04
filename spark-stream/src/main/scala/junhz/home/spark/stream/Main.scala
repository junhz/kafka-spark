package junhz.home.spark.stream


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import java.net.InetAddress

object Main {
  object infra {
    def sparkSession(appName: String) = {
	  val session = SparkSession.builder().appName(appName).master(s"spark://${InetAddress.getLocalHost().getHostName()}:7077").getOrCreate()
	  sys addShutdownHook {
        session.close()
      }
      session
	}
  }

  def main(args: Array[String]): Unit = {
    val spark = infra.sparkSession("isPrime")
    val ds = spark.readStream.format("kafka")
	         .option("kafka.bootstrap.servers", "localhost:9092")
			 .option("subscribe", "random1_1000")
			 .option("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer")
			 .option("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer")
			 .load()
    val s = ds.selectExpr("CAST(key AS INT)", "CAST(value AS INT)").join(spark.range(1, 1001), pmod(col("value"), col("id")).equalTo(0)).groupBy(col("key"), col("value")).agg(count("*").equalTo(2))
	val d = s.writeStream.format("kafka")
	        .option("kafka.bootstrap.servers", "localhost:9092")
			.option("topic", "randomPrime1_1000")
			.option("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
			.option("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
			.start()
	sys addShutdownHook {
        d.stop()
    }
	d.awaitTermination()
  }

}