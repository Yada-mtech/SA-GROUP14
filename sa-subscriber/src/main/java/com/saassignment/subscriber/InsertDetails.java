package com.saassignment.subscriber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;

public class InsertDetails {

private final String url = "jdbc:postgresql://localhost/postgres";
private final String user = "postgres";
private final String password = "admin";

public Connection connect() {
    Connection conn = null;
    try {
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the PostgreSQL server successfully.");

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    return conn;
}

public void createItem(String title, String cast, String dir) {
    try {
        Statement stmnt = null;
        stmnt = connect().createStatement();
        String sql = "INSERT INTO public.\"IMDB\"(\"ID\", \"TITLE\", \"CAST\", \"DIRECTOR\") "
                + "VALUES " + "(" + "1, '" + title + "', '" + cast + "', '" + dir + "')";
        System.out.println(sql);
        stmnt.executeUpdate(sql);

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

	}
}

