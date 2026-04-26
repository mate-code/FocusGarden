package com.matecode.focusgarden.db.session;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SessionWithCategory {
    @PrimaryKey
    @NonNull
    private Long sessionStart;  // like: 1776333000000
    private Long sessionEnd;    // like: 1776333000000
    private String categoryName;
    private int categoryColor;  // like: 0xFFFF0000;

    public SessionWithCategory() {}
    public Long getSessionStart() { return sessionStart; }
    public Long getSessionEnd() { return sessionEnd; }
    public String getCategoryName() { return categoryName; }
    public int getCategoryColor() { return categoryColor; }

    public void setSessionStart(@NonNull Long sessionStart) { this.sessionStart = sessionStart; }
    public void setSessionEnd(Long sessionEnd) { this.sessionEnd = sessionEnd; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setCategoryColor(int categoryColor) { this.categoryColor = categoryColor; }
}
