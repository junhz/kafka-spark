package junhz.home.kafka.producer

import java.util.Properties
import org.apache.kafka.clients.producer.{ ProducerConfig, KafkaProducer }
import org.apache.kafka.common.serialization.{ Serdes, Serde }

object Main {
  def main(args: Array[String]): Unit = {
    val infra = new Infrastructure {
	  def kafkaProducer[K, V](k: Serde[K], v: Serde[V]) = {
	    val props = new Properties()
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        
        val producer = new KafkaProducer(props, k.serializer(), v.serializer())
        
        sys addShutdownHook {
          producer.close()
        }
		producer
	  }
	}
	
	Random1_1000("random1_1000", infra.kafkaProducer(Serdes.Integer().asInstanceOf[Serde[Int]], Serdes.Integer().asInstanceOf[Serde[Int]]))
  }

}