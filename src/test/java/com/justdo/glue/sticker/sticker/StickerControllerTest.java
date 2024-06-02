package com.justdo.glue.sticker.sticker;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;
import com.justdo.glue.sticker.domain.sticker.service.StickerCommandService;
import com.justdo.glue.sticker.domain.sticker.service.StickerQueryService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StickerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StickerCommandService stickerCommandService;

    @Mock
    private StickerQueryService stickerQueryService;

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
    void testCreateImage() throws Exception {
        StickerItem mockStickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");
        when(jwtProvider.getUserIdFromToken(any())).thenReturn(1L);
        when(stickerCommandService.generateAndSaveSticker(anyString(), anyLong())).thenReturn(mockStickerItem);

        mockMvc.perform(post("/stickers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + MASTER_TOKEN)
                        .content("cat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.prompt").value("Create an emoji in the style of Apple's emojis, themed around 'cat'. The emoji should be cute, illustrated (not realistic), and without thick outlines. It is crucial that the entire background of the emoji, excluding the emoji itself, is plain white. Design the emoji with a 1:1 aspect ratio."));
    }

    @Test
    void testGetSticker() throws Exception {
        StickerItem mockStickerItem = new StickerItem(1L, "http://example.com/image.png", "cat");
        when(stickerQueryService.getStickerById(anyLong())).thenReturn(mockStickerItem);

        MvcResult result = mockMvc.perform(get("/stickers/1")
                        .header("Authorization", "Bearer " + MASTER_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.stickerId").value(1L))
                .andExpect(jsonPath("$.result.url").value("https://glue-bucket-sticker.s3.ap-northeast-2.amazonaws.com/036adc2e-00de-4689-a044-27f52aafcd35.png"))
                .andExpect(jsonPath("$.result.prompt").value("귀여운 고양이"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testDeleteSticker() throws Exception {
        when(jwtProvider.getUserIdFromToken(any())).thenReturn(1L);
        when(stickerCommandService.deleteSticker(anyLong(), anyLong())).thenReturn("스티커가 성공적으로 삭제되었습니다.");

        mockMvc.perform(delete("/stickers/1")
                        .header("Authorization", "Bearer " + MASTER_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("스티커가 성공적으로 삭제되었습니다."));
    }
}