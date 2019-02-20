package com.freak.commonappframework.event;

/**
 * @author freak
 * @date 2019/2/19
 */
public class UserEvent {

    long id;
    String name;

    public UserEvent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}