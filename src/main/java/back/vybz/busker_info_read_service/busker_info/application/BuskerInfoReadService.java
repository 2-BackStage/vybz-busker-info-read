package back.vybz.busker_info_read_service.busker_info.application;

import back.vybz.busker_info_read_service.busker_info.dto.ResponseBuskerInfoReadDto;

public interface BuskerInfoReadService {

    /**
     * 버스커 UUID로 버스커 정보 조회
     * @param buskerUuid
     */
    ResponseBuskerInfoReadDto getBuskerInfo(String buskerUuid);

}
