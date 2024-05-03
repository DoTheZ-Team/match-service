package com.justdo.glue.sticker.domain.blog.repository;

import com.justdo.glue.sticker.domain.blog.Blog;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<com.justdo.glue.sticker.domain.blog.Blog, Long> {

    @Query("SELECT b FROM Blog b WHERE b.id in :blogIdList")
    Slice<com.justdo.glue.sticker.domain.blog.Blog> findBlogIdList(List<Long> blogIdList, PageRequest pageRequest);

    @Query("SELECT b FROM Blog b WHERE b.memberId in :memberIdList")
    Slice<com.justdo.glue.sticker.domain.blog.Blog> findSubscriberIdList(List<Long> memberIdList, PageRequest pageRequest);

    com.justdo.glue.sticker.domain.blog.Blog findByMemberId(Long memberId);
}
