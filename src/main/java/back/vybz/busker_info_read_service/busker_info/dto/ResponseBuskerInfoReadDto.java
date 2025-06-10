package back.vybz.busker_info_read_service.busker_info.dto;

import back.vybz.busker_info_read_service.busker_info.domain.BuskerInfoRead;
import back.vybz.busker_info_read_service.busker_info.vo.ResponseBuskerInfoReadVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerInfoReadDto {

    private String buskerUuid;
    private String nickname;
    private String profileImageUrl;
    private String introduction;
    private Integer followerCount;
    private Integer subscribedCount;

    @Builder
    public ResponseBuskerInfoReadDto(String buskerUuid, String nickname, String profileImageUrl,
                                      String introduction, Integer followerCount, Integer subscribedCount) {
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
        this.followerCount = followerCount;
        this.subscribedCount = subscribedCount;
    }

    public static ResponseBuskerInfoReadDto from(BuskerInfoRead buskerInfoRead) {
        return ResponseBuskerInfoReadDto.builder()
                .buskerUuid(buskerInfoRead.getBuskerUuid())
                .nickname(buskerInfoRead.getNickname())
                .profileImageUrl(buskerInfoRead.getProfileImageUrl())
                .introduction(buskerInfoRead.getIntroduction())
                .followerCount(buskerInfoRead.getFollowerCount())
                .subscribedCount(buskerInfoRead.getSubscribedCount())
                .build();
    }

    public ResponseBuskerInfoReadVo toVo() {
        return ResponseBuskerInfoReadVo.builder()
                .buskerUuid(buskerUuid)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .introduction(introduction)
                .followerCount(followerCount)
                .subscribedCount(subscribedCount)
                .build();
    }

}
