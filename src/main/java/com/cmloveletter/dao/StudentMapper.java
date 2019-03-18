package com.cmloveletter.dao;

import com.cmloveletter.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    public List<Student> getAll();
    public void insertStu(Student s);
    public void deleteStu(int id);
}
