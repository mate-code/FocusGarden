package com.matecode.focusgarden.db.session;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SessionDao {
    @Insert
    void insert(Session session);

    @Query("SELECT * FROM Session")
    List<Session> getAll();

    @Query("SELECT * FROM Session WHERE userID = :userId")
    List<Session> getUserSessions(String userId);

    @Query("DELETE FROM Session")
    void deleteAll();
}
