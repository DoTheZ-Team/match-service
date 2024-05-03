package com.justdo.glue.sticker.global.response.code;

public interface BaseErrorCode {

    public ErrorReasonDto getReason();

    public ErrorReasonDto getReasonHttpStatus();
}
