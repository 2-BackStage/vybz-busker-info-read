package back.vybz.busker_info_read_service.kafka.config;

import back.vybz.busker_info_read_service.kafka.event.BuskerFollowerCountEvent;
import back.vybz.busker_info_read_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@RequiredArgsConstructor
public class BuskerFollowerCountEventKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, BuskerFollowerCountEvent> buskerFollowerCountEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(BuskerFollowerCountEvent.class, false))
        );
    }

    @Bean(name = "buskerFollowerCountKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, BuskerFollowerCountEvent> buskerFollowerCountKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BuskerFollowerCountEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(buskerFollowerCountEventConsumerFactory());
        return factory;
    }

}
