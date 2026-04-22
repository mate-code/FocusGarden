package com.matecode.focusgarden.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.matecode.focusgarden.db.category.Category;
import com.matecode.focusgarden.db.category.CategoryDao;
import com.matecode.focusgarden.db.category.CategoryRepository;
import com.matecode.focusgarden.db.session.Session;
import com.matecode.focusgarden.db.session.SessionDao;
import com.matecode.focusgarden.db.session.SessionRepository;
import com.matecode.focusgarden.db.user.User;
import com.matecode.focusgarden.db.user.UserDao;
import com.matecode.focusgarden.db.user.UserRepository;

import java.io.File;
import java.util.concurrent.Executors;


@Database(
        entities = {User.class, Session.class, Category.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final String DB_NAME = "FocusGardenDataBase";

    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract SessionDao sessionDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {     // avoid locking when instance already exists, for fast performance
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {     // prevents multiple instance creation
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME
                    ).build();
                    Log.println(Log.DEBUG, AppDatabase.class.getSimpleName(), "DB instance was created");

                    if (!doesDatabaseExists(context)) { // if database file do not exists in phone memory, seed some default data
                        seedUser();
                        seedCategory();
                        seedSession();
                        Log.println(Log.DEBUG, AppDatabase.class.getSimpleName(), "DB was seed with default data");
                    }
                }
            }
        }
        return INSTANCE;
    }

    public static void seedUser() {
        Executors.newSingleThreadExecutor().execute(() -> {
            UserRepository userRepo = new UserRepository(INSTANCE.userDao());
            User user = new User("0-0-0","TestUser", "TestUserPassword123");
            userRepo.insert(user);
        });
    }

    public static void seedCategory() {
        Executors.newSingleThreadExecutor().execute(() -> {
            CategoryRepository categoryRepo = new CategoryRepository(INSTANCE.categoryDao());
            categoryRepo.insert(new Category("0-0-0", "Work", 0xFFFF0000));     // RED
            categoryRepo.insert(new Category("0-0-1", "Study", 0xFF00FF00));    // GREEN
            categoryRepo.insert(new Category("0-0-2", "Sport", 0xFF0000FF));    // BLUE
        });
    }

    public static void seedSession() {
        Executors.newSingleThreadExecutor().execute(() -> {
            SessionRepository sessionRepo = new SessionRepository(INSTANCE.sessionDao());
            sessionRepo.insert(new Session("0-0-0", "0-0-0", "0-0-0", 1776366000000L, 1776369000000L, false));
            sessionRepo.insert(new Session("0-0-1", "0-0-1", "0-0-0", 1776330000000L, 1776331800000L, true));
            sessionRepo.insert(new Session("0-0-2", "0-0-1", "0-0-0", 1776320100000L, 1776321000000L, false));
            sessionRepo.insert(new Session("0-0-3", "0-0-2", "0-0-0", 1776346200000L, 1776349800000L, false));
            sessionRepo.insert(new Session("0-0-4", "0-0-0", "0-0-0", 1776310800000L, 1776312000000L, true));
            sessionRepo.insert(new Session("0-0-5", "0-0-0", "0-0-0", 1776301200000L, 1776303180000L, false));
        });
    }

    public static void deleteDataBase(Context context) {
        context.deleteDatabase(DB_NAME);
        INSTANCE = null;
    }

    private static boolean doesDatabaseExists(Context context) {
        File dbFile = context.getDatabasePath(DB_NAME);
        return dbFile.exists();
    }

    public static String getDatabaseName() {
        return DB_NAME;
    }

}
