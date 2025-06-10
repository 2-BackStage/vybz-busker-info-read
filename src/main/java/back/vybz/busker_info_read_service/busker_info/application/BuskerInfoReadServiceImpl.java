package back.vybz.busker_info_read_service.busker_info.application;

import back.vybz.busker_info_read_service.busker_info.domain.BuskerInfoRead;
import back.vybz.busker_info_read_service.busker_info.dto.ResponseBuskerInfoReadDto;
import back.vybz.busker_info_read_service.busker_info.infrastructure.BuskerInfoReadRepository;
import back.vybz.busker_info_read_service.common.entity.BaseResponseStatus;
import back.vybz.busker_info_read_service.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuskerInfoReadServiceImpl implements BuskerInfoReadService {

    private final BuskerInfoReadRepository buskerInfoReadRepository;

    /**
     * 버스커 UUID로 버스커 정보 조회
     * @param buskerUuid
     */
    @Override
    public ResponseBuskerInfoReadDto getBuskerInfo(String buskerUuid) {
        BuskerInfoRead buskerInfoRead = buskerInfoReadRepository.findByBuskerUuid(buskerUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));
        return ResponseBuskerInfoReadDto.from(buskerInfoRead);
    }

}
