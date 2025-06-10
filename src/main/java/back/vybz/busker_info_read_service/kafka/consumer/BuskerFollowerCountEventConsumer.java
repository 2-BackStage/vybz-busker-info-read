package back.vybz.busker_info_read_service.kafka.consumer;

import back.vybz.busker_info_read_service.busker_info.domain.BuskerInfoRead;
import back.vybz.busker_info_read_service.busker_info.infrastructure.BuskerInfoReadRepository;
import back.vybz.busker_info_read_service.common.entity.BaseResponseStatus;
import back.vybz.busker_info_read_service.common.exception.BaseException;
import back.vybz.busker_info_read_service.kafka.event.BuskerFollowerCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuskerFollowerCountEventConsumer {

    private final BuskerInfoReadRepository buskerInfoReadRepository;

    @KafkaListener(
            topics = "busker-follower-count",
            groupId = "busker-info-read-group",
            containerFactory = "buskerFollowerCountKafkaListenerContainerFactory"
    )
    public void consumeBuskerFollowerCountEvent(BuskerFollowerCountEvent event) {
        log.info("📦 [Kafka] 버스커 팔로워 수 업데이트 이벤트 수신: {}", event);

        BuskerInfoRead buskerInfoRead = buskerInfoReadRepository.findByBuskerUuid(event.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));

        buskerInfoRead.updateFollowerCount(event.getFollowerCount(), event.getDisplayFollowerCount());
        buskerInfoReadRepository.save(buskerInfoRead);
        log.info("📦 [Kafka] 버스커 팔로워 수 업데이트 완료: {}", buskerInfoRead);
    }

}
