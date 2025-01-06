package potel.account.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import potel.account.dao.AccountDao;
import potel.account.vo.AccountBean;

public class AccountDaoImpl implements AccountDao{
	private DataSource ds;
	
	public AccountDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/potel");
	}
	
	@Override
	public AccountBean login(String loginid, String password) {
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from MEMBERS where (email=? or cellphone=?) and passwd=?");){
			int pind = 1;
			pstmt.setString(pind++, loginid);
			pstmt.setString(pind++, loginid);
			pstmt.setString(pind++, password);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					String email = rs.getString("EMAIL");
					AccountBean bean = new AccountBean(email);
					return bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
