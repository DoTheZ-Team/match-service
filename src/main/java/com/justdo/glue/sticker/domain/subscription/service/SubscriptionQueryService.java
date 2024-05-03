package com.justdo.glue.sticker.domain.subscription.service;

import com.justdo.glue.sticker.domain.subscription.Subscription;
import com.justdo.glue.sticker.domain.subscription.repository.SubscriptionRepository;
import com.justdo.glue.sticker.domain.post.PostClient;
import com.justdo.glue.sticker.domain.post.PostResponse.PostItemList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionQueryService {

    private final PostClient postClient;
    private final SubscriptionRepository subscriptionRepository;

    public PostItemList getSubscriptionPostFrom(Long memberId, int page) {

        List<Long> blogIdList = getSubscriptionBlogIdList(memberId, page);

        return postClient.findSubscriptionFrom(blogIdList, page);
    }

    // 나를 구독하는 블로그의 포스트 정보 조회
    public PostItemList getSubscriptionPostTo(Long blogId, int page) {

        List<Long> subscriberIdList = getSubscriberBlogIdList(blogId, page);

        return postClient.findSubscriptionTo(subscriberIdList, page);
    }

    // 내가 구독하는 블로그 사용자의 아이디 목록 조회
    public List<Long> getSubscriptionBlogIdList(Long memberId, int page) {

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createdAt"));

        return subscriptionRepository.findAllByFromMemberIdAndStateIsTrue(memberId, pageRequest)
            .stream()
            .map(Subscription::getToBlogId)
            .toList();
    }

    // 나를 구독하는 블로그 사용자의 아이디 목록 조회
    public List<Long> getSubscriberBlogIdList(Long blogId, int page) {

        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createdAt"));
        return subscriptionRepository.findAllByToBlogIdAndStateIsTrue(blogId, pageRequest)
            .stream()
            .map(Subscription::getFromMemberId)
            .toList();

    }
}
