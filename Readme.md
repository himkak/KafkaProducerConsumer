# Project Description
This is a simple core java application.
This project is targeted to demonstrate a sample kafka producer & consumer.

Add kafka client dependency.

## Step 1
Add kafka client dependency into pom.

## Step 2
### Setup producer config
Majorly we setup 3 configurations, 
- producer IP/IPS, 
- key serializer. 
- value serializer.

Refer com.him.sample.producer.SimpleProducer class.
For other producer config can refer : https://kafka.apache.org/documentation.html#producerconfigs

### Setup Producer

## Step 3

### Setup consumer configuration

### Setup Consumer

## Step 4 
### Run Kafka

1. Start the zookeeper  
`zookeeper-server-start.sh config\zookeeper.properties`
2. Start kafka broker  
`kafka-server-start.bat config\server.properties`

3. Create topic:  
`kafka-topics.sh --create --topic my_topic1 --zookeeper localhost:2181 --replication-factor 1 --partitions 1`

### Run the application
1. Run the producer:  
Run the SimpleProducer class. This will publish the message into the topic.

2. Run the consumer:  
This will consume the message from the topic.
