package back.vybz.busker_info_read_service.kafka.consumer;

import back.vybz.busker_info_read_service.busker_info.infrastructure.BuskerInfoReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteBuskerInfoEventConsumer {

    private final BuskerInfoReadRepository buskerInfoReadRepository;

    @KafkaListener(
            topics = "delete-busker-info",
            groupId = "busker-info-read-group",
            containerFactory = "stringBuskerInfoKafkaListenerContainerFactory"
    )
    public void consumeDeleteBuskerInfoEvent(String buskerUuid) {
        log.info("🔥 Kafka 버스커 정보 삭제 메시지 수신: {}", buskerUuid);

        if (!buskerInfoReadRepository.existsByBuskerUuid(buskerUuid)) {
            log.warn("🔥 버스커 정보가 존재하지 않습니다. buskerUuid: {}", buskerUuid);
            return;
        }

        buskerInfoReadRepository.deleteByBuskerUuid(buskerUuid);
        log.info("🔥 버스커 정보 삭제 완료: {}", buskerUuid);
    }

}
