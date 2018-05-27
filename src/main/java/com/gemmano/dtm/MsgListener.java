package com.gemmano.dtm;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import com.gemmano.dtm.entities.DeviceData;

@Configuration
@EnableKafka
public class MsgListener {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
	@Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    private ConsumerFactory<String, DeviceData> accountConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "device_topics");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaJsonListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeviceData> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountConsumerFactory());
        factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }
}
