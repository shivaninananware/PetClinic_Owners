package com.fedex.apicommon.owner;

import com.fedex.apicommon.owner.exception.InvalidResponseException;

public interface ApiResponse<T> {
    T getContent() throws InvalidResponseException;

    Integer getHttpStatusCode();
}
