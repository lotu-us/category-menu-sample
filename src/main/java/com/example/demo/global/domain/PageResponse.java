package com.example.demo.global.domain;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse {
    private List content;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private boolean firstPage;
    private boolean lastPage;

    public PageResponse(Page page) {
        this.content = page.getContent();
        this.currentPage = page.getNumber()+1;
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();
        this.firstPage = page.isFirst();
        this.lastPage = page.isLast();
    }
}
