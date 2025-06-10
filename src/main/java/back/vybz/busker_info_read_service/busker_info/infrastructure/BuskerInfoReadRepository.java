package back.vybz.busker_info_read_service.busker_info.infrastructure;

import back.vybz.busker_info_read_service.busker_info.domain.BuskerInfoRead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuskerInfoReadRepository extends MongoRepository<BuskerInfoRead, String> {

    /**
     * 버스커 UUID로 버스커 정보 조회
     * @param buskerUuid
     */
    Optional<BuskerInfoRead> findByBuskerUuid(String buskerUuid);

    /**
     * 버스커 UUID로 버스커 존재 여부 확인
     * @param buskerUuid
     */
    boolean existsByBuskerUuid(String buskerUuid);

    /**
     * 버스커 UUID로 버스커 정보 삭제
     * @param buskerUuid
     */
    void deleteByBuskerUuid(String buskerUuid);

}
