package com.justdo.glue.sticker.domain.sticker.service;

import com.justdo.glue.sticker.domain.sticker.Sticker;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerGenerationResult;
import com.justdo.glue.sticker.domain.sticker.repository.StickerRepository;
import com.theokanning.openai.image.CreateImageRequest;
import jakarta.annotation.Resource;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.SchemaOutputResolver;

import static com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StickerCommandService {
    @Resource(name = "getOpenAiService")
    private final OpenAiService openAiService;
    private final StickerRepository stickerRepository;
    private final StickerQueryService stickerQueryService;

    //TODO: 생성한 스티커 저장 로직 제대로 구현 안됨
    public StickerGenerationResult generateAndSaveSticker(String stickerPrompt, String stickerType) {
        StickerGenerationResult generatedResult = generation(stickerPrompt, stickerType);

        Sticker newSticker = new Sticker();
        newSticker.setUrl(generatedResult.getStickerUrl());
        saveSticker(newSticker);

        return generatedResult;
    }

    private StickerGenerationResult generation(String stickerPrompt, String stickerType) {
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(stickerPrompt)
                .size("512x512")
                .n(1)
                .responseFormat("b64_json")
                .build();

        String b64 = openAiService.createImage(createImageRequest).getData().get(0).getB64Json();
        return StickerResponse.toStickerGenerationResult(stickerPrompt, stickerType, b64);
    }

    private StickerItem saveSticker(Sticker sticker) {
        StickerItem savedSticker = stickerQueryService.saveSticker(sticker);
        return toStickerItem(savedSticker.getStickerId(), savedSticker.getUrl());
    }

}
