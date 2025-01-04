package potel.petsfile.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.petsfile.dao.PetsFileDao;
import potel.petsfile.vo.PetsFile;

public class PetsFileDaoImpl implements PetsFileDao {

    private static final int PetsFile = 0;
	private HikariDataSource ds;

    // 建構子，初始化數據源
    public PetsFileDaoImpl() {
        // 建議將數據源配置移到配置類中
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://114.32.203.170:3306/group");
        ds.setUsername("root");
        ds.setPassword("TIP102_25541859101");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    // 查詢所有寵物資料
    @Override
	public List<PetsFile> selectAllFiles() {

        String sql = "SELECT * FROM PETSFILE"; // 查詢所有寵物資料
        List<PetsFile> pets = new ArrayList<>();
        
        try (Connection conn = ds.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("數據庫連接已建立。");

            // 遍歷結果集，將每一筆資料封裝成 PetsFile 物件
            while (rs.next()) {
                PetsFile pet = new PetsFile();
                pet.setPetId(rs.getInt("PETID"));
                pet.setPetType(rs.getString("PETTYPE"));
                pet.setNickname(rs.getString("NICKNAME"));
                pet.setWeight(rs.getDouble("WEIGHT"));
                pet.setBreed(rs.getString("BREED"));
                pet.setImageId(rs.getInt("IMAGEID"));
                pet.setStatus(rs.getString("STATUS"));
                pet.setCreatedDate(rs.getTimestamp("CREATEDDATE"));
                pet.setModifyDate(rs.getTimestamp("MODIFYDATE"));
                pets.add(pet);
            }
            
            System.out.println("成功從資料庫取得 " + pets.size() + " 隻寵物資料。");
        } catch (SQLException e) {
            // 發生錯誤時，打印錯誤堆疊資訊
            e.printStackTrace();
        }
        
        return pets.isEmpty() ? null : pets;
    }

    // 根據寵物 ID 查詢單隻寵物資料
    @Override
	public PetsFile selectFileById(int id) {

        String sql = "SELECT * FROM PETSFILE WHERE PETID = ?";
        PetsFile pet = null;

        try (Connection conn = ds.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, PetsFile); // 設定查詢的 PETID

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pet = new PetsFile();
                    pet.setPetId(rs.getInt("PETID"));
                    pet.setPetType(rs.getString("PETTYPE"));
                    pet.setNickname(rs.getString("NICKNAME"));
                    pet.setWeight(rs.getDouble("WEIGHT"));
                    pet.setBreed(rs.getString("BREED"));
                    pet.setImageId(rs.getInt("IMAGEID"));
                    pet.setStatus(rs.getString("STATUS"));
                    pet.setCreatedDate(rs.getTimestamp("CREATEDDATE"));
                    pet.setModifyDate(rs.getTimestamp("MODIFYDATE"));
                }
            }

            if (pet != null) {
                System.out.println("成功從資料庫取得一隻寵物資料。");
            } else {
                System.out.println("未找到對應的寵物資料。");
            }
        } catch (SQLException e) {
            // 發生錯誤時，打印錯誤堆疊資訊
            e.printStackTrace();
        }

        return pet;
    }

    // 新增一隻寵物資料
    @Override
	public boolean insertFile(PetsFile petsFile) {
        String sql = "INSERT INTO PETSFILE (PETTYPE, NICKNAME, WEIGHT, BREED, IMAGEID, STATUS, CREATEDDATE, MODIFYDATE) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ds.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, petsFile.getPetType());
            pstmt.setString(2, petsFile.getNickname());
            pstmt.setDouble(3, petsFile.getWeight());
            pstmt.setString(4, petsFile.getBreed());
            pstmt.setInt(5, petsFile.getImageId());
            pstmt.setString(6, petsFile.getStatus());
            pstmt.setTimestamp(7, petsFile.getCreatedDate());
            pstmt.setTimestamp(8, petsFile.getModifyDate());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // 發生錯誤時，打印錯誤堆疊資訊
            e.printStackTrace();
        }

        return false;
    }

    // 更新一隻寵物資料
    @Override
	public boolean updateFile(PetsFile petsFile) {
        String sql = "UPDATE PETSFILE SET PETTYPE = ?, NICKNAME = ?, WEIGHT = ?, BREED = ?, IMAGEID = ?, STATUS = ?, "
                   + "MODIFYDATE = ? WHERE PETID = ?";

        try (Connection conn = ds.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, petsFile.getPetType());
            pstmt.setString(2, petsFile.getNickname());
            pstmt.setDouble(3, petsFile.getWeight());
            pstmt.setString(4, petsFile.getBreed());
            pstmt.setInt(5, petsFile.getImageId());
            pstmt.setString(6, petsFile.getStatus());
            pstmt.setTimestamp(7, petsFile.getModifyDate());
            pstmt.setInt(8, petsFile.getPetId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // 發生錯誤時，打印錯誤堆疊資訊
            e.printStackTrace();
        }

        return false;
    }

    // 刪除一隻寵物資料
    @Override
	public boolean deleteFile(int id) {
        String sql = "DELETE FROM PETSFILE WHERE PETID = ?";

        try (Connection conn = ds.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, PetsFile); // 設定要刪除的 PETID

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // 發生錯誤時，打印錯誤堆疊資訊
            e.printStackTrace();
        }

        return false;
    }
}
