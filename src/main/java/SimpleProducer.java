import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) {
        KafkaProducer<String, String> producer=new KafkaProducer(getProducerConfig()) ;
        ProducerRecord<String, String> record= new ProducerRecord("my_topic1","Key 1","Hello topic Msg 1");

        // async call
        producer.send(record);
        producer.flush();
        producer.close();

        System.out.println("msg sent");

    }

    private static Properties getProducerConfig(){
        Properties configs=new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configs;
    }
}
