package junhz.home.kafka.stream

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{ StreamsBuilder, Topology }
import org.apache.kafka.streams.kstream.{ Consumed, Produced, KStream }
import org.apache.spark.SparkContext
import junhz.home.spark.app.IsPrime

object PrimeOnly {

  def apply(fromTopic: String, toTopic: String, sc: SparkContext): Topology = {
    val sb = new StreamsBuilder()
	sb.stream(fromTopic, Consumed.`with`(Serdes.Integer(), Serdes.Integer()))
	  .filter((k, v) => IsPrime(sc, k))
	  .to(toTopic, Produced.`with`(Serdes.Integer(), Serdes.Integer()))
	sb.build()
  }
  
}