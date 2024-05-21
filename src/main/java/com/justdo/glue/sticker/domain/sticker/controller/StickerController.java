package com.justdo.glue.sticker.domain.sticker.controller;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;
import com.justdo.glue.sticker.domain.sticker.service.StickerCommandServiceImpl;
import com.justdo.glue.sticker.domain.sticker.service.StickerQueryServiceImpl;
import com.justdo.glue.sticker.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Sticker API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers/sticker")
public class StickerController {

    private final StickerCommandServiceImpl stickerCommandServiceImpl;
    private final StickerQueryServiceImpl stickerQueryServiceImpl;

    //TODO: 달리 api 파라미터 값에 따른 parameter 추가 수정 필요
    //TODO: 사진 생성 횟수 초과시 막는 로직 필요
    @Operation(summary = "이미지 생성 요청", description = "사용자의 요청에 따라 이미지를 생성합니다.")
    @Parameter(name = "prompt", description = "이미지 생성에 사용될 키워드, Query Parameter입니다.", required = true, example = "cat", in = ParameterIn.QUERY)
    @PostMapping("/create")
    public ApiResponse<StickerItem> createImage(HttpServletRequest request,
                                                @RequestParam(name = "prompt") String prompt) {

        return ApiResponse.onSuccess(stickerCommandServiceImpl.generateAndSaveSticker(prompt));
    }
    

    @Operation(summary = "스티커 이미지 조회", description = "사용자가 생성한 스티커를 조회합니다.")
    @Parameter(name = "stickerId", description = "스티커 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @GetMapping("/{stickerId}")
    public ApiResponse<StickerItem> getSticker(HttpServletRequest request,
                                               @PathVariable(name = "stickerId") Long stickerId) {

        return ApiResponse.onSuccess(stickerQueryServiceImpl.getStickerById(stickerId));
    }

    @Operation(summary = "스티커 이미지 삭제", description = "사용자가 생성한 스티커를 삭제합니다.")
    @Parameter(name = "stickerId", description = "스티커 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    // 스티커 삭제 요청
    @DeleteMapping("/{stickerId}")
    public ApiResponse<String> deleteSticker(@PathVariable Long stickerId) {
        return ApiResponse.onSuccess(stickerCommandServiceImpl.deleteSticker(stickerId));
    }
}
