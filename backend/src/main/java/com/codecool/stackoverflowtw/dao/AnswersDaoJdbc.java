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
    public void add(int id, String answer) {
        String sql = "INSERT INTO answer(answer, question_id, date_created) VALUES (?, ?, ?)";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, answer);
            pstmt.setInt(2, id);

            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

            pstmt.setTimestamp(3, timestamp);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Answer get(int id) {
        String sql = "SELECT answer_id, answer, question_id, date_created FROM answer " +
                "WHERE answer_id = ?";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

//                    System.out.println(
//                            rs.getInt("answer_id") + "\t" +
//                                    rs.getString("answer") + "\t" +
//                                    rs.getInt("question_id") + "\t" +
//                                    rs.getTimestamp("date_created"));

                    Answer question = new Answer(
                            rs.getInt("answer_id"),
                            rs.getInt("question_id"),
                            rs.getString("answer"),
                            rs.getTimestamp("date_created").toLocalDateTime());

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
        String sql = "SELECT a.answer_id, a.answer, a.question_id, a.date_created FROM answer a " +
                "JOIN question q ON q.question_id = a.question_id " +
                "WHERE a.question_id = ? " +
                "ORDER BY a.answer_id";
        List<Answer> answers = new ArrayList<>();

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
//                System.out.println(
//                        rs.getInt("answer_id") + "\t" +
//                                rs.getString("answer") + "\t" +
//                                rs.getInt("question_id") + "\t" +
//                                rs.getTimestamp("date_created"));
                answers.add(new Answer(
                        rs.getInt("answer_id"),
                        rs.getInt("question_id"),
                        rs.getString("answer"),
                        rs.getTimestamp("date_created").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return answers;
    }

    @Override
    public void update(int id, String answer) {
        String sql = "UPDATE answer SET answer = ? WHERE answer_id = ?";

        try (Connection conn = sqlConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, answer);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM answer WHERE answer_id = ?";

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
