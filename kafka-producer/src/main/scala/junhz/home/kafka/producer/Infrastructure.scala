package junhz.home.kafka.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.Serde

trait Infrastructure {
  def kafkaProducer[K, V](k: Serde[K], v: Serde[V]): KafkaProducer[K, V]
}