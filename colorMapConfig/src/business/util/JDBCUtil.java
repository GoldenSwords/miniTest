package business.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	/**
	 * 数据库链接
	 *
	 * @return
	 */
	public static Connection getDbConnect() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1521;DatabaseName=localdb";
		String userName = "sa";
		String userPwd = "12345678";
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("连接失败");
		}
		return dbConn;
	}
	/**
	 * 数据库链接
	 *
	 * @return
	 */
	public static Connection getDbConnect(String driverName,String dbURL,String userName,String userPwd) {
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("连接失败");
		}
		return dbConn;
	}
	/**
	 * 获取数据库 GIS配置参数
	 *
	 * @param sql SQL
	 * @return
	 */
	public static List<Map<String, Object>> queryForList(Connection connection,String sql)throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);// 实例化预编译语句
			ResultSet result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			int number = 0;
			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				ResultSetMetaData resultSetMetaData = result.getMetaData();
				int columnsNumber = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= columnsNumber; i++) {
					String key = resultSetMetaData.getColumnName(i);
					map.put(key, result.getString(key));
				}
				number++;
				list.add(map);
			}
			result.close();
			pre.close();
		}finally {
			connection.close();
		}
		return list;
	}
	/**
	 * 批量执行sql
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int[] excuteBatchSql(Connection connection,String sql,List<Object[]> params)throws SQLException{
		int[] rNumber;
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			for (Object[] obj:params) {
				for(int i=1;i<=obj.length;i++){
					pre.setObject(i,obj[i-1]);
				}
				pre.addBatch();
			}
			rNumber = pre.executeBatch();
			pre.close();
		}finally {
			connection.close();
		}
		return rNumber;
	}
	/**
	 * 批量执行sql
	 * @param connection
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	public static int[] excuteBatchSql(Connection connection,List<String> sqls)throws SQLException{
		int[] rNumber;
		try {
			Statement pre = connection.createStatement();
			for (int i = 0; i < sqls.size(); i++) {
				pre.addBatch(sqls.get(i));
			}
			rNumber = pre.executeBatch();
			pre.close();
		}finally {
			connection.close();
		}
		return rNumber;
	}

	/**
	 * 执行sql
	 * @param connection
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int excuteSql(Connection connection,String sql)throws SQLException{
		int rNumber=-1;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);// 实例化预编译语句
			rNumber = pre.executeUpdate();// 执行查询，注意括号中不需要再加参数
			pre.close();
		}finally {
			connection.close();
		}
		return rNumber;
	}
	/**
	 * 获取数据库 GIS配置参数
	 *
	 * @param sql SQL
	 * @return
	 */
	public static Map<String, Object> queryForMap(Connection connection,String sql)throws SQLException,Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);// 实例化预编译语句
			ResultSet result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			int number = 0;
			while (result.next()){
				if(number!=0){
					throw new Exception("返回结果超过了一条数据,请检查");
				}
				ResultSetMetaData resultSetMetaData = result.getMetaData();
				int columnsNumber = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= columnsNumber; i++) {
					String key = resultSetMetaData.getColumnName(i);
					map.put(key, result.getString(key));
				}
				number++;
			}
			result.close();
			pre.close();
		}finally {
			connection.close();
		}
		return map;
	}

//	public static void main(String[] args) throws Exception{
//		Map<String, Object> MAP = queryForMap(getDbConnect(),"select count(*) from result");
////		List<Map<String, Object>> LIST = queryForList(getDbConnect(),"select * from result");
////		int insert = excuteSql(getDbConnect(),"select count(*) from result");
//		List<Object[]> sqls = new ArrayList<>();
//		sqls.add(new Object[]{5,"fff"});
//		sqls.add(new Object[]{6,"qqq"});
//		int[] insert = excuteBatchSql(getDbConnect(),"insert into  result(id,text)values(?,?)",sqls);
//		System.out.println("sss");
//	}
}
