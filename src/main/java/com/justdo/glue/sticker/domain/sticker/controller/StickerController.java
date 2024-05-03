package com.justdo.glue.sticker.domain.sticker.controller;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.ImageProc;
import com.justdo.glue.sticker.domain.sticker.service.StickerCommandService;
import com.justdo.glue.sticker.global.response.ApiResponse;
import com.justdo.glue.sticker.global.utils.JwtProvider;
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
@RequestMapping("/sticker")
public class StickerController {

    private final JwtProvider jwtProvider;
    private final StickerCommandService stickerCommandService;

    //TODO: 달리 api 파라미터 값에 따른 parameter 추가 수정 필요
    //TODO: 사진 생성 횟수 초과시 막는 로직 필요
    @Operation(summary = "이미지 생성 요청", description = "사용자의 요청에 따라 이미지를 생성합니다.")
    @Parameter(name = "userId", description = "사용자 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @Parameter(name = "prompt", description = "이미지 생성에 사용될 키워드, Query Parameter입니다.", required = true, example = "cat", in = ParameterIn.QUERY)
    @Parameter(name = "style", description = "이미지 스타일, Query Parameter입니다.", required = false, example = "Emoticon", in = ParameterIn.QUERY)
    @PostMapping("/{userId}")
    public ApiResponse<ImageProc> createImage(HttpServletRequest request,
                                                              @PathVariable(name = "userId") Long userId,
                                                              @RequestParam(name = "prompt") String prompt,
                                                              @RequestParam(name = "style", required = false) String style) {

        Long requesterId = jwtProvider.getUserIdFromToken(request);

        return ApiResponse.onSuccess(stickerCommandService.createImage(requesterId, userId, prompt, style));
    }
}
