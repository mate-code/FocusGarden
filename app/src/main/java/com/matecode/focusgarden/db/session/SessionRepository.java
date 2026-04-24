package com.matecode.focusgarden.db.session;

import java.util.List;

public class SessionRepository {
    private final SessionDao sessionDao;

    public SessionRepository(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public void insert(Session session) {
        sessionDao.insert(session);
    }

    public List<Session> getAll() {
        return sessionDao.getAll();
    }

    public List<Session> getUserSessions(String userId) { return sessionDao.getUserSessions(userId); }

    public List<SessionWithCategory> getUserSessionsWithCategories(String userId) { return sessionDao.getUserSessionsWithCategories(userId); }

    public void deleteAll() {
        sessionDao.deleteAll();
    }
}
