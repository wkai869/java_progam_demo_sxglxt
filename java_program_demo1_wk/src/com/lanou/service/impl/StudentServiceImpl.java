package com.lanou.service.impl;

import com.lanou.dao.StudentDao;
import com.lanou.dao.impl.StudentDaoImpl;
import com.lanou.entity.StudentDO;
import com.lanou.req.StudentRequest;
import com.lanou.res.TableDTO;
import com.lanou.service.StudentService;

import java.util.List;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {
	public StudentDao studentDao;
	
	public StudentServiceImpl() {
		studentDao = new StudentDaoImpl();
	}


    /**
     * 将List转储为Vector
     * @param students
     * @return
     */
    private Vector<Vector<Object>> fillData(List<StudentDO> students){
        Vector<Vector<Object>> data = new Vector<>();
        for (StudentDO student : students) {
        	Vector<Object> oneRecord = new Vector<>();
            long id = student.getId();
            String name = student.getName();
            String no = student.getNo();
            String homeTown = student.getHomeTown();
            double cnScore = student.getCnScore();
            double enScore = student.getEnScore();
            double mathScore = student.getMathScore();
            int totalScore = (int) (cnScore + enScore + mathScore);
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(no);
            oneRecord.addElement(homeTown);
            oneRecord.addElement(cnScore);
            oneRecord.addElement(enScore);
            oneRecord.addElement(mathScore);
            oneRecord.addElement(totalScore);
            data.add(oneRecord);
        }
        return data;
    }

    /**
     * 对学生信息进行分页+条件查询
     * @param request       查询条件
     * @return                      当前这一页的数据
     */
    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        //1、查询数据库符合要求的学生数据，总共有多少
        int totalCount = studentDao.searchSlistForCount(request).intValue();
        System.out.println(totalCount);
        //2、查询数据库当前页有哪些数据需要展示
        List<StudentDO> slist = studentDao.searchSlist(request);
        //3、将List转为Vector
        Vector<Vector<Object>> vectors = fillData(slist);
        //4、返回查询结果
        TableDTO tableDTO = new TableDTO();
        tableDTO.setTotalCount(totalCount);
        tableDTO.setData(vectors);
        return tableDTO;
    }

    /**
     * 添加学生
     * @param studentDO     待添加的学生信息
     * @return                          添加是否成功   true 添加成功  false 添加失败
     */
    @Override
    public boolean add(StudentDO studentDO) {
        return studentDao.add(studentDO)>0;
    }

    /**
     * 根据学生的ID查询学生信息
     * @param selectedStudentId     学生ID
     * @return                                  学生信息
     */
    @Override
    public StudentDO searchStudentById(int selectedStudentId) {
        return studentDao.searchStudentById(selectedStudentId);
    }

    /**
     * 根据ID修改学生信息
     * @param studentDO         学生信息
     * @return                              修改业务结果   true  修改成功  false  修改失败
     */
    @Override
    public boolean updateStudent(StudentDO studentDO) {
        int row = studentDao.updateStudent(studentDO);
        return row>0;
    }

    /**
     * 删除选中学生
     * @param selectedStudentIds        待删除学生的ID
     * @return                                      删除的业务结果    true:成功   false:删除失败
     */
    @Override
    public boolean delStudentsByIds(int[] selectedStudentIds) {
        //1、调用dao,传递ID，进行删除操作
        int row = studentDao.delStudentsByIds(selectedStudentIds);
        //2、判断业务
        // （如果删除成功的条目 等于  需要删除的条目数量，删除成功）
        //（如果删除成功的条目 不等于  需要删除的条目数量，删除失败）
        return row==selectedStudentIds.length;
    }









}
