package com.justdo.glue.sticker.domain.userSticker.controller;

import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.domain.userSticker.UserSticker;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse.*;
import com.justdo.glue.sticker.domain.userSticker.service.UserStickerQueryService;
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


@Tag(name = "User-Sticker API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers")
public class UserStickerController {
    private final JwtProvider jwtProvider;
    private final UserStickerQueryService userStickerQueryService;

    @Operation(summary = "특정 사용자가 제작한 스티커 이미지 조회", description =
            "JWT 토큰 값을 이용해 접속한 사용자가 생성한 스티커를 리스트 형태로 조회합니다." +
                    "특정 사용자의 모든 스티커를 한꺼번에 전부 불러올 때 사용하시면 됩니다.")
    @GetMapping("/users")
    public ApiResponse<UserStickerItems> getSpecificUserStickers(HttpServletRequest request) {
        Long userId = jwtProvider.getUserIdFromToken(request);
        System.out.println(userId);

        return ApiResponse.onSuccess(userStickerQueryService.getStickersByUserId(userId));
    }

    @Operation(summary = "사용자가 생성해둔 스티커 이미지 페이징 조회", description =
            "사용자가 생성한 스티커를 페이징 처리하여 조회합니다. " +
                    "한꺼번에 사용자가 생성한 스티커를 불러오는 것이 아닌, 페이징 처리를 통해 불러오고 싶을 때 사용하시면 됩니다.")
    @Parameter(name = "page", description = "페이지 번호, Query Parameter입니다.", required = true, example = "0", in = ParameterIn.QUERY)
    @Parameter(name = "size", description = "페이지 크기, Query Parameter입니다.", required = true, example = "10", in = ParameterIn.QUERY)
    @GetMapping("/users/pages")
    public ApiResponse<CustomPage<UserSticker, UserStickerResponse.UserStickerItems>> getSpecificUserStickersPage(HttpServletRequest request,
                                                                                                                  @RequestParam(name = "page") int page,
                                                                                                                  @RequestParam(name = "size") int size) {
        Long userId = jwtProvider.getUserIdFromToken(request);
        return ApiResponse.onSuccess(userStickerQueryService.getPageStickersByUserId(userId, page, size));
    }
}
