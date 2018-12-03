package junhz.home.kafka.producer

import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerRecord }

object Random1_1000 {

  def apply(topic: String, producer: KafkaProducer[Int, Int]): Unit = {
    while (true) {
      Thread.sleep(1000)
      val milis = (System.nanoTime() % 1000 + 1).toInt
      print(producer.send(new ProducerRecord(topic, milis, milis)).get().toString())
    }
  }
  
}