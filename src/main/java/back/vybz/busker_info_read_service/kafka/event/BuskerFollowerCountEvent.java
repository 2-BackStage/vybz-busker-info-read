package back.vybz.busker_info_read_service.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuskerFollowerCountEvent {

    private String buskerUuid;
    private Integer followerCount;
    private String displayFollowerCount;

}
