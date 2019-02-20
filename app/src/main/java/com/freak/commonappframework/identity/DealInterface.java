package com.freak.commonappframework.identity;

/**
 *
 * @author clint
 * @date 2017/9/23
 */

public interface DealInterface<T> {
    void success(T object);
    void failure(String error);
}