package back.vybz.busker_info_read_service.busker_info.presentation;

import back.vybz.busker_info_read_service.busker_info.application.BuskerInfoReadService;
import back.vybz.busker_info_read_service.busker_info.dto.ResponseBuskerInfoReadDto;
import back.vybz.busker_info_read_service.busker_info.vo.ResponseBuskerInfoReadVo;
import back.vybz.busker_info_read_service.common.entity.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/busker-info-read")
public class BuskerInfoReadController {

    private final BuskerInfoReadService buskerInfoReadService;

    /**
     * 버스커 uuid로 버스커 정보 조회
     *
     * @param buskerUuid
     */
    @Operation(summary = "버스커 uuid로 마이페이지 버스커 정보 조회", description = "버스커 uuid로 마이페이지 버스커 정보를 조회합니다.", tags = {"Busker-Info-Read-Service"})
    @GetMapping("/{buskerUuid}")
    public BaseResponseEntity<ResponseBuskerInfoReadVo> getBuskerInfo(@PathVariable("buskerUuid") String buskerUuid) {
        ResponseBuskerInfoReadDto responseBuskerInfoReadDto = buskerInfoReadService.getBuskerInfo(buskerUuid);
        return new BaseResponseEntity<>(responseBuskerInfoReadDto.toVo());
    }

}
