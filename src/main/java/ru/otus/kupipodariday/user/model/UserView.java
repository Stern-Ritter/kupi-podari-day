package ru.otus.kupipodariday.user.model;

import java.time.LocalDateTime;

public interface UserView {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getUsername();
    String getAbout();
    String getAvatar();
    String getEmail();
}