package com.matecode.focusgarden.db.category;

import androidx.room.PrimaryKey;

import java.util.UUID;

public class Category {
    @PrimaryKey
    private String id;
    private String name;
    private int color;  // like: 0xFFFF0000;

    public Category () {
        this.id = UUID.randomUUID().toString();
    }
}
