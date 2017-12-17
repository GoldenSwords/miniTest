package luo.system.util;

public class JDBCUtil {
    //        //获取认证
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            System.out.println("begin.");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1521;databaseName=localdb;user=sa;password=12345678");
//            System.out.println("end.");
//            String SQL = "SELECT TOP 10 * FROM users";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(SQL);
//            while (rs.next()) {
//                System.out.println(rs.getString(4) + " " + rs.getString(6));
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        finally {
//            if (rs != null)
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                }
//            if (stmt != null)
//                try {
//                    stmt.close();
//                } catch (Exception e) {
//                }
//            if (con != null)
//                try {
//                    con.close();
//                } catch (Exception e) {
//                }
//        }
}
