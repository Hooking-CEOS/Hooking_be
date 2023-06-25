package shop.hooking.hooking.dto;



import lombok.Builder;
import lombok.Getter;
import shop.hooking.hooking.entity.User;

import java.util.Map;
import shop.hooking.hooking.config.Role;

//소셜에서 가져온 유저 정보를 담을 도메인
@Getter
public class OAuthAttributesDTO {
    private Map<String, Object> attributes;
    private Long kakaoId;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributesDTO(Map<String, Object> attributes, Long kakaoId, String name,
                              String email, String picture) {
        this.kakaoId = kakaoId;
        this.attributes = attributes;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributesDTO ofKakao(Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");

        return OAuthAttributesDTO.builder()
                .kakaoId((Long)attributes.get("id"))
                .name((String)profile.get("nickname"))
                .email((String)response.get("email"))
                .picture((String)profile.get("profile_image_url"))
                .attributes(attributes)
                .build();
    }

    public User toEntity() {
         User user = User.builder()
                .kakaoId(kakaoId)
                .nickname(name)
                .image(picture)
                .email(email)
                .role(Role.USER)
                .build();

        return user;
    }
}

