package com.justdo.glue.sticker.domain.poststicker.controller;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerResponse;
import com.justdo.glue.sticker.domain.poststicker.service.PostStickerCommandServiceImpl;
import com.justdo.glue.sticker.domain.poststicker.service.PostStickerQueryServiceImpl;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
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
@RequestMapping("/stickers/poststicker")
public class PostStickerController {

    private final PostStickerCommandServiceImpl postStickerCommandService;
    private final PostStickerQueryServiceImpl postStickerQueryService;

    @Operation(summary = "포스트 내 스티커 정보 저장", description = "사용자가 포스트를 업로드 할 시 스티커의 정보를 저장합니다.")
    @Parameter(name = "postId", description = "포스트의 id, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "stickerId", description = "스티커의 id, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "xLocation", description = "스티커의 x 위치, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "yLocation", description = "스티커의 y 위치, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "width", description = "스티커의 width, Query Parameter입니다.", required = true, example = "10", in = ParameterIn.QUERY)
    @Parameter(name = "height", description = "스티커의 height, Query Parameter입니다.", required = true, example = "10", in = ParameterIn.QUERY)
    @Parameter(name = "angle", description = "스티커의 angle, Query Parameter입니다.", required = true, example = "0", in = ParameterIn.QUERY)
    @PostMapping("/create")
    public ApiResponse<PostStickerResponse.PostStickerItem> savePostSticker(HttpServletRequest request,
                                                                        @RequestParam(name = "postId") Long post_id,
                                                                        @RequestParam(name = "stickerId") Long sticker_id,
                                                                        @RequestParam(name = "xLocation") Double x_location,
                                                                        @RequestParam(name = "yLocation") Double y_location,
                                                                        @RequestParam(name = "width") Double width,
                                                                        @RequestParam(name = "height") Double height,
                                                                        @RequestParam(name = "angle") Double angle) {

        return ApiResponse.onSuccess(postStickerCommandService.BuildPostSticker(post_id, sticker_id, x_location, y_location, width, height, angle));
    }

    @Operation(summary = "포스트 내 스티커 이미지 조회", description = "사용자가 업로드한 포스트의 스티커를 조회합니다.")
    @Parameter(name = "postStickerId", description = "포스트 내 저장된 스티커 정보 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @GetMapping("/{poststickerId}")
    public ApiResponse<PostStickerResponse.PostStickerItem> getSticker(HttpServletRequest request,
                                                               @PathVariable(name = "poststickerId") Long poststickerId) {

        return ApiResponse.onSuccess(postStickerQueryService.getPostStickerById(poststickerId));
    }
}