package com.justdo.glue.sticker.domain.membersticker.controller;

import com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse;
import com.justdo.glue.sticker.domain.membersticker.dto.MemberStickerResponse.*;
import com.justdo.glue.sticker.domain.membersticker.service.MemberStickerCommandService;
import com.justdo.glue.sticker.domain.membersticker.service.MemberStickerQueryService;
import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO;
import com.justdo.glue.sticker.global.response.ApiResponse;
import com.justdo.glue.sticker.global.utils.JwtProvider;
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
@RequestMapping("/stickers")
public class MemberStickerController {
    private final JwtProvider jwtProvider;
    private final MemberStickerQueryService memberStickerQueryService;

    @Operation(summary = "특정 사용자가 제작한 스티커 이미지 조회", description = "특정 사용자가 생성한 스티커를 리스트 형태로 조회합니다. 특정 사용자의 모든 스티커를 페이지 형태가 아닌 한꺼번에 불러올 때 사용하시면 됩니다.")
    @Parameter(name = "memberId", description = "사용자 Id, Path Variable입니다.", required = true, example = "1", in = ParameterIn.PATH)
    @GetMapping("/users")
    public ApiResponse<MemberStickerItems> getSpecificMemberStickers(HttpServletRequest request) {
        Long memberId = jwtProvider.getUserIdFromToken(request);

        return ApiResponse.onSuccess(memberStickerQueryService.getStickersByMemberId(memberId));
    }

    @Operation(summary = "사용자의 스티커 이미지 페이징 조회", description = "사용자가 생성한 스티커를 페이징 처리하여 조회합니다. 한꺼번에 사용자가 생성한 스티커를 불러오는 것이 아닌, 페이징 처리를 통해 불러오고 싶을 때 사용하시면 됩니다.")
    @Parameter(name = "memberId", description = "사용자 id, Query Parameter입니다.", required = true, example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", required = true, example = "0", in = ParameterIn.QUERY)
    @Parameter(name = "size", description = "페이지 크기, Query Parameter입니다.", required = true, example = "10", in = ParameterIn.QUERY)
    @GetMapping("/pages")
    public ApiResponse<Page<MemberStickerItems>> getSpecificMemberStickersPage(HttpServletRequest request,
                                                                               @RequestParam(name = "page") int page,
                                                                               @RequestParam(name = "size") int size) {
        Long memberId = jwtProvider.getUserIdFromToken(request);
        return ApiResponse.onSuccess(memberStickerQueryService.getPageStickersByMemberId(memberId, page, size));
    }
}
