package potel.shopping.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import potel.shopping.Dao.ListDao;
import potel.shopping.Vo.Product;

public class ListDaoImpl implements ListDao {
	private HikariDataSource ds;

	public ListDaoImpl() {
		// 建議將數據源配置移到配置類中
		ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:mysql://114.32.203.170:3306/potel");
		ds.setUsername("root");
		ds.setPassword("TIP102_25541859101");
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	}

	@Override
	public List<Product> selectAll() {
		String sql = "select * from products";
		List<Product> list = new ArrayList<>();
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Product product = new Product();
				product.setPrdName(rs.getString("PRDNAME"));
				product.setPrice(rs.getInt("PRICE"));
				product.setImageId(rs.getInt("IMAGEID"));
				product.setPrdDesc(rs.getString("PRDDESC"));
				list.add(product);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int insert(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletebyId(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product selectByUsernameAndPassword(Product product) {
		// TODO Auto-generated method stub
		return null;
	}
}
