package potel.account.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.account.dao.AccountDao;
import potel.account.vo.Member;

public class AccountDaoImpl implements AccountDao {

    private HikariDataSource ds;

    public AccountDaoImpl() {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://114.32.203.170:3306/potel");
        ds.setUsername("root");
        ds.setPassword("TIP102_25541859101");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public List<Member> selectAll() {
        String sql = "SELECT * FROM forum"; // 可考慮只選擇必要的欄位來提高效能
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
        String sql = "INSERT INTO members (name, cellphone, address, gender, birthday, email, passwd, imageid, status, createdate, modifydate) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getCellphone());
            stmt.setString(3, member.getAddress());
            stmt.setObject(4, member.getGender()); // 使用 setObject 來處理 Character 類型
            stmt.setString(5, member.getBirthday());
            stmt.setString(6, member.getEmail());
            stmt.setString(7, member.getPasswd());
            stmt.setInt(8, member.getImageid());
            stmt.setObject(9,member.getStatus()); // 使用 setObject 來處理 Character 類型
            stmt.setTimestamp(10,member.getCreatedate());
            stmt.setTimestamp(11,member.getModifydate());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Member> selectAccount() {
        // 這裡可以實現選擇帳戶的邏輯
        return null;
    }
}
