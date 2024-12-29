package potel.utils;

import com.zaxxer.hikari.HikariDataSource;

public class JDBCConstants {
	private static HikariDataSource ds;
	public static final String URL = "jdbc:mysql://114.32.203.170:3306/potel";
//	public static final String URL = "jdbc:mysql://localhost:3306/example";
	public static final String USER = "root";
	public static final String PASSWORD = "TIP102_25541859101";
//	public static final String PASSWORD = "abcd1234";
	
	public static synchronized HikariDataSource getDataSource() {
		
		if(ds == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			ds = new HikariDataSource();
			ds.setJdbcUrl(URL);
			ds.setUsername(USER);
			ds.setPassword(PASSWORD);
			// 最小空閒連線數
			ds.setMinimumIdle(5);
			// 最大連線數
			ds.setMaximumPoolSize(10);
			// 啟⽤預編譯敘述快取
			ds.addDataSourceProperty("cachePrepStmts", true);
			// 設定最多可保存的預編譯敘述數量
			ds.addDataSourceProperty("prepStmtCacheSize", 250);
			// 設定預編譯敘述⻑度上限
			ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		}
		return ds;
	}
}
