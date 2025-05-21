package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private String baseSql = "select student.ent_year, student.no, student.name, student.class_num,"
			+ "a.noas nol, a.point as pointl, b.no as no2, bpoint as point2";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

		List<TestListSubject> list = new ArrayList<>();
		try{
			while (rSet.next()){
				TestListSubject tls = new TestListSubject();
				tls.setEntYear(rSet.getInt("ent_year"));
				tls.setStudentNo(rSet.getString("no"));
				tls.setStudentName(rSet.getString("name"));
				tls.setClassNum(rSet.getString("class_num"));
				tls.putPoint(rSet.getInt("no1"), rSet.getInt("point1"));
				if(rSet.getInt("no2") != 0){
					tls.putPoint(rSet.getInt("no2"), rSet.getInt("point2"));
				} else {
					tls.putPoint(2, -1);
				}

				list.add(tls);
			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}

		return list;
	}
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school)
throws Exception {
		List<TestListSubject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String from = " from (selecttest.student_no,test.subject_cd,test.school_cd,test.no,"
		+"test.point,test.class_num from test join student ontest.student_no = student.no"
	    +"wherestudent.ent_year = ? and subject_cd = ? andtest.class_num = ? and"
		+"test.school_cd = ? and test.no = 1 order bytest.student_no) as a";

		String join = "left join (select test.student_no, test.subject_cd,test.school_cd,test.no,test.point,test.class_num"
		+ "from test join student on test.student_no = student.no where student.ent_year = ? and subject_cd = ? and"
		+ "test.class_num = ? and test.school_cd = ? and test.no = 2 order by test.student_no) as b";

		String condition = "on a.student_no = b.student_no and a.subject_cd = b.subject_cd and a.class_num= b.class_num";
		String join2 = "join student on a.student_no = student.no";
		String order ="order by a.student_no asc, a.no asc";

		try {
			statement = connection.prepareStatement(baseSql + from + join + condition + join2 + order);
			statement.setInt(1, entYear);
			statement.setString(2, subject.getCd());
			statement.setString(3, classNum);
			statement.setString(4, school.getCd());
			statement.setInt(5, entYear);
			statement.setString(6, subject.getCd());
			statement.setString(7, classNum);
			statement.setString(8, school.getCd());

			rSet = statement.executeQuery();
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null){
				try{
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}