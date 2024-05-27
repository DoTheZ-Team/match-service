package com.justdo.glue.sticker.domain.basicSticker.controller;

import com.justdo.glue.sticker.domain.basicSticker.dto.BasicStickerDTO.*;
import com.justdo.glue.sticker.domain.basicSticker.service.BasicStickerQueryService;
import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.global.response.ApiResponse;
import com.justdo.glue.sticker.global.utils.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Basic-Sticker API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers")
public class BasicStickerController {
    private final BasicStickerQueryService basicStickerQueryService;

    @Operation(summary = "기본 스티커 이미지 페이징 조회", description =
            "기본 스티커를 페이징 처리하여 조회합니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", required = true, example = "0", in = ParameterIn.QUERY)
    @Parameter(name = "size", description = "페이지 크기, Query Parameter입니다.", required = true, example = "10", in = ParameterIn.QUERY)
    @GetMapping("/basics/pages")
    public ApiResponse<CustomPage<BasicStickerItems>> getBasicStickersPage(@RequestParam(name = "page") int page,
                                                                           @RequestParam(name = "size") int size) {
        return ApiResponse.onSuccess(basicStickerQueryService.getBasicStickersPage(page, size));
    }
}
