package com.justdo.glue.sticker.userSticker;

import com.justdo.glue.sticker.domain.common.CustomPage;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerItem;
import com.justdo.glue.sticker.domain.userSticker.dto.UserStickerResponse.UserStickerItems;
import com.justdo.glue.sticker.domain.userSticker.service.UserStickerQueryService;
import com.justdo.glue.sticker.global.utils.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserStickerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserStickerQueryService userStickerQueryService;

    @Mock
    private JwtProvider jwtProvider;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String MASTER_TOKEN = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhY2Nlc3NUb2tlbiIsImV4cCI6MTc0NzMwMTI1NSwibWVtYmVySWQiOjF9.lTc9rlnI8yiuVw8RliqYGxsRXu2MwUO-3KdyhCVptkPzTfsTxi8TR-wy4Se31zfF";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetSpecificUserStickers() throws Exception {
        StickerItem stickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");
        UserStickerItems userStickerItems = new UserStickerItems(1L, List.of(stickerItem));

        when(jwtProvider.getUserIdFromToken(any())).thenReturn(1L);
        when(userStickerQueryService.getStickersByUserId(anyLong())).thenReturn(userStickerItems);

        mockMvc.perform(get("/stickers/users")
                        .header("Authorization", "Bearer " + MASTER_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value(1L))
                .andExpect(jsonPath("$.result.stickers[0].stickerId").value(1L))
                .andExpect(jsonPath("$.result.stickers[0].url").value("https://glue-bucket-sticker.s3.ap-northeast-2.amazonaws.com/036adc2e-00de-4689-a044-27f52aafcd35.png"))
                .andExpect(jsonPath("$.result.stickers[0].prompt").value("귀여운 고양이"));
    }

    @Test
    void testGetSpecificUserStickersPage() throws Exception {
        StickerItem stickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");
        UserStickerItems userStickerItems = new UserStickerItems(1L, List.of(stickerItem));
        CustomPage<UserStickerItems> customPage = new CustomPage<>(new org.springframework.data.domain.PageImpl<>(List.of(userStickerItems)));

        when(jwtProvider.getUserIdFromToken(any())).thenReturn(1L);
        when(userStickerQueryService.getPageStickersByUserId(anyLong(), anyInt(), anyInt())).thenReturn(customPage);

        mockMvc.perform(get("/stickers/users/pages")
                        .header("Authorization", "Bearer " + MASTER_TOKEN)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content[0].userId").value(1L))
                .andExpect(jsonPath("$.result.content[0].stickers[0].stickerId").value(1L))
                .andExpect(jsonPath("$.result.content[0].stickers[0].url").value("https://glue-bucket-sticker.s3.ap-northeast-2.amazonaws.com/036adc2e-00de-4689-a044-27f52aafcd35.png"))
                .andExpect(jsonPath("$.result.content[0].stickers[0].prompt").value("귀여운 고양이"));
    }
}
