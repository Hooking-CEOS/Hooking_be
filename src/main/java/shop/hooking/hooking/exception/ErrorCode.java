package shop.hooking.hooking.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.hooking.hooking.dto.ErrorReason;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode{

    //400 BAD_REQUEST : 잘못된 요청
    CANNOT_EMPTY_CONTENT(BAD_REQUEST, "COPY_400_1","내용이 비어있을 수 없습니다."),
    INVALID_VALUE(BAD_REQUEST, "ADMIN_400_1","올바르지 않은 값입니다."),
    CANNOT_DUPLICATE_SCRAP(BAD_REQUEST, "SCRAP_400_1","중복 스크랩이 불가합니다."),

    //403 FORBIDDEN : 권한 없음
    USER_UNAUTHORIZED(UNAUTHORIZED,"USER_403_1", "로그인이 필요한 서비스입니다."),
    ACCESS_DENIED(FORBIDDEN,"ADMIN_403_1", "해당 페이지를 요청할 권한이 없습니다."),

    //404 NOT_FOUND : Resource를 찾을 수 없음
    USER_NOT_FOUND(NOT_FOUND,"USER_404_1", "해당 유저 정보를 찾을 수 없습니다."),
    CARD_NOT_FOUND(NOT_FOUND, "COPY_404_1","해당 결과를 찾을 수 없습니다."),
    SCRAP_NOT_FOUND(NOT_FOUND,"SCRAP_404_1", "해당 스크랩을 찾을 수 없습니다."),
    OUT_OF_INDEX(NOT_FOUND,"COPY_404_2", "허용가능한 인덱스 범위를 넘었습니다."),

    //500 INTERNAL_SERVER_ERROR : 서버 내 문제
    EXCEPTION(INTERNAL_SERVER_ERROR, "ADMIN_500_1","서버 내에 알 수 없는 오류가 발생했습니다."),
    IO_EXCEPTION(INTERNAL_SERVER_ERROR,"ADMIN_500_2", "이미지 업로드/다운로드 중 알 수 없는 오류가 발생했습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
