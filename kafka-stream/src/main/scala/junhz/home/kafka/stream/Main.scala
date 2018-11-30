package junhz.home.kafka.stream

import java.util.Properties
import org.apache.kafka.streams.{ StreamsConfig, KafkaStreams, Topology }

object Main {
  val infra = new Infrastructure {
	def kafkaStreams(topo: Topology, appId: String) = {
	  val props = new Properties()
      props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      props.put(StreamsConfig.APPLICATION_ID_CONFIG, appId)
        
      val streams = new KafkaStreams(topo, props)
        
      sys addShutdownHook {
        streams.close()
      }
	  streams
	}
  }
  
  def main(args: Array[String]): Unit = {
    infra.kafkaStreams(PrimeOnly("random1_1000", "randomPrime1_1000"), "junhz").start()
	while (true) {
	  Thread.sleep(1000)
	}
  }

}