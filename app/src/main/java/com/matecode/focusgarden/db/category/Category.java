package com.matecode.focusgarden.db.category;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.util.UUID;

public class Category {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private int color;  // like: 0xFFFF0000;

    public Category() {
        this.id = UUID.randomUUID().toString();
    }

    public Category(String name, int color) {
        this();
        this.name = name;
        this.color = color;
    }

    // This constructor only for test purpose
    public Category(String id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }


    @NonNull
    public String getId() { return id; }
    public String getName() { return name; }
    public int getColor() { return color; }
    public void setId(@NonNull String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setColor(int color) { this.color = color; }
}
