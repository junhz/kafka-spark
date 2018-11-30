package junhz.home.kafka.stream

import org.apache.kafka.streams.{ KafkaStreams, Topology }

trait Infrastructure {

  def kafkaStreams(topo: Topology, appId: String): KafkaStreams

}