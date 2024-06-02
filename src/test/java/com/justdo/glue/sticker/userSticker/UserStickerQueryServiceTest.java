package com.justdo.glue.sticker.userSticker;

import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;
import com.justdo.glue.sticker.domain.sticker.service.StickerQueryService;
import com.justdo.glue.sticker.domain.userSticker.UserSticker;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse.UserStickerItems;
import com.justdo.glue.sticker.domain.userSticker.repository.UserStickerRepository;
import com.justdo.glue.sticker.domain.userSticker.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserStickerQueryServiceTest {

    private UserStickerQueryService userStickerQueryService;

    @Mock
    private UserStickerRepository userStickerRepository;

    @Mock
    private StickerQueryService stickerQueryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userStickerQueryService = new UserStickerQueryServiceImpl(userStickerRepository, stickerQueryService);
    }

    @Test
    void testGetStickersByUserId() {
        UserSticker userSticker = UserSticker.builder().userId(1L).stickerId(1L).build();
        StickerItem stickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");

        when(userStickerRepository.findByUserId(anyLong())).thenReturn(List.of(userSticker));
        when(stickerQueryService.getStickerById(anyLong())).thenReturn(stickerItem);

        UserStickerItems result = userStickerQueryService.getStickersByUserId(1L);
        assertEquals(1L, result.getUserId());
        assertEquals(1L, result.getStickers().get(0).getStickerId());
    }

    @Test
    void testGetPageStickersByUserId() {
        UserSticker userSticker = UserSticker.builder().userId(1L).stickerId(1L).build();
        StickerItem stickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<UserSticker> userStickerPage = new PageImpl<>(List.of(userSticker), pageable, 1);

        when(userStickerRepository.findByUserId(anyLong(), any(Pageable.class))).thenReturn(userStickerPage);
        when(stickerQueryService.getStickerById(anyLong())).thenReturn(stickerItem);

        CustomPage<UserStickerItems> result = userStickerQueryService.getPageStickersByUserId(1L, 0, 10);
        assertEquals(1L, result.getContent().get(0).getUserId());
        assertEquals(1L, result.getContent().get(0).getStickers().get(0).getStickerId());
    }
}

