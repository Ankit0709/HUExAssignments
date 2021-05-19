package com.hashedin.assignments.trainDashboard.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Order(2)
public class KafkaProducerConfig {
    @Value("${kafka.producer.bootstrap-server}")
    private String bootstrapAddress;
    @Value("${kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value("${kafka.producer.properties.acks}")
    private String producerAcksProperty;
    @Value("${kafka.producer.retries}")
    private Integer retiesProperty;
    @Value("${kafka.producer.retry.backoff.ms}")
    private Integer backoffMilliSecondsProperty;

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object>  producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializer);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);
        producerConfig.put(ProducerConfig.ACKS_CONFIG,producerAcksProperty);
        producerConfig.put(ProducerConfig.RETRIES_CONFIG,retiesProperty);
        producerConfig.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,backoffMilliSecondsProperty);
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<String,String>(producerFactory());
    }
}
