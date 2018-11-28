package junhz.home

import java.util.Properties
import org.apache.kafka.clients.producer.{ ProducerConfig, KafkaProducer, ProducerRecord }
import org.apache.kafka.common.serialization.Serdes

object Main {
  def main(args: Array[String]): Unit = {
    val props = new Properties()
	props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
	props.put(ProducerConfig.CLIENT_ID_CONFIG, "random1-1000")
	
	val producer = new KafkaProducer(props, Serdes.Integer().serializer(), Serdes.Integer().serializer())
	
	while (true) {
	  Thread.sleep(1000)
	  val milis = (System.currentTimeMillis() % 1000 + 1).toInt
	  print(producer.send(new ProducerRecord("random1_1000", milis, milis)).get().toString())
	}
	
	producer.close()
  }

}