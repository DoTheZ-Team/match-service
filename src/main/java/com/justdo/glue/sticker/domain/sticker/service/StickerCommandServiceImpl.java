package com.justdo.glue.sticker.domain.sticker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justdo.glue.sticker.domain.sticker.Sticker;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse;
import com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.StickerGenerationResult;
import com.justdo.glue.sticker.domain.sticker.repository.StickerRepository;
import com.justdo.glue.sticker.global.exception.ApiException;
import com.justdo.glue.sticker.global.response.ApiResponse;
import com.justdo.glue.sticker.global.s3.S3Service;
import com.theokanning.openai.image.CreateImageRequest;
import jakarta.annotation.Resource;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.justdo.glue.sticker.domain.sticker.dto.StickerResponse.*;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._INTERNAL_SERVER_ERROR;
import static com.justdo.glue.sticker.global.response.code.status.ErrorStatus._STICKER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
@Configuration
public class StickerCommandServiceImpl implements StickerCommandService {
    @Value("${api-key}")
    private String OPENAI_API_KEY;

    @Resource(name = "getOpenAiService")
    private final OpenAiService openAiService;
    private final StickerQueryService stickerQueryService;
    private final S3Service s3Service;
    private final StickerRepository stickerRepository;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/images/generations";

    @Override
    public StickerItem generateAndSaveSticker(String stickerPrompt, Long memberId) {
        StickerGenerationResult generatedResult = generation(stickerPrompt);

        Sticker newSticker = Sticker.builder()
                .url(generatedResult.getStickerUrl())
                .prompt(generatedResult.getStickerPrompt())
                .build();

        return saveSticker(newSticker, memberId);
    }

    // DALL-E API를 통해 이미지를 생성하고 S3에 업로드 후 URL 반환
    private StickerGenerationResult generation(String stickerPrompt) {

        stickerPrompt = "Create an emoji in the style of Apple's emojis, themed around '" + stickerPrompt +
                "'. The emoji should be cute, illustrated (not realistic), and without thick outlines. " +
                "It is crucial that the entire background of the emoji, excluding the emoji itself, is plain white. " +
                "Design the emoji with a 1:1 aspect ratio.";

        try {
            String b64Image = generateImage(stickerPrompt);
            String s3Url = s3Service.uploadBase64Image(b64Image, "generated-sticker.png");
            return toStickerGenerationResult(stickerPrompt, s3Url);
        } catch (Exception e) {
            throw new ApiException(_INTERNAL_SERVER_ERROR);
        }
    }

    // OpenAI API를 호출하여 이미지를 생성하는 메서드
    private String generateImage(String prompt) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String requestBody = objectMapper.writeValueAsString(Map.of(
                "prompt", prompt,
                "model", "dall-e-3",
                "n", 1,
                "response_format", "b64_json",
                "size", "1024x1024"
        ));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENAI_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);

        if (response.statusCode() != 200)
            throw new RuntimeException("Failed to generate image: " + response.body());

        Map<String, Object> responseMap = objectMapper.readValue(response.body(), Map.class);
        List<Map<String, String>> data = (List<Map<String, String>>) responseMap.get("data");
        return data.get(0).get("b64_json");
    }

    private StickerItem saveSticker(Sticker sticker, Long memberId) {
        StickerItem savedSticker = stickerQueryService.saveSticker(sticker, memberId);
        return toStickerItem(savedSticker.getStickerId(), savedSticker.getUrl(), savedSticker.getPrompt());
    }

    // 스티커와 관련된 이미지 삭제
    @Transactional
    public String deleteSticker(Long stickerId, Long memberId) {
        StickerItem sticker = stickerQueryService.getStickerById(stickerId);

        Sticker deleteSticker = Sticker.builder()
                .url(sticker.getUrl())
                .prompt(sticker.getPrompt())
                .build();

        stickerQueryService.deleteSticker(deleteSticker, memberId);
        return "스티커가 성공적으로 삭제되었습니다.";
    }
}