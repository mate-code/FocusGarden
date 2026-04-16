package com.matecode.focusgarden.db.user;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.matecode.focusgarden.Utils.PasswordUtils;

import java.util.UUID;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    private String id;
    private String username;
    private String passwordHash;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String username, String passwordRaw) {
        this();
        this.username = username;
        this.passwordHash = PasswordUtils.hashPassword(passwordRaw);
    }

    // This constructor only for test purpose
    public User(@NonNull String id, String username, String passwordRaw) {
        this.id = id;
        this.username = username;
        this.passwordHash = PasswordUtils.hashPassword(passwordRaw);
    }

    @NonNull
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public void setId(@NonNull String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
