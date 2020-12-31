package api.common;

import api.common.exception.InvalidResponseException;

public interface ApiResponse<T> {
    T getContent() throws InvalidResponseException;

    Integer getHttpStatusCode();
}
