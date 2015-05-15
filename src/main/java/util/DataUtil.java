package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceUtils;
/**
 * 
 * @Project Name: alpmes
 * @Type Name: DataUtil
 * @Type descriptions:
 * 通过获取配置文件中的datasource来获取数据库连接等信息
 * <b>注意此类需要依赖Spring FrameWork 和 ProxoolDataSource第三方包</b>
 * @Creater:hanzn
 * @Date Created:Dec 26, 2011
 *
 * @Modifier:
 * @Date Modified:
 * @Modification Reasons:
 *
 * @Version: 1.0
 */
public class DataUtil implements ApplicationContextAware{
	private static ApplicationContext context;
	//注入ApplicationContext
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
	}
	/**
	 * 
	 * @param dataSourceName spring配置文件中的DataSource Bean名称
	 * @return 获取的javax.sql.DataSource
	 * @Method Descriptions:
	 * 从spring配置文件中通过dataSource名称来获取一个DataSource
	 * @Date Created:Dec 26, 2011
	 * @Creater:hanzn
	 *
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 *
	 *
	 */
	public static DataSource getDataSourceFromBean(String dataSourceName) {
		String dsname = dataSourceName;
		if (dataSourceName == null || "".equals(dataSourceName.trim())) {
			dsname = "baseDataSource";
		}
		DataSource ds = (DataSource)context.getBean(dsname);
		return ds;
	}
	/**
	 * 
	 * @param dataSourceName spring配置文件中的DataSource Bean名称
	 * @return 一个基于ProxoolDataSource的数据库连接
	 * @Method Descriptions:
	 * 从spring配置文件中通过dataSource名称来获取一个此数据源的数据连接
	 * @Date Created:Dec 26, 2011
	 * @Creater:hanzn
	 *
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 *
	 */
	public static Connection getConnectionFromDataSource(String dataSourceName) {
		return DataSourceUtils.getConnection(getDataSourceFromBean(dataSourceName));
	}
	/**
	 * 关闭数据库连接
	 * @param con
	 * @param rs
	 * @param st
	 */
	public static  void closeConn(Connection con, ResultSet rs, Statement st) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
