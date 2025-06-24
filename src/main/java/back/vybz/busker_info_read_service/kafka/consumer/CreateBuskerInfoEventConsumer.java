package back.vybz.busker_info_read_service.kafka.consumer;

import back.vybz.busker_info_read_service.busker_info.domain.BuskerInfoRead;
import back.vybz.busker_info_read_service.busker_info.infrastructure.BuskerInfoReadRepository;
import back.vybz.busker_info_read_service.common.entity.BaseResponseStatus;
import back.vybz.busker_info_read_service.common.exception.BaseException;
import back.vybz.busker_info_read_service.kafka.event.BuskerInfoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateBuskerInfoEventConsumer {

    private final BuskerInfoReadRepository buskerInfoReadRepository;

    @KafkaListener(
            topics = "create-busker-auth",
            groupId = "busker-info-read-group",
            containerFactory = "buskerInfoKafkaListenerContainerFactory"
    )
    public void consumeBuskerInfoEvent(BuskerInfoEvent buskerInfoEvent) {
        log.info("🔥 Kafka 버스커 정보 메시지 수신: {}", buskerInfoEvent);

        boolean exists = buskerInfoReadRepository.existsByBuskerUuid(buskerInfoEvent.getBuskerUuid());

        if (exists) {
            log.warn("🔥 버스커 정보가 이미 존재합니다. buskerUuid: {}", buskerInfoEvent.getBuskerUuid());
            throw new BaseException(BaseResponseStatus.DUPLICATE_USER);
        }
        BuskerInfoRead buskerInfoRead = BuskerInfoRead.builder()
                .buskerUuid(buskerInfoEvent.getBuskerUuid())
                .nickname(buskerInfoEvent.getNickname())
                .introduction(buskerInfoEvent.getIntroduction())
                .profileImageUrl(buskerInfoEvent.getProfileImageUrl())
                .followerCount(buskerInfoEvent.getFollowerCount() == null ? 0 : buskerInfoEvent.getFollowerCount())
                .displayFollowerCount(buskerInfoEvent.getDisplayFollowerCount() == null ? "0" : buskerInfoEvent.getDisplayFollowerCount())
                .subscribedCount(buskerInfoEvent.getSubscribedCount() == null ? 0 : buskerInfoEvent.getSubscribedCount())
                .build();

        buskerInfoReadRepository.save(buskerInfoRead);
        log.info("🔥 버스커 정보 저장 완료: {}", buskerInfoRead);
    }

}
