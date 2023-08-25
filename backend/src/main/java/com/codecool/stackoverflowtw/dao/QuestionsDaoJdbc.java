package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.service.SqlConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private final SqlConnector sqlConnector;

    public QuestionsDaoJdbc(SqlConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
    }

    @Override
    public void add(int id, String title, String description) {
        String sql = "INSERT INTO question(title, description, date_created) VALUES (?, ?, ?)";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, description);

            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            System.out.println("Generated Timestamp: " + timestamp);

            pstmt.setTimestamp(3, timestamp);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Question get(int id) {
        String sql = "SELECT question_id, title, description, date_created FROM question WHERE question_id = ?";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    Question question = new Question(
                            rs.getInt("question_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getTimestamp("date_created").toLocalDateTime());

                    System.out.println(
                            rs.getInt("question_id") + "\t" +
                                    rs.getString("title") + "\t" +
                                    rs.getString("description") + "\t" +
                                    rs.getTimestamp("date_created"));

                    return question;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Question> getAll() {
        String sql = "SELECT question_id, title, description, date_created FROM question " +
                "ORDER BY question_id";
        List<Question> questions = new ArrayList<>();

        try (Connection conn = sqlConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("question_id") + "\t" +
                        rs.getString("title") + "\t" +
                        rs.getString("description") + "\t" +
                        rs.getTimestamp("date_created"));
                questions.add(new Question(
                        rs.getInt("question_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("date_created").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return questions;
    }

    @Override
    public void update(int id, String title, String description) {
        String sql = "UPDATE question SET title = ?, description = ? WHERE question_id = ?";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM question WHERE question_id = ?";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteAll() {
        String sql = "DELETE FROM question";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
