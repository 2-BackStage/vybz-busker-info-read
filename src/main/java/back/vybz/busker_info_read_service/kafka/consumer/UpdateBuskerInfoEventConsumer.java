package back.vybz.busker_info_read_service.kafka.consumer;

import back.vybz.busker_info_read_service.busker_info.domain.BuskerInfoRead;
import back.vybz.busker_info_read_service.busker_info.infrastructure.BuskerInfoReadRepository;
import back.vybz.busker_info_read_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateBuskerInfoEventConsumer {

    private final BuskerInfoReadRepository buskerInfoReadRepository;

    @KafkaListener(
            topics = "update-busker-info",
            groupId = "busker-info-read-group",
            containerFactory = "buskerInfoKafkaListenerContainerFactory"
    )
    public void consumeUpdateBuskerInfoEvent(BuskerInfoEvent buskerInfoEvent) {
        log.info("📦 [Kafka] 버스커 정보 업데이트 이벤트 수신: {}", buskerInfoEvent);

        BuskerInfoRead buskerInfoRead = buskerInfoReadRepository.findByBuskerUuid(buskerInfoEvent.getBuskerUuid())
                .orElse(null);

        if (buskerInfoRead == null) {
            log.warn("📦 [Kafka] 버스커 정보가 존재하지 않습니다. buskerUuid: {}", buskerInfoEvent.getBuskerUuid());
            return;
        }
        buskerInfoRead.updateBuskerInfo(
                buskerInfoEvent.getBuskerUuid(),
                buskerInfoEvent.getNickname(),
                buskerInfoEvent.getProfileImageUrl(),
                buskerInfoEvent.getIntroduction());

        buskerInfoReadRepository.save(buskerInfoRead);
        log.info("📦 [Kafka] 버스커 정보 업데이트 완료: {}", buskerInfoRead);
    }

}
