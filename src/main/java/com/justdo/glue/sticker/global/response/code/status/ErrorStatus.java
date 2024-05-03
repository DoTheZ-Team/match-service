package com.justdo.glue.sticker.global.response.code.status;

import com.justdo.glue.sticker.global.response.code.BaseErrorCode;
import com.justdo.glue.sticker.global.response.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 일반 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // ================================================================================================================= //

    // Sticker 관련
    _STICKER_NOT_FOUND(HttpStatus.NOT_FOUND, "STICKER_001", "요청한 sticker는 존재하지 않습니다."),
    _STICKER_NOT_SAVED(HttpStatus.NOT_FOUND, "STICKER_002", "sticker를 서버에 저장하는 과정에서 오류가 발생했습니다."),

    // JWT 관련
    _JWT_NOT_FOUND(HttpStatus.NOT_FOUND, "JWT_001", "Header에 JWT가 존재하지 않습니다."),

    // S3 관련
    _S3_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "S3_001", "S3에 존재하지 않는 이미지입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
            .isSuccess(false)
            .code(code)
            .message(message)
            .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
            .httpStatus(httpStatus)
            .isSuccess(false)
            .code(code)
            .message(message)
            .build();
    }
}