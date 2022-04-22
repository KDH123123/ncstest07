package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/insert")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	//5번 문항작성//////////////////////////////////////////////////////////
	@Override
	public void init() throws ServletException {
		conn = (Connection) getServletContext().getAttribute("conn");
	}
	//DB 접속하는 객체 init()가 실행되면서 초기화 진행

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//7번 문항 : 파라미터에서 넘어오는 데이터 인코딩 설정하세요
		request.setCharacterEncoding("utf-8");

		//8번 문항 : 웹에서 넘어오는 데이터 받아 알맞은 변수를 만들어 저장하세요
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		if(("").equals(writer)||writer==null) {
			writer="guest";
		}
		

		//9번 board 테이블에 입력처리할 쿼리를 문자열로 생성하세요
		String sql = "insert into BOARD(NO,SUBJECT,CONTENT,WRITER,CREATED_DATE) " + "values(seq_bo.nextval,?,?,?,sysdate) ";
		//10번 PreparedStatement 객체롤 만들어서 쿼리를 실행하세요
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)
				pstmt.close();
				System.out.println("입력완료");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		//11번 페이지이동//////////////////////////////////////////////////////////
		request.getRequestDispatcher("/output.jsp").forward(request, response);

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	
	

}
