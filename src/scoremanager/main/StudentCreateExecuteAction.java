package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

@Override
public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	HttpSession session = req.getSession(); // セッション
	Teacher teacher = (Teacher)session.getAttribute("user");

	// ローかル変数の指定1
	int ent_year = 0;
	String student_no = "";
	String student_name = "";
	String class_num = "";
	StudentDao studentDao = new StudentDao();

	Map<String, String> errors = new HashMap<>(); //エラーメッセージ

	// リクエストパラメーターの取得2
	ent_year = Integer.parseInt( req.getParameter("ent_year"));
	student_no = req.getParameter("no");
	student_name = req.getParameter("name");
	class_num = req.getParameter("class_num");


	// DBからデータ取得3
	// なし

	// ビジネスロジック4

	if(ent_year == 0) {
		//エラー処理
		errors.put("ent_year", "入学年度を選択してください");
		req.setAttribute("errors", errors);
	}else{
		if(studentDao.get(student_no) != null) {
			//エラー処理
			errors.put("no", "学生番号が重複しています");
			req.setAttribute("errors", errors);
		} else {
			Student student = new Student();

			student.setEntYear(ent_year);
			student.setNo(student_no);
			student.setName(student_name);
			student.setClassNum(class_num);
			student.setAttend(true);
			student.setSchool(teacher.getSchool());

			studentDao.save(student);
		}
	}

	// レスポンス値をセット 6
	// リクエストにデータをセット
	req.setAttribute("ent_year", ent_year);
	req.setAttribute("no", student_no);
	req.setAttribute("name", student_name);
	req.setAttribute("class_num", class_num);

	// JSPへフォワード 7
	if(errors.isEmpty()) {
		req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
	} else {
		req.getRequestDispatcher("StudentCreate.action").forward(req, res);
	}
	}
}
