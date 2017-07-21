package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.AuthorVp;

public class AuthorDao {

	public int insert(AuthorVp authorVp) {

		int count = -1;

		String name = authorVp.getAuthorName();
		String desc = authorVp.getAuthorDesc();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO AUTHOR VALUES(7,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			count = pstmt.executeUpdate();

			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이브 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
			// 5. 자원정리

		}
		return count;
	}

	public int update(AuthorVp authorVp) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		int count = -1;

		int num = authorVp.getAuthorId();
		String name = authorVp.getAuthorName();
		String desc = authorVp.getAuthorDesc();

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "UPDATE AUTHOR " + "SET AUTHOR_DESC = ? " + ",AUTHOR_NAME= ? " + "WHERE AUTHOR_ID= ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, desc);
			pstmt.setString(2, name);
			pstmt.setInt(3, num);
			count = pstmt.executeUpdate();
			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}

		return count;
	}

	public int delete(int num) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩

			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "DELETE FROM AUTHOR " + "WHERE AUTHOR_ID = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, num);
			int count = pstmt.executeUpdate();

			System.out.println(count + "건 처리");
			// 4.결과처리

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error: " + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}
		}

		return 1;

	}

	/*
	 * public List<AuthorVp> select() {
	 * 
	 * List<AuthorVp> authorList = new ArrayList<AuthorVp>();
	 * 
	 * 
	 * // 0. import java.sql.*;
	 * 
	 * Connection conn = null;
	 * 
	 * PreparedStatement pstmt = null;
	 * 
	 * ResultSet rs = null;
	 * 
	 * try {
	 * 
	 * // 1. JDBC 드라이버 (Oracle) 로딩 Class.forName("oracle.jdbc.driver.OracleDriver");
	 * 
	 * // 2. Connection 얻어오기 String url = "jdbc:oracle:thin:@localhost:1521:xe";
	 * conn = DriverManager.getConnection(url, "webdb", "webdb");
	 * 
	 * System.out.println("접속되었습니다.");
	 * 
	 * // 3. SQL문 준비 / 바인딩 / 실행
	 * 
	 * String query = "SELECT AUTHOR_ID, " + "AUTHOR_NAME, " + "AUTHOR_DESC " +
	 * "FROM AUTHOR"; pstmt = conn.prepareStatement(query);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * // 4.결과처리
	 * 
	 * while(rs.next()) { int authorId = rs.getInt("AUTHOR_ID"); String authorName =
	 * rs.getString("AUTHOR_NAME"); String authorDesc = rs.getString("AUTHOR_DESC");
	 * 
	 * AuthorVp vo = new AuthorVp(authorId, authorName, authorDesc);
	 * 
	 * authorList.add(vo); }
	 * 
	 * } catch (ClassNotFoundException e) {
	 * 
	 * System.out.println("error: 드라이버 로딩 실패 - " + e);
	 * 
	 * } catch (SQLException e) {
	 * 
	 * System.out.println("error:" + e);
	 * 
	 * } finally {
	 * 
	 * // 5. 자원정리
	 * 
	 * try {
	 * 
	 * if (pstmt != null) {
	 * 
	 * pstmt.close();
	 * 
	 * }
	 * 
	 * if (conn != null) {
	 * 
	 * conn.close();
	 * 
	 * }
	 * 
	 * } catch (SQLException e) {
	 * 
	 * System.out.println("error:" + e);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * return authorList; }
	 */

	public List<AuthorVp> Selectex() {

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<AuthorVp> list = new ArrayList<AuthorVp>();

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("접속되었습니다.");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "SELECT AUTHOR_ID, " + "AUTHOR_NAME, " + "AUTHOR_DESC " + "FROM AUTHOR";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {

				AuthorVp vp = new AuthorVp();

				vp.setAuthorId(rs.getInt("AUTHOR_ID"));
				vp.setAuthorName(rs.getString("AUTHOR_NAME"));
				vp.setAuthorDesc(rs.getString("AUTHOR_DESC"));

				list.add(vp);
			}

		} catch (ClassNotFoundException e) {

			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {

			System.out.println("error:" + e);

		} finally {

			// 5. 자원정리

			try {

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}

		}
		return list;
	}

}
