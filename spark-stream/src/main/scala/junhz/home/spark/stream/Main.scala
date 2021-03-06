package junhz.home.spark.stream

import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming._
import org.apache.spark.SparkConf

object Main {

  def main(args: Array[String]): Unit = {
    val streamingContext = new StreamingContext(new SparkConf().setAppName("IsPrime_Streaming"), Seconds(1))
  
    val kafkaParams = Map[String, Object](
	  "bootstrap.servers" -> "localhost:9092",
	  "key.deserializer" -> classOf[IntegerDeserializer],
	  "value.deserializer" -> classOf[IntegerDeserializer],
	  "group.id" -> "IsPrime_Streaming"
	)
	
	val stream = KafkaUtils.createDirectStream[Int, Int](
	  streamingContext, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[Int, Int](Seq("random1_1000"), kafkaParams)
	)
	
	(stream map { record =>
	  record.key
	} filter { value =>
      !(2 until value).exists(i => value % i == 0)
    }).print()
	
	streamingContext.start()
	sys addShutdownHook {
	  streamingContext.stop()
	}
	streamingContext.awaitTermination()
  }

}