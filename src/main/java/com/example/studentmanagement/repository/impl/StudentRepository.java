package com.example.studentmanagement.repository.impl;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.IStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, score from student"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            Student student;
            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setScore(resultSet.getDouble("score"));

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    @Override
    public Student findById(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, score from student where id = ?"
            );

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Student student;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setScore(resultSet.getDouble("score"));

                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "insert into student (name, score) VALUES (?, ?)"
            );

            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(2, student.getScore());

           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
