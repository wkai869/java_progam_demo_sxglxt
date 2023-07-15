package com.lanou.dao;

import com.lanou.entity.StudentDO;
import com.lanou.req.StudentRequest;

import java.util.List;

public interface StudentDao {

    Number searchSlistForCount(StudentRequest request);

    List<StudentDO> searchSlist(StudentRequest request);

    int add(StudentDO studentDO);

    StudentDO searchStudentById(int selectedStudentId);

    int updateStudent(StudentDO studentDO);

    int delStudentsByIds(int[] selectedStudentIds);
}
