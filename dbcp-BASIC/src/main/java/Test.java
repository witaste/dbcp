import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) throws SQLException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:Beans-DataSource.xml");
		BasicDataSource ds = ctx.getBean("dataSource_TEST", BasicDataSource.class);
		print(ds);
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT 1+1 SUM FROM DUAL";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("SUM"));
		}
		print(ds);
		conn.close();
		print(ds);
	}
	
	public static void print(BasicDataSource ds){
		System.out.println("using :"+ds.getNumActive()+" usable:"+ds.getNumIdle());
	}
}
