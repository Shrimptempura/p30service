package com.tech.prjm09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.eclipse.tags.shaded.org.apache.bcel.generic.RETURN;

import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.util.DBCon;
import com.tech.prjm09.util.DBUtil;

public class BDao {
	
	Connection conn = null;

	public ArrayList<BDto> list() {

		ArrayList<BDto> dtos = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		Connection conn = null;

		String sql = "select bid, bname, btitle, bcontent, "
				+ "bdate, bhit, bgroup, bstep, bindent "
				+ "from replyboard "
				+ "order by bgroup desc, bstep asc";

		try {
			conn = DBCon.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				BDto bDto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				dtos.add(bDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return dtos;
	}

	public void write(String bname, String btitle, String bcontent) {

		PreparedStatement pstmt = null;
//		Connection conn = null;

		String sql = "insert into replyboard(bid, bname, btitle, " + "bcontent, bdate, bhit, bgroup, bstep, bindent) "
				+ "values(replyboard_seq.nextval, ?, ?, " + "?, sysdate, 0, replyboard_seq.currval, 0, 0)";

		try {
			conn = DBCon.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);

			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	public BDto contentView(String sbid) {

		upHit(sbid);

		BDto dto = null;
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bid, bname, btitle, bcontent, bdate, " + "bhit, bgroup, bstep, bindent "
				+ "from replyboard where bid = ?";

		try {
			conn = DBCon.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return dto;
	}

	public BDto modifyView(String sbid) {

		BDto dto = null;
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bid, bname, btitle, bcontent, bdate, " + "bhit, bgroup, bstep, bindent "
				+ "from replyboard where bid = ?";

		try {
			conn = DBCon.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return dto;
	}

	public void upHit(String sbid) {

//		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBCon.getConnection();
			String query = "update replyboard " + "set bhit = bhit + 1 " + "where bid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(sbid));
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	public void modify(String bid, String bname, String btitle, String bcontent) {

//		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update replyboard set bname = ?, btitle = ?, " + "bcontent = ? where bid = ?";

		try {
			conn = DBCon.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);

			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	public void reply(String bid, String bname, String btitle, String bcontent, 
			String bgroup, String bstep, String bindent) {
		
//		Connection conn = null;
		replyShape(bgroup, bstep);
		PreparedStatement pstmt = null;

		String query = "insert into replyboard(bid, bname, btitle, "
				+ "bcontent, bgroup, bstep, bindent) "
				+ "values(replyboard_seq.nextval, ?, ?, ?, ?, ?, ?)";

		try {
			conn = DBCon.getConnection();
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bgroup));
			pstmt.setInt(5, Integer.parseInt(bstep) + 1);
			pstmt.setInt(6, Integer.parseInt(bindent) + 1);
			
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	public BDto reply_view(String sbid) {

		BDto dto = null;
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select bid, bname, btitle, bcontent, bdate, " 
				+ "bhit, bgroup, bstep, bindent "
				+ "from replyboard where bid = ?";

		try {
			conn = DBCon.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbid));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return dto;
	}
	
	public void replyShape(String strgroup, String strstep) {
		
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int rn = 0;

		String query = "update replyboard set bstep = bstep + 1 "
				+ "where bgroup = ? and bstep > ?";

		try {
			conn = DBCon.getConnection();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strgroup));
			pstmt.setInt(2, Integer.parseInt(strstep));
			
			rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	public void delete(String bid) {
		
		PreparedStatement pstmt = null;
		
		String query = "delete from replyboard where bid = ?";
		
		try {
			conn = DBCon.getConnection();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bid));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

}
