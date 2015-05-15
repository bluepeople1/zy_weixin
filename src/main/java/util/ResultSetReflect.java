package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateUtils;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author java
 * 
 */
public class ResultSetReflect {

	/**
	 * RS和Json转换 
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getJSONArray(ResultSet rs) throws Exception {
		JSONArray res = new JSONArray();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			if (0 == metaData.getColumnCount()) {
				throw new Exception("列数为0");
			}
			while (rs.next()) {
				JSONObject json = new JSONObject();
				for (int j = 0; j < metaData.getColumnCount(); j++) {
					int i = j + 1;
					String metaColName = metaData.getColumnName(i)
							.toLowerCase();
					int columType = metaData.getColumnType(i);
					switch (columType) {
					case Types.TIMESTAMP:
						json.put(metaColName, DateFormat.dateFormat(rs
								.getTimestamp(i), "yyyy-MM-dd HH:mm:ss"));
						break;
					case Types.VARCHAR:
						json.put(metaColName, (rs.getString(i)==null || rs.getString(i).equals("null"))?"":rs.getString(i));
						break;
					case Types.CHAR:
						json.put(metaColName, (rs.getString(i)==null || rs.getString(i).equals("null"))?"":rs.getString(i));
						break;
					case Types.INTEGER:
						json.put(metaColName, rs.getInt(i));
						break;
					case Types.FLOAT:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),2));
						break;
					case Types.DOUBLE:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),2));
						break;
					case Types.DATE:
						json.put(metaColName, DateFormat.dateFormat(rs
								.getTimestamp(i), "yyyy-MM-dd HH:mm:ss"));
						break;
					case Types.NUMERIC:
						if("dwsjccl".equalsIgnoreCase(metaColName))
						{
							json.put(metaColName, NumberFormat.dblFromat(rs
									.getDouble(i),3));
						}
						else
						{
							json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),2));
						}
						break;
					case Types.DECIMAL:
						json.put(metaColName, NumberFormat.numberFormat(rs
								.getDouble(i), 2));
						break;
					case Types.NULL:
						json.put(metaColName, "");
						break;
					default:
						System.out.println(columType + "  " + metaColName);
						break;
					}

				}
				res.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public static JSONArray getJSONArray(ResultSet rs,int dblf) throws Exception {
		JSONArray res = new JSONArray();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			if (0 == metaData.getColumnCount()) {
				throw new Exception("列数为0");
			}
			while (rs.next()) {
				JSONObject json = new JSONObject();
				for (int j = 0; j < metaData.getColumnCount(); j++) {
					int i = j + 1;
					String metaColName = metaData.getColumnName(i)
							.toLowerCase();
					int columType = metaData.getColumnType(i);
					switch (columType) {
					case Types.TIMESTAMP:
						json.put(metaColName, DateFormat.dateFormat(rs
								.getTimestamp(i), "yyyy-MM-dd HH:mm:ss"));
						break;
					case Types.VARCHAR:
						json.put(metaColName, rs.getString(i)==null?"":rs.getString(i));
						break;
					case Types.CHAR:
						json.put(metaColName, rs.getString(i)==null?"":rs.getString(i));
						break;
					case Types.INTEGER:
						json.put(metaColName, rs.getInt(i));
						break;
					case Types.FLOAT:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),dblf));
						break;
					case Types.DOUBLE:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),dblf));
						break;
					case Types.DATE:
						json.put(metaColName, DateFormat.dateFormat(rs
								.getTimestamp(i), "yyyy-MM-dd HH:mm:ss"));
						break;
					case Types.NUMERIC:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),dblf));
						break;
					case Types.DECIMAL:
						json.put(metaColName, NumberFormat.numberFormat(rs
								.getDouble(i), dblf));
						break;
					case Types.NULL:
						json.put(metaColName, "");
						break;
					default:
						System.out.println(columType + "  " + metaColName);
						break;
					}

				}
				res.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public static JSONArray getJSONArray(ResultSet rs,String dateFormat) throws Exception {
		JSONArray res = new JSONArray();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			if (0 == metaData.getColumnCount()) {
				throw new Exception("列数为0");
			}
			while (rs.next()) {
				JSONObject json = new JSONObject();
				for (int j = 0; j < metaData.getColumnCount(); j++) {
					int i = j + 1;
					String metaColName = metaData.getColumnName(i)
							.toLowerCase();
					int columType = metaData.getColumnType(i);
					switch (columType) {
					case Types.TIMESTAMP:
						json.put(metaColName, DateFormat.dateFormat(rs
								.getTimestamp(i), dateFormat));
						break;
					case Types.VARCHAR:
						json.put(metaColName, rs.getString(i)==null?"":rs.getString(i));
						break;
					case Types.CHAR:
						json.put(metaColName, rs.getString(i)==null?"":rs.getString(i));
						break;
					case Types.INTEGER:
						json.put(metaColName, rs.getInt(i));
						break;
					case Types.FLOAT:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),2));
						break;
					case Types.DOUBLE:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),2));
						break;
					case Types.DATE:
						json.put(metaColName, DateFormat.dateFormat(rs
								.getTimestamp(i), dateFormat));
						break;
					case Types.NUMERIC:
						json.put(metaColName, NumberFormat.dblFromat(rs
								.getDouble(i),2));
						break;
					case Types.DECIMAL:
						json.put(metaColName, NumberFormat.numberFormat(rs
								.getDouble(i), 2));
						break;
					case Types.NULL:
						json.put(metaColName, "");
						break;
					default:
						System.out.println(columType + "  " + metaColName);
						break;
					}

				}
				res.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * RS和Class转换
	 * 
	 * @param clazz
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static List getBeanCollection(Class clazz, ResultSet rs)
			throws Exception {
		Method[] methods = clazz.getMethods();
		List<Method> setterMethods = new ArrayList<Method>();
		Method m = null;
		List result = new ArrayList();
		for (int i = 0; i < methods.length; i++) {
			m = methods[i];
			if (m.getName().startsWith("set")) {
				setterMethods.add(m);
			}
		}
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			if (setterMethods.size() != metaData.getColumnCount()) {
				throw new Exception("列数不一致");
			}
			while (rs.next()) {
				Object object = clazz.newInstance();
				for (int j = 0; j < metaData.getColumnCount(); j++) {
					for (int i = 0; i < setterMethods.size(); i++) {
						m = (Method) setterMethods.get(i);
						String type = m.getParameterTypes()[0].getName();
						String columnName = m.getName().substring(3)
								.toLowerCase();
						String metaColName = metaData.getColumnName(j + 1);
						if (columnName.equals(metaColName.toLowerCase())) {
							if ("java.lang.Integer".equals(type)
									|| "int".equals(type)) {
								m.invoke(object, Integer.valueOf(rs
										.getString(columnName)));
							} else if ("java.lang.Long".equals(type)
									|| type.equals(long.class)) {
								m.invoke(object, Integer.valueOf(rs
										.getString(columnName)));
							} else if ("java.lang.Double".equals(type)
									|| type.equals(double.class)) {
								m.invoke(object, Double.valueOf(rs
										.getString(columnName)));
							} else if ("java.lang.String".equals(type)) {
								m.invoke(object, rs.getString(columnName));
							} else if ("java.util.Date".equals(type)) {
								m.invoke(object, rs.getDate(columnName));
							} else if ("java.lang.Double".equals(type)) {
								m.invoke(object, rs.getDouble(columnName));
							} else {
								m.invoke(object, rs.getObject(columnName));
							}
							break;
						}
					}
				}
				result.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
