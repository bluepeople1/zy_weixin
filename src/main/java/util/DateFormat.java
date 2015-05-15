package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateFormat {
	/**
	 * 根据年度和月份获得本月最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		// c.set(Calendar.YEAR, year);
		c.set(year, month - 1, 1);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 格式化日期为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");
		return date == null ? "" : sdf.format(date);
	}

	/**
	 * 格式化日期为HH：mm：ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToHmsFormat(Date date) {
		SimpleDateFormat sdfhms24 = new SimpleDateFormat("HH:mm:ss");
		return date == null ? "" : sdfhms24.format(date);
	}

	/**
	 * 格式化日期为所定义的格式
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date, String s) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(s);
		return date == null ? "" : sdf.format(date);
	}

	/**
	 * 格式化日期为yyyy-MM-dd
	 * 
	 * @param year
	 * @param m
	 * @param d
	 * @return
	 */
	public static String dateStr(String year, String m, String d) {
		String month = m;
		String dd = d;
		if (m.length() < 2) {
			month = "0" + m;
		}
		if (d.length() < 2) {
			dd = "0" + d;
		}
		return year + "-" + month + "-" + dd;
	}

	/**
	 * 将字符串解析为日期
	 * 
	 * @param s
	 *            日期字符串：格式为yyyy-MM-dd
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateFormat(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.parse(s);
	}

	/**
	 * 根据指定格式,将字符串解析为日期
	 * 
	 * @param s
	 *            日期字符串
	 * @param pattern
	 *            格式
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateFormat(String s, String pattern)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		return sdf.parse(s);
	}

	/**
	 * 根据指定格式,将字符串解析为日期,提取月份信息
	 * 
	 *            日期字符串
	 *            格式
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String dateMFormat(Date d) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			sdf.applyPattern("MM");
			return sdf.format(d);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 将日期解析为字符串
	 * 
	 * @param d
	 *            日期字符串：格式为yyyy-MM-dd hh:mm:ss（12小时 hh;24小时 HH）
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String dateTosFormat(Date d) throws ParseException {
		SimpleDateFormat sdf24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		if (d == null) {
			return "";
		}
		return sdf24.format(d);
	}

	/**
	 * 将字符串解析为日期
	 * 
	 * @param d
	 *            日期字符串：格式为yyyy-MM-dd hh:mm:ss（12小时 hh;24小时 HH）
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateTodFormat(String d) throws ParseException {
		SimpleDateFormat sdf24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf24.parse(d);
	}

	/**
	 * 将字符串解析为日期
	 * 
	 * @param d
	 *            日期字符串：格式为yyyy-MM-dd hh:mm:ss（12小时 hh;24小时 HH）
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateTodNullFormat(String d) throws ParseException {
		SimpleDateFormat sdf24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("".equals(d) == true) {
			return null;
		}
		return sdf24.parse(d);
	}

	/**
	 * 对日期年、月、日、时、分、秒提前或延后
	 * 
	 * @author zj
	 * @since Apr 26, 2011
	 * @param d
	 *            2011-1-1 11:11:11
	 * @param lx
	 *            类型：y m d h mm s
	 * @param v
	 *            数值
	 * @return Date
	 * @throws java.text.ParseException
	 */
	public static Date dateCalTodFormat(Date d, String lx, int v)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		Date rDate = d;
		if ("y".equalsIgnoreCase(lx)) {
			calendar.add(Calendar.YEAR, v);
		}
		if ("m".equalsIgnoreCase(lx)) {
			calendar.add(Calendar.MONTH, v);
		}
		if ("d".equalsIgnoreCase(lx)) {
			calendar.add(Calendar.DAY_OF_MONTH, v);
		}
		if ("h".equalsIgnoreCase(lx)) {
			calendar.add(Calendar.HOUR_OF_DAY, v);
		}
		if ("mm".equalsIgnoreCase(lx)) {
			calendar.add(Calendar.MINUTE, v);
		}
		if ("s".equalsIgnoreCase(lx)) {
			calendar.add(Calendar.SECOND, v);
		}
		rDate = calendar.getTime();
		return rDate;
	}

	/**
	 * 得到上一个月的28号
	 * 
	 * @return
	 */
	public static String getMonthFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 当前日期<29时，返回上个月的28号;>29时，返回本月的28号
		int dd = c.get(Calendar.DATE);
		if (dd < 29) {
			c.add(Calendar.MONTH, -1);
			int y = c.get(Calendar.YEAR);// 年
			int M = c.get(Calendar.MONTH) + 1;// 月,注意这里要加1,计算机第一个月从0开始
			int d = c.get(Calendar.DATE);// 日
			return y + "-" + M + "-" + "28";
		} else {
			int y = c.get(Calendar.YEAR);// 年
			int M = c.get(Calendar.MONTH) + 1;// 月,注意这里要加1,计算机第一个月从0开始
			int d = c.get(Calendar.DATE);// 日
			return y + "-" + M + "-" + "28";
		}
	}

	/**
	 * 得到下一个月的28号
	 */
	public static String getMonthLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		int y = c.get(Calendar.YEAR);// 年
		int M = c.get(Calendar.MONTH) + 1;// 月,注意这里要加1,计算机第一个月从0开始
		// int d = c.get(Calendar.DATE);//日
		return y + "-" + M + "-" + "28";
	}

	/**
	 * 获取日期后几天
	 * 
	 *            日期字符串：格式为yyyy-MM-dd
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateFormat(Date d, int n) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for (int i = 1; i <= n; i++) {
			cal.add(Calendar.DATE, 1);
		}
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.parse(sdf.format(cal.getTime()));
	}

	/**
	 * 获取日期前几天
	 * 
	 *            日期字符串：格式为yyyy-MM-dd
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateBeforeFormat(Date d, int n) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for (int i = 1; i <= n; i++) {
			cal.add(Calendar.DATE, -1);
		}
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.parse(sdf.format(cal.getTime()));
	}

	/**
	 * 获取日期前几天
	 * 
	 *            日期字符串：格式为yyyy-MM-dd HH24:mi:ss
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date dateHmsBeforeFormat(Date d, int n) throws ParseException {
		SimpleDateFormat sdf24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for (int i = 1; i <= n; i++) {
			cal.add(Calendar.DATE, -1);
		}
		return sdf24.parse(sdf24.format(cal.getTime()));
	}

	/**
	 * 获取日期前一天
	 * 
	 *            日期字符串：格式为yyyy-MM-dd
	 * @return
	 * @author yb
	 * @throws java.text.ParseException
	 */
	public static Date dateFormatQ(Date d, int m) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, -1);
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.parse(sdf.format(cal.getTime()));
	}

	/**
	 * 分钟格式化为小时：1小时20.12分钟
	 * 
	 *            对象
	 * @throws
	 */
	public static String hourMinFromat(Double d) {
		String res = "";
		long h = 0l;
		double m = 0d;
		if (d >= 60) {
			if (d / 60 == (Double.valueOf(d / 60).longValue())) {
				return Double.valueOf(d / 60).longValue() + "小时";
			} else {
				h = Double.valueOf(d / 60).longValue();
				m = d % 60;
				if (m == (Double.valueOf(d % 60).longValue())) {
					return h + "小时" + Double.valueOf(d % 60).longValue() + "分";
				} else {
					return h + "小时" + Math.round(m * 100) / 100.00 + "分";
				}
			}
		} else {
			return Math.round(d * 100) / 100.00 + "分";
		}
	}

	/**
	 * 秒格式化为分钟：1分钟20秒
	 * 
	 *            对象
	 * @throws
	 */
	public static String secsMinFromat(Double d) {
		String res = "";
		long h = 0l;
		double m = 0d;
		if (d >= 60) {
			if (d / 60 == (Double.valueOf(d / 60).longValue())) {
				return Double.valueOf(d / 60).longValue() + "分";
			} else {
				h = Double.valueOf(d / 60).longValue();
				m = d % 60;
				if (m == (Double.valueOf(d % 60).longValue())) {
					if (m > 0) {
						return h + "分" + (Double.valueOf(d % 60).longValue())
								+ "秒";
					} else {
						return h + "分";
					}
				} else {
					if (Double.valueOf((Math.round(m * 100) / 100.00))
							.longValue() > 0) {
						return h
								+ "分"
								+ Double
										.valueOf((Math.round(m * 100) / 100.00))
										.longValue() + "秒";
					} else {
						return h + "分";
					}
				}
			}
		} else {
			return Double.valueOf(Math.round(d * 100) / 100.00).longValue()
					+ "秒";
		}
	}

	/**
	 * 将格式化时间，如1小时20.12分钟转换为时间（分）
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */
	public static double CvrthourMinFromat(Object o) {
		String str = o.toString();
		double res = 0d;
		long left = 0l;
		double right = 0d;
		try {
			str = str.replace("分", "");
			if (str.indexOf("小时") > -1) {
				left = Long.valueOf(str.split("小时", -1)[0]);
			}
			if (str.indexOf("小时") > -1) {
				right = Double.valueOf("".equals(str.split("小时", -1)[1]) ? "0"
						: str.split("小时", -1)[1]);
			} else {
				right = Double.valueOf(str);
			}
			res = right + left * 60;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 将格式化时间，如1分20秒转换为时间（秒）
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */
	public static double CvrtminSecsFromat(Object o) {
		String str = o.toString();
		double res = 0d;
		long left = 0l;
		double right = 0d;
		try {
			str = str.replace("秒", "");
			if (str.indexOf("分") > -1) {
				left = Long.valueOf(str.split("分", -1)[0]);
			}
			if (str.indexOf("分") > -1) {
				right = Double.valueOf("".equals(str.split("分", -1)[1]) ? "0"
						: str.split("分", -1)[1]);
			} else {
				right = Double.valueOf(str);
			}
			res = right + left * 60;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 根据起始日期与终止日期，返回之间所有的月份。格式：2010-12
	 * 
	 * @author zhaoj
	 * @since 2010-12-27
	 * @param qsrq
	 *            起始日期，格式：2010-12-12
	 * @param zzrq
	 *            终止，格式：2010-12-12
	 * @return List<String>
	 */
	public static List<String> getAllMonthRq(String qsrq, String zzrq) {
		List<String> res = new ArrayList();
		Calendar cal = Calendar.getInstance();
		Date d1 = null;
		Date d2 = null;
		try {
			cal.setTime(DateFormat.dateFormat(qsrq));
			cal.set(Calendar.DAY_OF_MONTH, 1);
			d1 = cal.getTime();
			d2 = DateFormat.dateFormat(zzrq);
			if (d1.after(d2)) {
				return null;
			}
			while (d1.before(d2) || d1.equals(d2)) {
				res.add(DateFormat.dateFormat(d1, "yyyy-MM"));
				cal.setTime(d1);
				cal.add(Calendar.MONTH, 1);// 月份加1
				d1 = cal.getTime();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 根据起始日期与终止日期，返回之间所有的日期。格式：2010-12-12
	 * 
	 * @author zhaoj
	 * @since 2010-12-27
	 * @param qsrq
	 *            起始日期，格式：2010-12-12
	 * @param zzrq
	 *            终止，格式：2010-12-12
	 * @return List<String>
	 */
	public static List<String> getAllRq(String qsrq, String zzrq) {
		List<String> res = new ArrayList();
		Calendar cal = Calendar.getInstance();
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = DateFormat.dateFormat(qsrq);
			d2 = DateFormat.dateFormat(zzrq);
			if (d1.after(d2)) {
				return null;
			}
			res.add(qsrq);
			while (d1.before(d2)) {
				cal.setTime(d1);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				d1 = cal.getTime();
				res.add(DateFormat.dateFormat(d1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 返回起始时间d1与终止时间d2之间的时间差
	 * 
	 * @author zj
	 * @since Jan 21, 2011
	 * @param d1
	 *            起始时间
	 * @param d2
	 *            截止时间
	 * @return 时间差(秒)
	 */
	public static double getSjc(String d1, String d2) {
		double cz = 0d;
		try {
			Date da = DateFormat.dateTodFormat(d1);
			Date db = DateFormat.dateTodFormat(d2);
			cz = (db.getTime() - da.getTime()) / 1000.00;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cz;
	}

	/**
	 * 判断日期与当月某天（day）的关系
	 * 
	 * @author zj
	 * @since May 5, 2011
	 * @param rq
	 *            日期
	 * @param day
	 *            天
	 * @return false 晚 true 早
	 */
	public static boolean getQHGX(String rq, int day) {
		boolean flag = false;
		Calendar cal = Calendar.getInstance();
		int day1 = 0;
		try {
			cal.setTime(DateFormat.dateFormat(rq));
			day1 = cal.get(Calendar.DAY_OF_MONTH);
			if (day1 >= day) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 返回rq所在月份的day所表示的日期
	 * 
	 * @author zj
	 * @since May 5, 2011
	 * @param rq
	 *            日期
	 * @param day
	 *            天
	 * @return
	 */
	public static String getCurMonthRq(String rq, int day) {
		String res = rq;
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(DateFormat.dateFormat(rq));
			cal.set(Calendar.DAY_OF_MONTH, day);
			res = DateFormat.dateFormat(cal.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

	/**
	 * 返回日期所在年度的第一天日期
	 * 
	 * @param d
	 *            欲转换日期
	 * @author zj
	 * @since May 16, 2011
	 * @return 格式：2011-1-1
	 */
	public static String getFirstDayNowYear(Date d) {
		Calendar cal = Calendar.getInstance();
		if (d == null) {
			return cal.get(Calendar.YEAR) + "-01-01";
		} else {
			cal.setTime(d);
			return cal.get(Calendar.YEAR) + "-01-01";
		}
	}

	/**
	 * 返回日期的part部分内容
	 * 
	 * @author zj
	 * @since May 16, 2011
	 * @param d
	 *            日期。为null表示当前日期
	 * @param part
	 *            展示部分:y m d h mm s
	 * @return
	 */
	public static String getPartRq(Date d, String part) {
		Calendar cal = Calendar.getInstance();
		if (d != null) {
			cal.setTime(d);
		}
		if ("y".equalsIgnoreCase(part)) {
			return cal.get(Calendar.YEAR) + "";
		}
		if ("m".equalsIgnoreCase(part)) {
			return cal.get(Calendar.MONTH) + "";
		}
		if ("d".equalsIgnoreCase(part)) {
			return cal.get(Calendar.DATE) + "";
		}
		if ("h".equalsIgnoreCase(part)) {
			return cal.get(Calendar.HOUR) + "";
		}
		if ("mm".equalsIgnoreCase(part)) {
			return cal.get(Calendar.MINUTE) + "";
		}
		if ("s".equalsIgnoreCase(part)) {
			return cal.get(Calendar.SECOND) + "";
		}
		return "";
	}

	/**
	 * 根据传入的类型返回指定的日期值
	 * 
	 * @author zj
	 * @since Jul 6, 2011
	 *            日期
	 * @param lx
	 *            y年 m 月 d 日 h 小时 mi 分 s 秒
	 * @return
	 */
	public static int getDateDetail(Date d, String lx) {
		int res = 0;
		Calendar cal = Calendar.getInstance();
		Date d1 = (d == null ? new Date() : d);
		cal.setTime(d1);
		if ("y".equalsIgnoreCase(lx)) {
			res = cal.get(Calendar.YEAR);
		}
		if ("m".equalsIgnoreCase(lx)) {
			res = cal.get(Calendar.MONTH);
		}
		if ("d".equalsIgnoreCase(lx)) {
			res = cal.get(Calendar.DAY_OF_MONTH);
		}
		if ("h".equalsIgnoreCase(lx)) {
			res = cal.get(Calendar.HOUR_OF_DAY);
		}
		if ("mi".equalsIgnoreCase(lx)) {
			res = cal.get(Calendar.MINUTE);
		}
		if ("s".equalsIgnoreCase(lx)) {
			res = cal.get(Calendar.SECOND);
		}
		return res;
	}

	/**
	 * 根据某一年中的某一周得到该周的第一天或最后一天
	 * 
	 * @author wangj
	 * @since 1.0
	 * @param flag
	 *            标志位，需要得到的是第一天(0)还是最后一天(1)
	 * @param year_week
	 *            某一年中的第几周
	 * @return Date 需要得到的日期
	 */
	public static Date getFirstOrLastDayOfWeek(int flag, String year_week) {
		// 返回的日期
		Date date = new Date();
		// 分割后包含年和周的数组
		String[] yearAndWeek = year_week.split("-");
		// 年份
		int year = Integer.parseInt(yearAndWeek[0]);
		// 一年中的第几周
		int week = Integer.parseInt(yearAndWeek[yearAndWeek.length - 1]);
		// 得到日期对象
		Calendar calendar = Calendar.getInstance();
		// 设置为要查询的年
		calendar.set(Calendar.YEAR, year);
		// 设置一周的开始时间为周一
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		// 得到该年最大的周数
		int max_week = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
		if (week <= max_week) {
			// 设置该年的第几周
			calendar.set(Calendar.WEEK_OF_YEAR, week);
			// 如果要得到第一天，即星期一
			if (0 == flag) {
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				date = calendar.getTime();
			} else if (1 == flag) {
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				date = calendar.getTime();
			}
		} else {
			// 设置一年的最后一周
			calendar.set(Calendar.WEEK_OF_YEAR, max_week);
			// 设置周末
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			// 去该周第一天
			if (0 == flag) {
				date = new Date(calendar.getTimeInMillis() + 24 * 3600 * 1000);
			} else if (1 == flag) {
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(year
							+ "-12-31");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		return date;
	}

	/**
	 * 求某年有多少个周
	 * 
	 * @author LF Email：linfei_feixiang@126.com
	 * @date 2011-1-7 下午04:57:21
	 * @param year
	 * @return
	 * @return int
	 */
	public static int getWeeksByYear(int year) {
		int weeks = 52;// 一般年52个周(2000年有54个周)
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, 53);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		if (year == cal.get(Calendar.YEAR)) {
			weeks = 53;
			cal.set(Calendar.WEEK_OF_YEAR, 54);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			if (year == cal.get(Calendar.YEAR)) {
				weeks = 54;
			}
		}
		return weeks;
	}

	/**
	 * @author LF 求某年下的第几个星期的开始和结束日期 返回 Date 类型日期 时间time为当前机器时间
	 * @param year
	 *            要获得的年
	 * @param week
	 *            第几个星期
	 * @param flag
	 *            是否是第一天还是最后一天,当为true时返回第一天,false则返回最后一天
	 * @return java.uilt.Date 类型日期
	 * @例如 getDayByWeek(2002,2,true) 返回Tue Jan 08 14:11:57 CST 2002
	 * @注意 某周开始或结束的日期若超出该年则默认为当年第一天或最后一天
	 */
	public static Date getDayByWeek(int year, int week, boolean flag) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		// cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		if (flag) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			if (year != cal.get(Calendar.YEAR)) {
				cal.clear();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.DAY_OF_YEAR, 1);
			}
		} else {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			if (year != cal.get(Calendar.YEAR)) {
				cal.clear();
				cal.set(Calendar.YEAR, year);
				cal.roll(Calendar.DAY_OF_YEAR, -1);// 本年最后一天
			}
		}
		return cal.getTime();
	}

	/**
	 * 根据年度获取最后一天日期
	 * 
	 * @author LF Email：linfei_feixiang@126.com
	 * @date 2011-1-7 下午07:03:50
	 * @param year
	 * @return
	 * @return Date
	 */
	public static Date getLastdayOfyear(int year) {
		Calendar cal = Calendar.getInstance();
		// 本年最后一天
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.YEAR, year + 1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}

	/**
	 * 获得本周一的日期 跨年的话取本年第一天
	 * 
	 * @author penglianfeng
	 */
	public static String getMondayOFWeek(Date date) {
		Calendar currentDate = Calendar.getInstance();
		if (date != null) {
			currentDate.setTime(date);
		}
		int year = currentDate.get(Calendar.YEAR);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int monday_year = currentDate.get(Calendar.YEAR);
		// 跨年
		if (monday_year < year) {
			currentDate.set(Calendar.DAY_OF_YEAR, 1);
		}
		return dateFormat(currentDate.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获得本周日的日期 跨年的话取本年最后一天
	 * 
	 * @author penglianfeng
	 */
	public static String getSundayOFWeek(Date date) {
		Calendar currentDate = Calendar.getInstance();
		if (date != null) {
			currentDate.setTime(date);
		}
		int year = currentDate.get(Calendar.YEAR);
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		int monday_year = currentDate.get(Calendar.YEAR);
		// 跨年
		if (monday_year > year) {
			currentDate.set(Calendar.DAY_OF_YEAR, currentDate
					.getMaximum(Calendar.DAY_OF_YEAR));
		}
		return dateFormat(currentDate.getTime(), "yyyy-MM-dd");
	}
	public static Map<String, String> getWeeksByDate(int beginYear,int endYear,Date begin, Date end) {
		Map<String, String> allMap = new HashMap<String, String>();
		for (; beginYear <= endYear; beginYear++) {
			Map<String, String> weeks = getWeekMapByYear(beginYear);
			allMap.putAll(weeks);
		}
		Map<String, String> result = new HashMap<String, String>();
		while (begin.compareTo(end) <= 0) {
			result.put(dateFormat(begin), allMap.get(dateFormat(begin)));
			begin=org.apache.commons.lang.time.DateUtils.addDays(begin, 7);
		}
		return result;
	}
	public static Map<String, String> getWeekMapByYear(int year) {
		Map<String, String> map = new HashMap<String, String>();
		Calendar ca = Calendar.getInstance();
		ca.set(year, 0, 1);
		ca.setFirstDayOfWeek(Calendar.MONDAY);
		if (ca.get(Calendar.DAY_OF_WEEK) >= 4
				|| ca.get(Calendar.DAY_OF_WEEK) == 1) {
			ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			ca.add(Calendar.DATE, 7);
		} else {
			ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		}
		Date begin = ca.getTime();
		ca.set(year, 11, 31);
		if (ca.get(Calendar.DAY_OF_WEEK) >= 4
				|| ca.get(Calendar.DAY_OF_WEEK) == 1) {
			ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			ca.add(Calendar.DATE, 7);
		} else {
			ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		}
		Date end = ca.getTime();
		int i = 1;
//		System.out.println("end ======= "+dateFormat(end, "yyyy-MM-dd"));
		while (begin.compareTo(end) <= 0) {
//			System.out.println("begin1==="+dateFormat(begin, "yyyy-MM-dd"));
			map.put(dateFormat(begin, "yyyy-MM-dd"), i + "周("
					+ DateFormat.dateFormat(begin, "MM月dd日") + ")");
			begin = org.apache.commons.lang.time.DateUtils.addDays(begin, 7);
//			System.out.println("begin2==="+dateFormat(begin, "yyyy-MM-dd"));
			i++;
		}
		return map;
	}
}
