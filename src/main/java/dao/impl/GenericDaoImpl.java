package dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.GenericDao;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import util.DataUtil;
import util.GenericUtils;
import util.ResultSetReflect;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T> extends HibernateDaoSupport implements
		GenericDao<T> {

	private Class<T> entityClass;

	public GenericDaoImpl() {
		// java 添加
		entityClass = GenericUtils.getSuperClassGenricType(getClass(), 0);
		// java 添加
	}

	public List<T> find(String hql, Object... values) {
		return getHibernateTemplate().find(hql, values);
	}

	public T findUnique(String hql, Object... values) {
		List<T> list = getHibernateTemplate().find(hql, values);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public T findUnique(final String propertyName, final Object value) {
		return (T) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(entityClass)
						.add(Restrictions.eq(propertyName, value))
						.uniqueResult();
			}
		});
	}

	public List<T> findList(final String propertyName, final Object value) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createCriteria(entityClass)
								.add(Restrictions.eq(propertyName, value))
								.list();
					}
				});
	}

	/**
	 * 根据列propertyName查询结果，并根据列order排序
	 * 
	 * @author 蔡仲瑞 2012年9月13日10:13:17
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findListAsc(final String propertyName, final Object value,
			final String order, final boolean asc) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session
								.createCriteria(entityClass)
								.addOrder(
										asc ? Order.asc(order) : Order
												.desc(order))
								.add(Restrictions.eq(propertyName, value))
								.list();
					}
				});
	}

	/**
	 * java 添加
	 * 
	 * @param clazz
	 * @return
	 */
	// 返回主键Name
	public String getIdName(Class clazz) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		return meta.getIdentifierPropertyName();
	}

	// 根据传入的实体bean对象来检索返回单个实体 可排序
	public T getByEntity(JSONObject obj, String order, boolean asc) {
		if (obj != null) {
			Criteria criteria = getSession().createCriteria(entityClass).add(
					Restrictions.allEq(obj));
			criteria.addOrder(asc ? Order.asc(order) : Order.desc(order));
			return (T) criteria.setMaxResults(1).uniqueResult();
		}
		return null;
	}

	// 根据传入的实体bean对象来检索返回单个实体
	public T getByEntity(JSONObject obj) {
		if (obj != null) {
			Criteria criteria = getSession().createCriteria(entityClass).add(
					Restrictions.allEq(obj));
			return (T) criteria.setMaxResults(1).uniqueResult();
		}
		return null;
	}

	// 根据传入的实体bean对象来检索返回多个实体 可排序
	public List<T> getByEntityList(JSONObject obj, String order, boolean asc) {
		if (obj != null) {
			Criteria criteria = getSession().createCriteria(entityClass).add(
					Restrictions.allEq(obj));
			criteria.addOrder(asc ? Order.asc(order) : Order.desc(order));
			return criteria.list();
		}
		return null;
	}

	// 根据传入的实体bean对象来检索返回多个实体
	public List<T> getByEntityList(JSONObject obj) {
		if (obj != null) {
			Criteria criteria = getSession().createCriteria(entityClass).add(
					Restrictions.allEq(obj));
			return criteria.list();
		}
		return null;
	}

	// 执行本地sql,自动将Rs转换成JSONArray(缺陷：返回的json key值是传入sql的select的属性)
	// 详细的转换时间和数字格式查看ResultSetReflect
	public JSONArray getJsonArray(String sql) {
		Connection con = DataUtil.getConnectionFromDataSource("baseDataSource");
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			return ResultSetReflect.getJSONArray(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.closeConn(null, rs, st);
		}
		return null;
	}

	// 执行本地sql,自动将Rs转换成JSONArray(缺陷：返回的json key值是传入sql的select的属性)
	// 详细的转换时间和数字格式查看ResultSetReflect、数字格式化
	public JSONArray getJsonArray(String sql, int dbl) {
		Connection con = DataUtil.getConnectionFromDataSource("baseDataSource");
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			return ResultSetReflect.getJSONArray(rs, dbl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.closeConn(null, rs, st);
		}
		return null;
	}

	public JSONArray getJsonArray(String sql, String dateFormat) {
		Connection con = DataUtil.getConnectionFromDataSource("baseDataSource");
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			return ResultSetReflect.getJSONArray(rs, dateFormat);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.closeConn(null, rs, st);
		}
		return null;
	}

	// 执行本地sql,执行更新或插入操作
	// modify:2012年9月12日17:51:50
	// 修改方法名
	public boolean executeSql(String sql) {
		Connection con = DataUtil.getConnectionFromDataSource("baseDataSource");
		Statement st = null;
		try {
			st = con.createStatement();
			return st.executeUpdate(sql) >= 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.closeConn(null, null, st);
		}
		return false;
	}

	/**
	 * 执行Insert 返回自增的主键ID
	 * 
	 * @param sql
	 * @return
	 */
	public Serializable getInsertKey(String sql) {
		Connection con = DataUtil.getConnectionFromDataSource("baseDataSource");
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.closeConn(null, rs, st);
		}
		return -1;
	}

	/**
	 * 批量执行本地sql,执行更新或插入操作
	 * 
	 * @param sqlList
	 * @return
	 */
	public boolean executeSql(List<String> sqlList) {
		Connection con = DataUtil.getConnectionFromDataSource("baseDataSource");
		Statement st = null;
		try {
			st = con.createStatement();
			for (String sql : sqlList) {
				st.addBatch(sql);
			}
			st.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DataUtil.closeConn(null, null, st);
		}
		return true;
	}

	/**
	 * CAI 查询实时库数据
	 */
	// public JSONArray getRealDataArray(String sql){
	// // System.out.println("执行查询");
	// Connection con = DataUtil.getConnectionFromDataSource("realDataSource1");
	// Statement st = null;
	// ResultSet rs = null;
	// try {
	// // System.out.println("111111");
	// st = con.createStatement();
	// // System.out.println("sql"+sql);
	// rs = st.executeQuery(sql);
	// JSONArray result = ResultSetReflect.getJSONArray(rs);
	// // System.out.println("获取记录"+result.size());
	// return result;
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// DataUtil.closeConn(con, rs, st);
	// }
	// return null;
	// String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// String dbURL = "jdbc:sqlserver://192.168.0.109:1433;DatabaseName=mes";
	// String userName = "alp";
	// String userPwd = "alp";
	// Connection dbConn = null;
	// Statement stmt = null;
	// ResultSet rs = null;
	// try {
	// // Class.forName("net.sourceforge.jtds.jdbc.Driver");
	// Class.forName(driverName);
	// //dbConn =
	// DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/TEST",userName,userPwd)
	// ;
	// dbConn = DriverManager.getConnection(dbURL,userName,userPwd) ;
	// stmt = dbConn.createStatement();
	// rs = stmt.executeQuery(sql);
	// return ResultSetReflect.getJSONArray(rs);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally{
	//
	// try {
	// rs.close();
	// stmt.close();
	// dbConn.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }
	/**
	 * java 添加
	 * 
	 * @return
	 */

	public List<T> getAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public T getById(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public boolean remove(T obj) {
		boolean isSuccess = false;
		getHibernateTemplate().delete(obj);
		isSuccess = true;
		return isSuccess;
	}

	public boolean removeById(Serializable id) {
		boolean isSuccess = false;
		getHibernateTemplate().delete(this.getById(id));
		isSuccess = true;
		return isSuccess;
	}

	public boolean saveOrUpdate(T obj) {
		boolean isSuccess = false;
		getHibernateTemplate().saveOrUpdate(obj);
		isSuccess = true;
		return isSuccess;
	}

	/**
	 * 蔡仲瑞 添加 更新或保存实体对象，返回保存后的对象 2012年9月12日17:57:34
	 * 
	 * @param obj
	 * @return obj
	 */
	public T saveOrUpdateReturnObject(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
		return obj;
	}

	public boolean saveOrUpdateCollection(Collection<T> collection) {
		boolean isSuccess = false;
		getHibernateTemplate().saveOrUpdateAll(collection);
		isSuccess = true;
		return isSuccess;
	}

	// 更新实体
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	// 存储实体到数据库
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	// 删除指定的实体
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	// 根据主键删除指定实体
	public void deleteByKey(Serializable id) {
		this.delete(this.load(id));
	}

	public List<T> findObjectsByPage(final String hql, final int firstResult,
			final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = s.createQuery(hql);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				List<T> list = query.list();
				return list;
			}
		});
	}

	public long getNumberOfResult(String hql) {
		List list = getHibernateTemplate().find("select count(*) " + hql);
		return ((Long) list.get(0)).intValue();
	}

	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	// 删除集合中的全部实体
	public void deleteAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);
	}



	public boolean saveOrUpdateObjectCollection(Collection collection) {
		boolean isSuccess = false;
		getHibernateTemplate().saveOrUpdateAll(collection);
		isSuccess = true;
		return isSuccess;
	}

	// 删除集合中的全部实体
	public boolean deleteAll(JSONArray array) {
		try {
			List<T> list = new ArrayList<T>();
			for (Object object : array) {
				T t = entityClass.newInstance();
				BeanUtils.copyProperties(t, object);
				list.add(t);
			}
			getHibernateTemplate().deleteAll(list);
		} catch (Exception e) {
			return false;
		}
		return true;

	}


}
