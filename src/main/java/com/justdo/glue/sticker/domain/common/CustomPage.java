package com.justdo.glue.sticker.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public class CustomPage<T, C> {
    private C content;
    private boolean isFirst;
    private boolean isLast;
    private int totalPage;
    private long totalElements;
    private int size;
    private int currPage;
    private boolean hasNext;

    public CustomPage(Page<T> page, C content) {
        this.content = content;
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.size = page.getSize();
        this.currPage = page.getNumber();
        this.hasNext = page.hasNext();
    }

    @JsonProperty("content")
    public C getContent() {
        return content;
    }

    @JsonProperty("isFirst")
    public boolean isFirst() {
        return isFirst;
    }

    @JsonProperty("isLast")
    public boolean isLast() {
        return isLast;
    }

    @JsonProperty("totalPage")
    public int getTotalPage() {
        return totalPage;
    }

    @JsonProperty("totalElements")
    public long getTotalElements() {
        return totalElements;
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    @JsonProperty("number")
    public int getCurrPage() {
        return currPage;
    }

    @JsonProperty("hasNext")
    public boolean isHasNext() {
        return hasNext;
    }
}
