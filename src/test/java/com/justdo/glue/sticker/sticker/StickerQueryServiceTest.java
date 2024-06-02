package com.justdo.glue.sticker.sticker;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;
import com.justdo.glue.sticker.domain.sticker.repository.StickerRepository;
import com.justdo.glue.sticker.domain.sticker.service.*;
import com.justdo.glue.sticker.domain.userSticker.service.*;
import com.justdo.glue.sticker.global.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class StickerQueryServiceTest {

    private StickerQueryServiceImpl stickerQueryService;

    @Mock
    private StickerRepository stickerRepository;

    @Mock
    private UserStickerCommandService userStickerCommandService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        stickerQueryService = new StickerQueryServiceImpl(stickerRepository, userStickerCommandService);
    }

    @Test
    void testGetStickerById() {
        Sticker sticker = Sticker.builder().id(1L).url("http://example.com/image.png").prompt("cat").build();
        when(stickerRepository.findById(anyLong())).thenReturn(Optional.of(sticker));

        StickerItem result = stickerQueryService.getStickerById(1L);
        assertEquals(1L, result.getStickerId());
        assertEquals("http://example.com/image.png", result.getUrl());
        assertEquals("cat", result.getPrompt());
    }

    @Test
    void testGetStickerById_NotFound() {
        when(stickerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> stickerQueryService.getStickerById(1L));
    }

    @Test
    void testSaveSticker() {
        Sticker sticker = Sticker.builder().id(1L).url("http://example.com/image.png").prompt("cat").build();
        when(stickerRepository.save(any(Sticker.class))).thenReturn(sticker);

        StickerItem result = stickerQueryService.saveSticker(sticker, 1L);
        assertEquals(1L, result.getStickerId());
        assertEquals("http://example.com/image.png", result.getUrl());
        assertEquals("cat", result.getPrompt());
    }

    @Test
    void testDeleteSticker() {
        Sticker sticker = Sticker.builder().id(100L).url("http://example.com/image.png").prompt("cat").build();
        when(stickerRepository.findById(anyLong())).thenReturn(Optional.of(sticker));
        doNothing().when(stickerRepository).delete(any(Sticker.class));

        stickerQueryService.deleteSticker(sticker, 100L);
        verify(stickerRepository, times(1)).delete(sticker);
    }
}
