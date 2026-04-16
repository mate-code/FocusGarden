package com.matecode.focusgarden.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.matecode.focusgarden.db.user.User;
import com.matecode.focusgarden.db.user.UserDao;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile com.matecode.focusgarden.db.AppDatabase INSTANCE;
    private static final String DB_NAME = "FocusGardenDataBase";

    public abstract UserDao userDao();

    public static com.matecode.focusgarden.db.AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {     // avoid locking when instance already exists, for fast performance
            synchronized (com.matecode.focusgarden.db.AppDatabase.class) {
                if (INSTANCE == null) {     // prevents multiple instance creation
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            com.matecode.focusgarden.db.AppDatabase.class,
                            DB_NAME
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    public static void deleteDataBase(Context context) {
        context.deleteDatabase(DB_NAME);
        INSTANCE = null;
    }

}
