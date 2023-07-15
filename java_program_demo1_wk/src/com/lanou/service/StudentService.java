package com.lanou.service;

import com.lanou.entity.StudentDO;
import com.lanou.req.StudentRequest;
import com.lanou.res.TableDTO;

public interface StudentService {

    TableDTO retrieveStudents(StudentRequest request);

    boolean add(StudentDO studentDO);

    StudentDO searchStudentById(int selectedStudentId);

    boolean updateStudent(StudentDO studentDO);

    boolean delStudentsByIds(int[] selectedStudentIds);
}

