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

import static com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StickerCommandServiceImpl implements StickerCommandService {
    @Resource(name = "getOpenAiService")
    private final OpenAiService openAiService;
    private final StickerRepository stickerRepository;
    private final StickerQueryServiceImpl stickerQueryServiceImpl;

    //TODO: 생성한 스티커 저장 로직 제대로 구현 안됨
    @Override
    public StickerItem generateAndSaveSticker(String stickerPrompt, String stickerType) {
        StickerGenerationResult generatedResult = generation(stickerPrompt, stickerType);

        Sticker newSticker = Sticker.builder()
                .url(generatedResult.getStickerUrl())
                .prompt(generatedResult.getStickerPrompt())
                .style(generatedResult.getStickerType())
                .build();

        return saveSticker(newSticker);
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
        StickerItem savedSticker = stickerQueryServiceImpl.saveSticker(sticker);
        return toStickerItem(savedSticker.getStickerId(), savedSticker.getUrl(), savedSticker.getPrompt(), savedSticker.getStyle());
    }

}