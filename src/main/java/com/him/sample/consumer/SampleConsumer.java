package com.him.sample.consumer;

import com.him.sample.producer.SimpleProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class SampleConsumer {
    public static void main(String[] args) {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerConfig());
        readAllMsgs(consumer);

        readFromSpecificOffset(consumer);

    }

    private static void readFromSpecificOffset(KafkaConsumer<String, String> consumer) {

        TopicPartition partition0=new TopicPartition("My-Topic",1);
        Collection<TopicPartition> topicPartitions = Arrays.asList(partition0);
        consumer.assign(topicPartitions);

        consumer.seek(partition0,10);

    }

    private static void readAllMsgs(KafkaConsumer<String, String> consumer) {
        consumer.subscribe(Arrays.asList(SimpleProducer.MY_TOPIC_1));
        while (true){
            ConsumerRecords<String, String> records= consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord record: records){
                System.out.println("Key:"+record.key()+", value:"+record.value()+", partition:"+record.partition()+", offset:"+record.offset());
            }
        }
    }


    private static Properties getConsumerConfig() {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "him-app-grp1");
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configs;
    }
}
