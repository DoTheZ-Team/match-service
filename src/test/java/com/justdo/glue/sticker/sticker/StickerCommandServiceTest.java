package com.justdo.glue.sticker.sticker;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;
import com.justdo.glue.sticker.domain.sticker.repository.StickerRepository;
import com.justdo.glue.sticker.domain.sticker.service.*;
import com.justdo.glue.sticker.global.exception.ApiException;
import com.justdo.glue.sticker.global.response.code.status.ErrorStatus;
import com.justdo.glue.sticker.global.s3.S3Service;
import com.theokanning.openai.service.OpenAiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StickerCommandServiceTest {

    private StickerCommandService stickerCommandService;

    @Mock
    private StickerRepository stickerRepository;

    @Mock
    private StickerQueryService stickerQueryService;

    @Mock
    private S3Service s3Service;

    @Mock
    private OpenAiService openAiService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        stickerCommandService = new StickerCommandServiceImpl(
                openAiService, stickerQueryService, s3Service, stickerRepository);
    }

    @Test
    void testDeleteSticker() {
        StickerItem mockStickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");

        when(stickerQueryService.getStickerById(anyLong())).thenReturn(mockStickerItem);
        doNothing().when(stickerRepository).delete(any(Sticker.class));

        String result = stickerCommandService.deleteSticker(1L, 1L);
        assertEquals("스티커가 성공적으로 삭제되었습니다.", result);
    }

    @Test
    void testDeleteSticker_NotFound() {
        when(stickerQueryService.getStickerById(anyLong())).thenThrow(new ApiException(ErrorStatus._STICKER_NOT_FOUND));

        assertThrows(ApiException.class, () -> stickerCommandService.deleteSticker(1L, 1L));
    }
}
