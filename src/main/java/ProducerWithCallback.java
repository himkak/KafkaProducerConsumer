import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerWithCallback {


    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer(getProducerConfig());
        ProducerRecord<String, String> record = new ProducerRecord("my_topic1", "Key 1", "Hello topic Msg 1");

        // async call
        Callback callback = new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (null == e) {
                    System.out.println("offset:" + recordMetadata.offset() + "\n timestamp:" + recordMetadata.timestamp());
                } else {
                    e.printStackTrace();
                }
            }
        };
        producer.send(record, callback);
        producer.flush();
        producer.close();

        System.out.println("msg sent");

    }

    private static Properties getProducerConfig() {
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configs;
    }

}
