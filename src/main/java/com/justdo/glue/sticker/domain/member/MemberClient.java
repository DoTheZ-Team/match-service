package com.justdo.glue.sticker.domain.member;

import com.justdo.glue.sticker.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "member-service", url = "${application.config.members-url}", configuration = {
    FeignConfig.class})
public interface MemberClient {

    @GetMapping
    MemberDTO findMember();

    @PutMapping
    void updateMember(@RequestBody MemberDTO memberDTO);

    @PostMapping("/blogs")
    List<String> findMemberNicknames(@RequestBody List<Long> memberIdList);
}
