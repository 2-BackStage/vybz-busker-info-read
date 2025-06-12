package back.vybz.busker_info_read_service.busker_info.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBuskerInfoReadVo {

    private String nickname;
    private String profileImageUrl;
    private String introduction;
    private Integer followerCount;
    private String displayFollowerCount;
    private Integer subscribedCount;

    @Builder
    public ResponseBuskerInfoReadVo(String nickname, String profileImageUrl,
                                     String introduction, Integer followerCount, String displayFollowerCount,
                                     Integer subscribedCount) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
        this.followerCount = followerCount;
        this.displayFollowerCount = displayFollowerCount;
        this.subscribedCount = subscribedCount;
    }

}
