package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	private String baseSql = "select * from student where school_cd=?" ;

	public Student get(String no) throws Exception {
		// クラス番号インスタンスを初期化
		Student student = new Student();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from student where no=? ");
			// プリペアードステートメントに学生番号をバインド
			statement.setString(1, no);
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (resultSet.next()) {
				// リザルトセットが存在する場合
				// クラス番号インスタンスに検索結果をセット
				student.setNo(resultSet.getString("no"));
				student.setName(resultSet.getString("name"));
				student.setEntYear(resultSet.getInt("ent_year"));
				student.setClassNum(resultSet.getString("class_num"));
				student.setAttend(resultSet.getBoolean("is_attend"));
				student.setSchool(schoolDao.get(resultSet.getString("school_cd")));
			} else {
				// リザルトセットが存在しない場合
				// クラス番号インスタンスにnullをセット
				student = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return student;
	}

	private List<Student> postFilter(ResultSet resultSet,School school) throws Exception {
		List<Student> list = new ArrayList<>();

		try{
		while (resultSet.next()) {
			// リザルトセットが存在する場合
			Student student = new Student();

			// クラス番号インスタンスに検索結果をセット
			student.setNo(resultSet.getString("no"));
			student.setName(resultSet.getString("name"));
			student.setEntYear(resultSet.getInt("ent_year"));
			student.setClassNum(resultSet.getString("class_num"));
			student.setAttend(resultSet.getBoolean("is_attend"));
			student.setSchool(school);

			list.add(student);
			}
		}catch(SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Student> filter(School school,int entYear,String classNum,boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		String conditionAttend = " and is_attend=true";
		if(isAttend) {
			conditionAttend = " and is_attend=true ";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement( baseSql + "and ent_year=? and class_num=?"  + conditionAttend + " order by no asc");
			// プリペアードステートメントに値をバインド
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			list = postFilter(resultSet, school);

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}

	public List<Student> filter(School school,int entYear,boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		String conditionAttend = " and is_attend=true";
		if(isAttend) {
			conditionAttend = " and is_attend=true ";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement( baseSql + " and ent_year=?" + conditionAttend + " order by no asc");
			// プリペアードステートメントに値をバインド
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			list = postFilter(resultSet, school);

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}

	public List<Student> filter(School school,boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		String conditionAttend = " and is_attend=true";
		if(isAttend) {
			conditionAttend = " and is_attend=true ";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement( baseSql + conditionAttend + " order by no asc");
			// プリペアードステートメントに値をバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			list = postFilter(resultSet, school);

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}

	public boolean save(Student student) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		// 実行件数
		int count = 0;

		try {
			Student old = get(student.getNo());

			if(old == null) {
			// 存在しなければINSERT
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
			// プリペアードステートメントに値をバインド
			statement.setString(1, student.getNo());
			statement.setString(2, student.getName());
			statement.setInt(3, student.getEntYear());
			statement.setString(4, student.getClassNum());
			statement.setBoolean(5, student.isAttend());
			statement.setString(6, student.getSchool().getCd());
		} else {
			// 存在すればUPDATE
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("update student set name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_cd = ? where no = ?");
			// プリペアードステートメントに値をバインド
			statement.setString(1, student.getName());
			statement.setInt(2, student.getEntYear());
			statement.setString(3, student.getClassNum());
			statement.setBoolean(4, student.isAttend());
			statement.setString(5, student.getSchool().getCd());
			statement.setString(6, student.getNo());
		}
			// プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return (count > 0);
	}
}