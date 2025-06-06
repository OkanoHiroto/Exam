//学生別成績一覧

package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject extends User implements Serializable {
	private int entYear;
	private String studentNo;
	private String studentName;

	//クラス String

	private String classNum;

	//回数とその得点 Map<Integer,Integer>

	private Map<Integer,Integer> points = new HashMap<>();

	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public Map<Integer, Integer> getPoints() {
		return points;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}
	public String getPoint(int key) {
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();

		map = getPoints();

		return Integer.toString(map.get(key));
	}
	public void putPoint(int key, int value) {
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();

		map = getPoints();

		map.put(key, value);

		setPoints(map);
	}
}
