package com.freak.neteasecloudmusic.identity;


/**
 * @author freak
 * @date 2019/2/19
 */

public interface DealInterface<T> {
    void success(T object);

    void failure(String error);
}