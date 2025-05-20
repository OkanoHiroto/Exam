package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
	public ClassNum get(String class_num,School school) throws Exception {
		// クラス番号インスタンスを初期化
				ClassNum classNum = new ClassNum();
				// コネクションを確立
				Connection connection = getConnection();
				// プリペアードステートメント
				PreparedStatement statement = null;

				try {
					// プリペアードステートメントにSQL文をセット
					statement = connection.prepareStatement("select * from class_num where class_num=? and school_cd=?");
					// プリペアードステートメントにクラス番号をバインド
					statement.setString(1, class_num);
					// プリペアードステートメントに学校コードをバインド
					statement.setString(2, school.getCd());
					// プリペアードステートメントを実行
					ResultSet resultSet = statement.executeQuery();

					// 学校Daoを初期化
					SchoolDao schoolDao = new SchoolDao();

					if (resultSet.next()) {
						// リザルトセットが存在する場合
						// クラス番号インスタンスに検索結果をセット
						classNum.setClass_num(resultSet.getString("class_num"));
						// 学校フィールドには学校コードで検索した学校インスタンスをセット
						classNum.setSchool(schoolDao.get(resultSet.getString("school_cd")));
					} else {
						// リザルトセットが存在しない場合
						// クラス番号インスタンスにnullをセット
						classNum = null;
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
				return classNum;
	}

	public List<String> filter(School school) throws Exception {
		// クラス番号インスタンスを初期化
		List<String> list= new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select class_num from class_num where school_cd=? order by class_num");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				// リストにクラス番号をセット
				list.add(resultSet.getString("class_num"));
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
		return list;
	}

	public boolean save(ClassNum classNum) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("insert into class_num(school_cd, class_num) values(?, ?)");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, classNum.getSchool().getCd());
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, classNum.getClass_num());
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

	public boolean save(ClassNum classNum,String newClassNum) throws Exception {
		// コネクションを確立
				Connection connection = getConnection();
				// プリペアードステートメント
				PreparedStatement statement = null;

				// 実行件数
				int count = 0;

				try {
					// プリペアードステートメントにSQL文をセット
					statement = connection.prepareStatement("update class_num set class_num = ? where school_cd = ? and class_num = ?");
					// プリペアードステートメントに学校コードをバインド
					statement.setString(1, newClassNum);
					// プリペアードステートメントにクラス番号をバインド
					statement.setString(2, classNum.getSchool().getCd());
					// プリペアードステートメントにクラス番号をバインド
					statement.setString(3, classNum.getClass_num());
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
