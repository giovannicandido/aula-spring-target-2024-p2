package br.com.eadtt.spring.p2.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.ContainerCustomizer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties properties;

//    @Bean
//    @Primary
    ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
                                                                                          ObjectProvider<ConsumerFactory<Object, Object>> kafkaConsumerFactory,
                                                                                          ObjectProvider<ContainerCustomizer<Object, Object,
                                                                                                  ConcurrentMessageListenerContainer<Object, Object>>> kafkaContainerCustomizer,
                                                                                          ObjectProvider<SslBundles> sslBundles) {
        JsonDeserializer<?> payloadJsonDeserializer = new JsonDeserializer<Object>();

        Map<String, Object> configs = this.properties.buildConsumerProperties((SslBundles) sslBundles.getIfAvailable());

        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");


        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory();

        configurer.configure(factory, (ConsumerFactory) kafkaConsumerFactory.getIfAvailable(() -> {
            return new DefaultKafkaConsumerFactory(configs, new StringDeserializer(), payloadJsonDeserializer);
        }));
        Objects.requireNonNull(factory);
        kafkaContainerCustomizer.ifAvailable(factory::setContainerCustomizer);

        return factory;
    }

}
