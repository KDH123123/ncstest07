package controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class BoardListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		Connection conn = (Connection) sce.getServletContext().getAttribute("conn");
		try {
			if(conn!=null)
			conn.close();
			System.out.println("DBCP종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			Connection conn = ds.getConnection();//// servlet context에 저장->서버 시작해서 끝날 때 까지 저장소 유지를 위해
			System.out.println("DBCP실행");
			sce.getServletContext().setAttribute("conn", conn);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

}
