package dev.astamur.geekbrains.lessons.Lesson7add.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private List<Entry> entries = new ArrayList<>();

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }


    public BaseAuthService() throws SQLException {
        SqlBaseForUsers sqlBaseForUsers = new SqlBaseForUsers();
        sqlBaseForUsers.setUpDB();
        sqlBaseForUsers.getUsersList();
        sqlBaseForUsers.rs = sqlBaseForUsers.stmt.executeQuery("SELECT * FROM usersInChat");
        while (sqlBaseForUsers.rs.next()) {
            entries.add(new Entry(sqlBaseForUsers.rs.getString(2), sqlBaseForUsers.rs.getString(3), sqlBaseForUsers.rs.getString(1)));
        }

    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }

    static class Entry {
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }
}
