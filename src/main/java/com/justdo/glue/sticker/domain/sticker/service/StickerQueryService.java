package com.justdo.glue.sticker.domain.sticker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//TODO: 서버에 스티커 생성 결과 저장로직 구현해야함.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StickerQueryService {
}
