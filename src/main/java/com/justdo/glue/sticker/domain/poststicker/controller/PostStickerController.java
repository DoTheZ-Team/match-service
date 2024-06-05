package com.justdo.glue.sticker.domain.poststicker.controller;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO.PostStickerItem;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerRequest;
import com.justdo.glue.sticker.domain.poststicker.service.PostStickerCommandServiceImpl;
import com.justdo.glue.sticker.domain.poststicker.service.PostStickerQueryServiceImpl;
import com.justdo.glue.sticker.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post-Sticker API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers")
public class PostStickerController {

    private final PostStickerCommandServiceImpl postStickerCommandService;
    private final PostStickerQueryServiceImpl postStickerQueryService;

    @Operation(summary = "포스트 내 스티커 개별 정보 저장 - 스티커를 한개씩 저장할 때 사용합니다.", description =
            "사용자가 포스트를 업로드 할 시 스티커의 정보를 저장합니다.\n" +
                    "업로드 된 포스트 내 스티커의 위치정보, 크기, 각도 정보를 저장하기 위해 사용합니다.")
    @PostMapping("/post")
    public ApiResponse<PostStickerDTO.PostStickerProc> savePostSticker(
            @RequestBody PostStickerRequest postStickerRequest) {

        return ApiResponse.onSuccess(postStickerCommandService.savePostSticker(postStickerRequest));
    }

    @Operation(summary = "포스트 내 스티커 전체 정보 저장 - Open Feign을 통해 post에서 사용되는 API입니다.", description =
            "사용자가 포스트를 업로드 할 시 스티커의 모든 정보를 저장합니다.\n" +
                    "업로드 된 포스트 내 스티커의 위치정보, 크기, 각도 정보를 저장하기 위해 사용합니다.")
    @PostMapping("/post-list")
    public void savePostStickers(@RequestBody List<PostStickerRequest> postStickerRequests) {

        postStickerCommandService.saveAllPostSticker(postStickerRequests);

    }

    @Operation(summary = "포스트 첨부 스티커 이미지 조회 - Open Feign을 통해 post에서 사용되는 API입니다.", description =
            "사용자가 업로드한 포스트의 내부에 첨부된 스티커를 전체 조회합니다.\n" +
                    "포스트 아이디를 입력하면 해당 포스트의 모든 스티커가 표출됩니다.")
    @Parameter(name = "postId", description = "포스트 id, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @GetMapping("/poststickers")
    public List<PostStickerItem> getStickersByPostId(@RequestParam(name = "postId") Long postId) {
        return postStickerQueryService.getPostStickersByPostId(postId);
    }
}