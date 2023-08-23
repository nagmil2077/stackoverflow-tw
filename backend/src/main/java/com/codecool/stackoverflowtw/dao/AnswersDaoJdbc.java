package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.service.SqlConnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO {

    private final SqlConnector sqlConnector;

    public AnswersDaoJdbc(SqlConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
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
    public Answer get(int id) {
        String sql = "SELECT id, answer, date_created FROM answer " +
                "JOIN question ON question.question_id = answer.question_id " +
                "WHERE question_id = ?";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    Answer question = new Answer(
                            rs.getInt("answer_id"),
                            rs.getInt("id"),
                            rs.getString("answer"),
                            rs.getTimestamp("date_created").toLocalDateTime());

                    System.out.println(
                            rs.getInt("id") + "\t" +
                                    rs.getString("answer") + "\t" +
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
    public List<Answer> getAll(int id) {
        String sql = "SELECT id, answer, date_created FROM answer " +
                "JOIN question ON question.question_id = answer.question_id " +
                "WHERE question_id = ?" +
                "ORDER BY answer_id";
        List<Answer> answers = new ArrayList<>();

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "\t" +
                                rs.getString("answer") + "\t" +
                                rs.getTimestamp("date_created"));
                answers.add(new Answer(
                        rs.getInt("answer_id"),
                        rs.getInt("id"),
                        rs.getString("answer"),
                        rs.getTimestamp("date_created").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return answers;
    }

    @Override
    public void update(int id, String title, String description) {
        String sql = "UPDATE question SET title = ?, description = ? WHERE id = ?";

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
        String sql = "DELETE FROM question WHERE id = ?";

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
