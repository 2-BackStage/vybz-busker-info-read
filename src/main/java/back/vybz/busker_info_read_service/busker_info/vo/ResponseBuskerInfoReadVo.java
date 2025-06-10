package back.vybz.busker_info_read_service.busker_info.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBuskerInfoReadVo {

    private String buskerUuid;
    private String nickname;
    private String profileImageUrl;
    private String introduction;
    private Integer followerCount;
    private Integer subscribedCount;

    @Builder
    public ResponseBuskerInfoReadVo(String buskerUuid, String nickname, String profileImageUrl,
                                     String introduction, Integer followerCount, Integer subscribedCount) {
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
        this.followerCount = followerCount;
        this.subscribedCount = subscribedCount;
    }

}
