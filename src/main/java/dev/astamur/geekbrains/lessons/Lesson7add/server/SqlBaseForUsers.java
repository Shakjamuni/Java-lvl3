package dev.astamur.geekbrains.lessons.Lesson7add.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlBaseForUsers {
    Connection conn;
    Statement stmt;
    PreparedStatement ps;
    String login = "login";
    String passw = "pass";
    String nick = "nick";
    ResultSet rs;

    void setUpDB() throws SQLException {
        String dbFileName = "chatusers.db";
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
        stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS usersInChat(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Nick TEXT NOT NULL UNIQUE," +
                " Login TEXT NOT NULL, Passw TEXT NOT NULL);");
    }

    void fillBase() {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO usersInChat(Nick, Login, Passw) VALUES (?, ?, ?)")) {
            for (int i = 1; i <= 10; i++) {
                ps.setString(1, nick + i);
                ps.setString(2, login + i);
                ps.setString(3, passw + i);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void getUsersList() throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM usersInChat");
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.print(rs.getString(3) + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) throws SQLException {
        SqlBaseForUsers s = new SqlBaseForUsers();
        s.setUpDB();
        s.rs = s.stmt.executeQuery("SELECT * FROM usersInChat");
        while (s.rs.next()) {
            System.out.print(s.rs.getString(1) + " ");
            System.out.print(s.rs.getString(2) + " ");
            System.out.print(s.rs.getString(3) + " ");
            System.out.println();
        }
        s.fillBase();

    }
}

