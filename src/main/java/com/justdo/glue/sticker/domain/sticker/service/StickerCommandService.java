package com.justdo.glue.sticker.domain.sticker.service;

import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerGenerationResult;
import com.justdo.glue.sticker.domain.sticker.repository.StickerRepository;
import com.theokanning.openai.image.CreateImageRequest;
import jakarta.annotation.Resource;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StickerCommandService {
    @Resource(name = "getOpenAiService")
    private final OpenAiService openAiService;
    private final StickerRepository stickerRepository;

    // sticker id, 프롬프트, 타입 넣으면 base64 data 반환
    public StickerGenerationResult generation(String stickerPrompt, String stickerType){
        return apiResponse(stickerPrompt, stickerType);
    }

    private StickerGenerationResult apiResponse(String stickerPrompt, String stickerType) {
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(stickerPrompt)
                .size("512x512")
                .n(1)
                .responseFormat("b64_json")
                .build();

        String b64 = openAiService.createImage(createImageRequest).getData().get(0).getB64Json();
        return StickerResponse.toEntity(stickerPrompt, stickerType, b64);
    }
}
