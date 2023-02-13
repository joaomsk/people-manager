package com.joaomeleski.peoplemanager.util;

import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
public class PartialResult<T> {

    private final T data;
    private final HttpHeaders headers;

    public PartialResult(T data, int pageNumber, int pageSize, long totalElements) {
        this.data = data;
        this.headers = new HttpHeaders();
        headers.set("Accept-Ranges", "items");
        headers.set("Content-Range", "items " + pageNumber + "-" + (pageNumber + pageSize - 1) + "/" + totalElements);
    }
}
