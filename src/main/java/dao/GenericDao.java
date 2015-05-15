package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @Type Name: GenericDao
 * @Type descriptions: 通用泛型Dao，提供持久化操作的通用接口，该接口的每个方法签名都将被添加到基础事务中，期望每个Dao接口继承该接口
 * @Creater:hanzn
 * @Date Created:Oct 31, 2011
 * 
 * @Modifier:
 * @Date Modified:
 * @Modification Reasons:
 * 
 * @Version: 1.0
 */
public interface GenericDao<T> {

	/**
	 * 
	 * @return 查询结果列表
	 * @Method Descriptions: 查询所有对象的列表
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	List<T> getAll();

	/**
	 * 
	 * @param id
	 *            持久化对象的标志
	 * @return 查询到的对象，为查找到则为null
	 * @Method Descriptions: 根据对象的持久化标识，查询该对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	T getById(Serializable id);

	/**
	 * 
	 * @param obj
	 *            需要保存或更新的持久化对象
	 * @return 保存成功或失败标志
	 * @Method Descriptions: 保存或者更新需要持久化的对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	boolean saveOrUpdate(T obj);
	

	/**
	 * 
	 * @param collection
	 *            需要保存或更新的集合列表
	 * @return 是否保存或者更新成功
	 * @Method Descriptions: 保存或者更新集合中的对象
	 * @Date Created:Nov 9, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	boolean saveOrUpdateCollection(Collection<T> collection);

	/**
	 * 
	 * @param obj
	 *            需要删除的<b>持久态</b>的对象
	 * @return 是否删除成功
	 * @Method Descriptions: 删除持久化的某个对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	boolean remove(T obj);

	/**
	 * 
	 * @param id
	 *            需要删除的持久化对象的标识
	 * @return 是否删除成功
	 * @Method Descriptions: 根据持久化对象的标识删除某个对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	boolean removeById(Serializable id);

	/**
	 * 
	 * @param hql
	 *            查询的HQL语句<br>
	 *            Example:from user u where u.org=? and u.fty=?
	 * @param values
	 *            HQL语句所匹配的参数列表<br>
	 *            Example:new String[]{org,fty}
	 * @return 查找到的对象列表
	 * @Method Descriptions: 根据HQL语句与条件查找对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	List<T> find(String hql, Object... values);

	/**
	 * 
	 * @param hql
	 *            查询的HQL语句<br>
	 *            Example:from user u where u.name=? and u.pwd=?
	 * @param values
	 *            HQL语句所匹配的参数列表<br>
	 *            Example:new String[]{name,pwd}
	 * @return 匹配的唯一对象
	 * @Method Descriptions: 根据HQL来查询唯一符合条件的对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	T findUnique(String hql, Object... values);

	/**
	 * 
	 * @param propertyName
	 *            对象的属性名
	 * @param value
	 *            属性名对应的值
	 * @return 匹配的唯一对象
	 * @Method Descriptions: 根据属性名匹配属性值来获取唯一符合条件的对象
	 * @Date Created:Oct 31, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	T findUnique(String propertyName, Object value);
	
	/**
	 * 
	 *
	 *@param propertyName 属性名称
	 *@param value 属性值
	 *@return List<T> 对象列表
	 *@Method Descriptions:
	 * 
	 *@Date:Apr 11, 2012 4:56:34 PM
	 *@Creater:MagicH
	 *
	 *@Modifier:
	 *@Date Modifier:
	 *@Modification Reasons:
	 *
	 */
	List<T> findList(final String propertyName, final Object value);
	/**
	 * 更新实体
	 * 
	 * @author
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 存储实体到数据库
	 * 
	 * @author
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除指定的实体
	 * 
	 * @author
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 根据主键删除指定实体
	 * 
	 * @author
	 * @param id
	 */
	void deleteByKey(Serializable id);

	/**
	 * 删除集合中的全部实体
	 * 
	 * @author
	 * @param entities
	 */
	void deleteAll(Collection<T> entities);

	/**
	 * 根据主键获取实体。如果没有相应的实体，返回 null。
	 * 
	 * @author
	 * @param id
	 * @return
	 */
	T get(Serializable id);

	/**
	 * 以分页方式查找对象
	 * 
	 * @author
	 * @param hql
	 *            HQL语句
	 * @param firstResult
	 *            起始行
	 * @param maxResults
	 *            每页记录数
	 * @return
	 */
	List<T> findObjectsByPage(final String hql, final int firstResult,
							  final int maxResults);
	
	/**
	 * 
	 *
	 *@param hql 要执行的hql
	 *@return long hql的结果集数
	 *@Method Descriptions:
	 * 执行hql，返回其结果数量
	 *@Date:Jun 12, 2012 9:39:10 AM
	 *@Creater:MagicH
	 *
	 *@Modifier:
	 *@Date Modifier:
	 *@Modification Reasons:
	 *
	 */
	long getNumberOfResult(final String hql);

}
