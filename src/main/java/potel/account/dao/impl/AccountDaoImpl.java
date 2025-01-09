package potel.account.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import potel.account.dao.AccountDao;

import potel.account.vo.Member;

public class AccountDaoImpl implements AccountDao {
	private DataSource ds;

	public AccountDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/potel");
	}

	@Override
	public List<Member> selectAll() {
		String sql = "SELECT * FROM account";
		List<Member> members = new ArrayList<>();

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			System.out.println("Database connection established.");

			while (rs.next()) {
				Member member = new Member();
				member.setName(rs.getString("name"));
				member.setCellphone(rs.getString("cellphone"));
				member.setAddress(rs.getString("address"));
				member.setBirthday(rs.getString("birthday"));
				member.setEmail(rs.getString("email"));
				member.setPasswd(rs.getString("passwd"));
				members.add(member);
			}

			System.out.println("Finished retrieving accounts.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return members.isEmpty() ? null : members;
	}

	@Override
	public boolean insertMember(Member member) {
		String sql = "INSERT INTO members (name, cellphone, address, gender, birthday, email, passwd, imageid) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getCellphone());
			stmt.setString(3, member.getAddress());
			stmt.setString(4, member.getGender() != null ? member.getGender().toString() : null);
			stmt.setString(5, member.getBirthday());
			stmt.setString(6, member.getEmail());
			stmt.setString(7, member.getPasswd());
			stmt.setInt(8, member.getImageid());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean selectForReset(String email, String cellphone) {
		String sql = "SELECT 1 FROM members Where EMAIL = ?  and CELLPHONE = ? ";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, email);
			pstmt.setString(2, cellphone);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int updatePassword(String password, String email) {
		String sql = "update members set passwd = ? where email = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, password);
			stmt.setString(2, email);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
