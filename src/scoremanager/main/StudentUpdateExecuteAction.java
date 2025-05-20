package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

@Override
public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	HttpSession session = req.getSession(); // セッション
	Teacher teacher = (Teacher)session.getAttribute("user");

	// ローカル変数の指定1
	int ent_year = 0;
	String student_no = "";
	String student_name = "";
	String class_num = "";
	String is_attend_str = ""; //在学中(文字列)
	boolean is_attend = false; //在学中
	StudentDao studentDao = new StudentDao();

	// リクエストパラメーターの取得2
	ent_year = Integer.parseInt( req.getParameter("ent_year"));
	student_no = req.getParameter("no");
	student_name = req.getParameter("name");
	class_num = req.getParameter("class_num");
	is_attend_str = req.getParameter("is_attend");



	// DBからデータ取得3
	// なし

	// ビジネスロジック4
	if(is_attend_str != null) {
		is_attend = true;
	}

	Student student = new Student();

	student.setEntYear(ent_year);
	student.setNo(student_no);
	student.setName(student_name);
	student.setClassNum(class_num);
	student.setAttend(is_attend);
	student.setSchool(teacher.getSchool());

	studentDao.save(student);

	// レスポンス値をセット 6
	// なし

	// JSPへフォワード 7
	req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}
}
