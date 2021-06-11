package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.TblAttach;
import jdbc.JdbcUtil;

public class TblAttachDao {

	public int insert(Connection conn, TblAttach tblAttach) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into tbl_attach "
					+ "(fullName, bno) values (?,?)");
			pstmt.setString(1, tblAttach.getFullName());
			pstmt.setInt(2, tblAttach.getBno());
			int insertedCount = pstmt.executeUpdate();

			return insertedCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}


	public List<TblAttach> selectByBno(Connection conn, int bno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from tbl_attach " +
					"where bno = ? " +
					"order by regdate desc");
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			List<TblAttach> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertTblAttach(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}	
	
	private TblAttach convertTblAttach(ResultSet rs) throws SQLException {
		return new TblAttach(
				rs.getString("fullName"),
				rs.getInt("bno"),
				toDate(rs.getTimestamp("regdate")));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}


}
