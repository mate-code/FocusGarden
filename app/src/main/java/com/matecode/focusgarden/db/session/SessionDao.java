package com.matecode.focusgarden.db.session;

import androidx.room.Insert;
import androidx.room.Query;

import com.matecode.focusgarden.db.session.Session;

import java.util.List;

public interface SessionDao {
    @Insert
    void insert(Session session);

    @Query("SELECT * FROM Session")
    List<Session> getAll();

    @Query("DELETE FROM Session")
    void deleteAll();
}
