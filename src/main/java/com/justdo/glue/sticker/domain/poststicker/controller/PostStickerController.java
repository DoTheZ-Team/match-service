package com.justdo.glue.sticker.domain.poststicker.controller;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import com.justdo.glue.sticker.domain.poststicker.service.PostStickerCommandServiceImpl;
import com.justdo.glue.sticker.domain.poststicker.service.PostStickerQueryServiceImpl;
import com.justdo.glue.sticker.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sticker API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers/poststicker")
public class PostStickerController {

    private final PostStickerCommandServiceImpl postStickerCommandService;
    private final PostStickerQueryServiceImpl postStickerQueryService;

    @Operation(summary = "포스트 내 스티커 정보 저장", description = "사용자가 포스트를 업로드 할 시 스티커의 정보를 저장합니다.")
    @PostMapping("/create")
    public ApiResponse<PostStickerDTO.PostStickerItem> savePostSticker(@RequestBody PostStickerDTO.PostStickerItem postStickerRequest) {
        Long postId = postStickerRequest.getPostId();
        Long stickerId = postStickerRequest.getStickerId();
        Double xLocation = postStickerRequest.getXLocation();
        Double yLocation = postStickerRequest.getYLocation();
        Double width = postStickerRequest.getWidth();
        Double height = postStickerRequest.getHeight();
        Double angle = postStickerRequest.getAngle();

        return ApiResponse.onSuccess(postStickerCommandService.BuildPostSticker(postId, stickerId, xLocation, yLocation, width, height, angle));
    }


    @Operation(summary = "포스트 내 스티커 이미지 조회", description = "사용자가 업로드한 포스트의 스티커를 조회합니다.")
    @Parameter(name = "postStickerId", description = "포스트 내 저장된 개별 스티커 정보 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @GetMapping("/{poststickerId}")
    public ApiResponse<PostStickerDTO.PostStickerItem> getSticker(HttpServletRequest request,
                                                                  @PathVariable(name = "poststickerId") Long poststickerId) {

        return ApiResponse.onSuccess(postStickerQueryService.getPostStickerById(poststickerId));
    }

    @Operation(summary = "포스트 내 스티커 이미지 페이징 조회", description = "사용자가 업로드한 포스트의 스티커를 페이징 처리하여 조회합니다.")
    @Parameter(name = "postId", description = "포스트의 id, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", required = true, example = "0", in = ParameterIn.QUERY)
    @Parameter(name = "size", description = "페이지 크기, Query Parameter입니다.", required = true, example = "10", in = ParameterIn.QUERY)
    @GetMapping("/list")
    public ApiResponse<Page<PostStickerDTO.PostStickerItem>> getStickersByPostId(@RequestParam(name = "postId") Long postId,
                                                                                 @RequestParam(name = "page") int page,
                                                                                 @RequestParam(name = "size") int size) {
        return ApiResponse.onSuccess(postStickerQueryService.getPostStickersByPostId(postId, page, size));
    }
}