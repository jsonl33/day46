import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Board_insert {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "night";
		String password = "123456";

		Connection con = null;
		Statement stmt = null;
		String sql = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

			Scanner sc = new Scanner(System.in);

			System.out.println("tbl_board10 테이블에 레코드 저장하기 >> ");
			System.out.print("글쓴이 입력 >> ");
			String bname = sc.nextLine();
			System.out.print("글제목 입력 >> ");
			String btitle = sc.nextLine();
			System.out.print("글내용 입력 >> ");
			String bcont = sc.nextLine();

			stmt = con.createStatement();

			sql = "insert into tbl_board10 (bno, bname, btitle, bcont, bdate) values" + "(bno_seq10.nextval, '" + bname
					+ "', '" + btitle + "', '" + bcont + "',sysdate)";

			int re = stmt.executeUpdate(sql);

			if (re == 1) {
				System.out.println("게시판 저장 성공");
				sc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
