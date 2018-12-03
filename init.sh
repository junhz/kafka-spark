#!/bin/bash

#prepare
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys
apt-get update
apt-get install openjdk-8-jre-headless -y
apt-get install git -y
apt-get install scala -y

#kafka
cd ~
wget http://apache.mirrors.hoobly.com/kafka/2.1.0/kafka_2.11-2.1.0.tgz
cd /usr/lib
tar xvf /root/kafka_2.11-2.1.0.tgz

#spark
cd ~
wget http://apache.mirrors.hoobly.com/spark/spark-2.4.0/spark-2.4.0-bin-hadoop2.7.tgz
cd /usr/lib
tar xvf /root/spark-2.4.0-bin-hadoop2.7.tgz

#sbt
cd ~
wget https://sbt-downloads.cdnedge.bluemix.net/releases/v1.2.6/sbt-1.2.6.tgz
cd /usr/lib
tar xvf /root/sbt-1.2.6.tgz

#run
cd ~
nohup /usr/lib/kafka_2.11-2.1.0/bin/zookeeper-server-start.sh /usr/lib/kafka_2.11-2.1.0/config/zookeeper.properties &
nohup /usr/lib/kafka_2.11-2.1.0/bin/kafka-server-start.sh /usr/lib/kafka_2.11-2.1.0/config/server.properties &
/usr/lib/spark-2.4.0-bin-hadoop2.7/sbin/start-master.sh
/usr/lib/spark-2.4.0-bin-hadoop2.7/sbin/start-slave.sh spark://`hostname`:7077

/usr/lib/kafka_2.11-2.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic random1_1000
/usr/lib/kafka_2.11-2.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic randomPrime1_1000
git clone https://github.com/junhz/kafka-spark.git