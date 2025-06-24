package back.vybz.busker_info_read_service.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuskerInfoEvent {

    private String buskerUuid;
    private String nickname;
    private String profileImageUrl;
    private String introduction;
    private Integer followerCount;
    private String displayFollowerCount;
    private Integer subscribedCount;

}
