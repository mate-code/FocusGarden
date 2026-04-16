package com.matecode.focusgarden.db.session;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {
    @PrimaryKey
    @NonNull
    private String id;
    private String categoryId;
    private String userID;
    private Long sessionStart;  // like: 1776333000000
    private Long sessionEnd;    // like: 1776333000000
    private boolean interrupted;

    public Session() {
        this.id = UUID.randomUUID().toString();
    }

    public Session(String categoryId, String userID, Long sessionStart, Long sessionEnd, boolean interrupted) {
        this();
        this.categoryId = categoryId;
        this.userID = userID;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.interrupted = interrupted;
    }

    // This constructor only for test purpose
    public Session(@NonNull String id, String categoryId, String userID, Long sessionStart, Long sessionEnd, boolean interrupted) {
        this.id = id;
        this.categoryId = categoryId;
        this.userID = userID;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.interrupted = interrupted;
    }

    @NonNull
    public String getId() {return id; }
    public String getCategoryId() { return categoryId; }
    public String getUserID() { return userID; }
    public Long getSessionStart() { return sessionStart; }
    public Long getSessionEnd() { return sessionEnd; }
    public boolean isInterrupted() { return interrupted; }


    public void setId(String id) { this.id = id; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public void setUserID(String userID) { this.userID = userID; }
    public void setSessionStart(Long sessionStart) { this.sessionStart = sessionStart; }
    public void setSessionEnd(Long sessionEnd) { this.sessionEnd = sessionEnd; }
    public void setInterrupted(boolean interrupted) { this.interrupted = interrupted; }

}
