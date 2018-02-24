package com.example.service;

import com.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuhaochao on 2017/7/18.
 */
@Service
public class StudentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getStuList() {
        String sql = "select user_no,user_name,user_birth,user_sex,id_no from student_info";
        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student stu = new Student();
                stu.setUser_no(resultSet.getString("user_no"));
                stu.setUser_name(resultSet.getString("user_name"));
                stu.setUser_sex(resultSet.getString("user_sex"));
                stu.setUser_birth(resultSet.getString("user_birth"));
                stu.setId_no(resultSet.getString("id_no"));
                return stu;
            }
        });
    }

}
