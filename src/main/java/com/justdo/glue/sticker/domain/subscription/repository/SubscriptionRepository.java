package com.justdo.glue.sticker.domain.subscription.repository;

import com.justdo.glue.sticker.domain.subscription.Subscription;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByFromMemberIdAndToBlogId(Long fromMemberId, Long toBlogId);

    List<Subscription> findAllByFromMemberIdAndStateIsTrue(Long fromMemberId, PageRequest pageRequest);

    List<Subscription> findAllByToBlogIdAndStateIsTrue(Long toBlogId, PageRequest pageRequest);
}
