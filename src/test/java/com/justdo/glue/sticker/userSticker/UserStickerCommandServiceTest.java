package com.justdo.glue.sticker.userSticker;

import com.justdo.glue.sticker.domain.userSticker.UserSticker;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse;
import com.justdo.glue.sticker.domain.userSticker.repository.UserStickerRepository;
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

public class UserStickerCommandServiceTest {

    private UserStickerCommandService userStickerCommandService;

    @Mock
    private UserStickerRepository userStickerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userStickerCommandService = new UserStickerCommandServiceImpl(userStickerRepository);
    }

    @Test
    void testSaveUserSticker() {
        UserSticker userSticker = UserSticker.builder().userId(100L).stickerId(100L).build();
        when(userStickerRepository.save(any(UserSticker.class))).thenReturn(userSticker);

        UserStickerResponse.UserStickerItem result = userStickerCommandService.saveUserSticker(100L, 100L);
        assertEquals(1L, result.getUserId());
        assertEquals(1L, result.getStickerId());
    }

    @Test
    void testDeleteUserSticker() {
        UserSticker userSticker = UserSticker.builder().userId(100L).stickerId(100L).build();
        when(userStickerRepository.findByStickerIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(userSticker));
        doNothing().when(userStickerRepository).delete(any(UserSticker.class));

        userStickerCommandService.deleteUserSticker(100L, 100L);
        verify(userStickerRepository, times(1)).delete(userSticker);
    }

    @Test
    void testDeleteUserSticker_NotFound() {
        when(userStickerRepository.findByStickerIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> userStickerCommandService.deleteUserSticker(1L, 1L));
    }
}

