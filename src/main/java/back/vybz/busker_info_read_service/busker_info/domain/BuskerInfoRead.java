package back.vybz.busker_info_read_service.busker_info.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Document("busker_info_read")
public class BuskerInfoRead {

    @Id
    private String id;

    /**
     * 버스커 UUID
     */
    @Field(name = "busker_uuid")
    private String buskerUuid;

    /**
     * 버스커 닉네임
     */
    @Field(name = "nickname")
    private String nickname;

    /**
     * 버스커 프로필 이미지 URL
     */
    @Field(name = "profile_image_url")
    private String profileImageUrl;

    /**
     * 버스커 소개
     */
    @Field(name = "introduction")
    private String introduction;

    /**
     * 버스커 팔로워 수
     */
    @Field(name = "follower_count")
    private Integer followerCount;

    /**
     * 버스커 팔로워 수 (표시용)
     */
    @Field(name = "display_follower_count")
    private String displayFollowerCount;

    /**
     * 버스커 구독자 수
     */
    @Field(name = "subscribed_count")
    private Integer subscribedCount;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public void updateBuskerInfo(String buskerUuid, String nickname, String profileImageUrl, String introduction) {
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
    }

    public void updateFollowerCount(Integer followerCount, String displayFollowerCount) {
        this.followerCount = followerCount;
        this.displayFollowerCount = displayFollowerCount;
    }

    @Builder
    public BuskerInfoRead(String id, String buskerUuid, String nickname, String profileImageUrl,
                          String introduction, Integer followerCount, Integer subscribedCount,
                          Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
        this.followerCount = followerCount;
        this.subscribedCount = subscribedCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
