//package com.justdo.glue.sticker.domain.sticker.service;
//
//import com.justdo.glue.sticker.domain.sticker.Sticker;
//import com.justdo.glue.sticker.domain.sticker.dto.SubscriptionResponse;
//import com.justdo.glue.sticker.domain.subscription.dto.SubscriptionResponse.SubscriptionProc;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SubscriptionCommandService {
//
//    private final SubscriptionRepository subscriptionRepository;
//
//    public SubscriptionProc subscribe(Long memberId, Long blogId) {
//        return getSubscription(memberId, blogId)
//            .map(this::updateSubscription)
//            .orElseGet(() -> createSubscription(memberId, blogId)); // Create new
//    }
//
//    private SubscriptionProc updateSubscription(Sticker existSub) {
//        existSub.changeState();
//        return SubscriptionResponse.toSubscriptionProc(existSub);
//    }
//
//    private SubscriptionProc createSubscription(Long memberId, Long blogId) {
//        Sticker newSub = SubscriptionResponse.toEntity(memberId, blogId);
//        save(newSub);
//        return SubscriptionResponse.toSubscriptionProc(newSub);
//    }
//
//    private void save(Sticker sticker) {
//        subscriptionRepository.save(sticker);
//    }
//
//    public Optional<Sticker> getSubscription(Long memberId, Long blogId) {
//        return subscriptionRepository.findByFromMemberIdAndToBlogId(memberId, blogId);
//    }
//}
