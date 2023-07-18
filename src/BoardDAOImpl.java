import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.daum.vo.BoardVO;

public class BoardDAOImpl {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "night";
	String password = "123456";

	Connection con = null;
	PreparedStatement pt = null;
	ResultSet rs = null;
	String sql = null;

	public List<BoardVO> getBoardList() {
		List<BoardVO> blist = new ArrayList<>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			sql = "select * from tbl_board10 order by bno desc";
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			
			while(rs.next()) {
				BoardVO b = new BoardVO();
				b.setBno(rs.getInt("bno"));
				b.setBname(rs.getString("bname"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcont(rs.getString("bcont"));
				b.setBhit(rs.getInt("bhit"));
				b.setBdate(rs.getString("bdate"));
				
				blist.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pt != null)
					pt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return blist;
	}

}
