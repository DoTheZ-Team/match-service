package com.justdo.glue.sticker.domain.poststicker.service;

import static com.justdo.glue.sticker.domain.poststicker.dto.PostStickerDTO.*;

import com.justdo.glue.sticker.domain.poststicker.dto.PostStickerRequest;
import java.util.List;

public interface PostStickerCommandService {

    PostStickerProc savePostSticker(PostStickerRequest postStickerRequest);

    void saveAllPostSticker(List<PostStickerRequest> postStickerRequests);
}