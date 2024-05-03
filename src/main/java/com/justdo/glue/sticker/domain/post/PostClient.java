package com.justdo.glue.sticker.domain.post;

import com.justdo.glue.sticker.domain.post.PostResponse.PostItemList;
import com.justdo.glue.sticker.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "post-service", url = "${application.config.posts-url}", configuration = {
    FeignConfig.class})
public interface PostClient {


    @PostMapping("/preview")
    PostItemList findSubscriptionFrom(@RequestBody List<Long> blogIdList, @RequestParam int page);

    @PostMapping("/preview/subscribers")
    PostItemList findSubscriptionTo(@RequestBody List<Long> memberIdList, @RequestParam int page);
}
