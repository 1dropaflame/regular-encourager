package com.dropaflame.encourage.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Encouragement {
    @Id
    long id;
    @Enumerated(EnumType.STRING)
    Category category;
    @Enumerated(EnumType.STRING)
    Tone tone;
    @Enumerated(EnumType.STRING)
    Topic topic;
    String message;

    public Encouragement(long id, Category category, Topic topic, Tone tone, String message) {
        this.id = id;
        this.category = category;
        this.tone = tone;
        this.topic = topic;
        this.message = message;
    }

    protected Encouragement() {
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public Tone getTone() {
        return tone;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }

    public long id() {
        return id;
    }

    public Encouragement setId(long id) {
        this.id = id;
        return this;
    }

    public Category category() {
        return category;
    }

    public Encouragement setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Tone tone() {
        return tone;
    }

    public Encouragement setTone(Tone tone) {
        this.tone = tone;
        return this;
    }

    public Topic topic() {
        return topic;
    }

    public Encouragement setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public String message() {
        return message;
    }

    public Encouragement setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Encouragement{" +
                "id=" + id +
                ", category=" + category +
                ", tone=" + tone +
                ", topic=" + topic +
                ", message='" + message + '\'' +
                '}';
    }
}
