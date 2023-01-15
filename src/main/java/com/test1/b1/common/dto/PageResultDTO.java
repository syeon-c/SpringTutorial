package com.test1.b1.common.dto;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public class PageResultDTO<E> {

    private List<E> dtoList;

    private int pageNum;

    private long totalPageNum, totalCount;

    private int pageSize;

    private int start, end;

    public PageResultDTO(List<E> dtoList, Pageable pageable, long totalPageNum, long totalCount) {
        this.dtoList = dtoList;
        this.pageNum = pageable.getPageNumber();
        this.totalPageNum = totalPageNum;
        this.totalCount = totalCount;
        this.pageSize = pageable.getPageSize();

        this.start = (int) ((Math.ceil(this.pageNum / 10.0)) * 10) - 9;
        int tmpEnd = (int) ((Math.ceil(this.pageNum / 10.0)) * 10);
        this.end = tmpEnd * pageSize > totalCount ?
                (int) (Math.ceil(totalCount/(double) pageSize)) : tmpEnd;
    }
}
