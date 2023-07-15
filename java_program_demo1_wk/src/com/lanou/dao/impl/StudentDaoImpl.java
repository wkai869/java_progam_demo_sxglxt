package com.lanou.dao.impl;

import com.lanou.dao.StudentDao;
import com.lanou.entity.StudentDO;
import com.lanou.req.StudentRequest;
import com.lanou.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
	private QueryRunner runner;

	public StudentDaoImpl() {
		runner = new QueryRunner(DBUtil.source());
	}

	/**
	 * 查询符合要求的学生数据总共有多少条
	 * @param request
	 * @return
	 */
	@Override
	public Number searchSlistForCount(StudentRequest request) {
		ArrayList<String> conditionList = new ArrayList<>();
		String sql = getSql("select count(*) from student where 1=1 ",request, conditionList);
		//生成对应?的赋值 数组
		Object[] array = conditionList.toArray();
		try {
			return runner.query(
					sql,
					new ScalarHandler<Number>(),
					array
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过条件，拼接SQL语句，加入条件集合，返回生成的SQL语句
	 * @param request
	 * @param conditionList
	 * @return
	 */
	private static String getSql(String sql,StudentRequest request, ArrayList<String> conditionList) {
		//拼接SQL语句
		StringBuffer buffer = new StringBuffer();
		buffer.append(sql);
		//判断是否有查询条件，有查询条件要进行拼接的
		if(null!= request.getSearchKey() && !"".equals(request.getSearchKey().trim())){
			buffer.append(" and name like ?");
			conditionList.add("%"+ request.getSearchKey()+"%");
		}
		//生成SQL语句
		return buffer.toString();
	}

	/**
	 * 进行分页+条件查询
	 * @param request
	 * @return
	 */
	@Override
	public List<StudentDO> searchSlist(StudentRequest request) {
		ArrayList conditionList = new ArrayList();
		String sql = getSql("select * from student  where 1=1",request, conditionList);
		sql+=" limit ?,?";
		conditionList.add(request.getStart());
		conditionList.add(request.getPageSize());
		//生成对应?的赋值 数组
		Object[] array = conditionList.toArray();
		try {
			return runner.query(
					sql,
					new BeanListHandler<StudentDO>(StudentDO.class),
					array
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将学生信息添加到数据库
	 * @param studentDO		待添加的学生信息
	 * @return							添加成功了几条学生信息
	 */
	@Override
	public int add(StudentDO studentDO) {
		try {
			return runner.execute(
					"insert into student values (null,?,?,CURRENT_TIMESTAMP(),?,?,?,?)",
					studentDO.getName(),
					studentDO.getNo(),
					studentDO.getHomeTown(),
					studentDO.getCnScore(),
					studentDO.getEnScore(),
					studentDO.getMathScore()
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据学生ID查询学生信息
	 * @param selectedStudentId		学生ID
	 * @return									学生信息
	 */
	@Override
	public StudentDO searchStudentById(int selectedStudentId) {
		try {
			return runner.query(
					"select * from student where id = ?",
					new BeanHandler<>(StudentDO.class),
					selectedStudentId
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据	学生ID修改学生信息
	 * @param studentDO			学生信息
	 * @return								修改了几条数据
	 */
	@Override
	public int updateStudent(StudentDO studentDO) {
		try {
			return runner.execute(
					"update student set name=?,no=?,homeTown=?,cnScore=?,enScore=?,mathScore=? where id=?",
					studentDO.getName(),
					studentDO.getNo(),
					studentDO.getHomeTown(),
					studentDO.getCnScore(),
					studentDO.getEnScore(),
					studentDO.getMathScore(),
					studentDO.getId()
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除选中学生信息
	 * @param selectedStudentIds		待删除的学生ID
	 * @return									删除成功了几条数据
	 * delete from student where id in (2,3,4);
	 */
	@Override
	public int delStudentsByIds(int[] selectedStudentIds) {
		//1、创建ArrayList，保存 需要给?赋值的内容
		ArrayList<Integer> conditionList = new ArrayList<>();
		//2、创建StringBuffer，拼接SQL语句
		StringBuffer buffer = new StringBuffer();
		buffer.append("delete from student where id in (");
		//3、遍历ids数组，拼接?
		for (int i = 0; i < selectedStudentIds.length; i++) {
			conditionList.add(selectedStudentIds[i]);
			buffer.append("?");
			//判断：如果不是最后一次循环，拼接逗号
			if(i!=selectedStudentIds.length-1){
				buffer.append(",");
			}
		}
		buffer.append(")");
		//4、将ArrayList转为数组
		Object[] array = conditionList.toArray();
		//5、执行SQL删除
		try {
			return runner.execute(
					buffer.toString(),
					array
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
