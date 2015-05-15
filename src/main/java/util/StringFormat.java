package util;

import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.text.NumberFormat;


public class StringFormat {

	/**
	 * 对传入的字符串进行处理,仅保留不重复的内容
	 * 
	 * @author zj
	 * @since Apr 17, 2011
	 * @param cs
	 *            ：以逗号分隔的字符串
	 * @return 以逗号分隔的字符串
	 */
	public static String getNoRepeat(String cs) {
		String[] arr = (cs + ";").split(";");
		String res = "";
		for (String s : arr) {
			if (res.indexOf(s) == -1 && "".equals(s) == false) {
				res += s + ";";
			}
		}
		if ("".equals(res) == false) {
			res = res.substring(0, res.length() - 1);
		}
		return res;
	}

	/**
	 * 根据代码长度要求在左侧填充0
	 * 
	 * @author zj
	 * @since May 16, 2011
	 * @param code
	 *            代码
	 * @param n
	 *            总长度
	 * @return
	 */
	public static String getPadCode(String code, int n) {
		int dm = Double.valueOf(code).intValue();
		int len = 0;
		String s = dm + "";
		if (s.length() >= n) {
			return s;
		}
		len = n - s.length();
		for (int i = len; i >= 1; i--) {
			s = "0" + s;
		}
		return s;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	/**
	 * 将文本对象格式化
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */
	public static String strFromat(Object o) {
		return o == null || o.toString().indexOf("null") > -1 ? "" : o
				.toString();
	}

	/**
	 * 
	 * @param input
	 *            需要转换的字符串
	 * @return 转换后的全角字符串
	 * @Method Descriptions: 半角字符串转全角字符串
	 * @Date Created:Nov 14, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	public static String toSBC(String input) {
		if (input != null) {
			char c[] = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ' ') {
					c[i] = '\u3000';
				} else if (c[i] < '\177') {
					c[i] = (char) (c[i] + 65248);
				}
			}
			return new String(c);
		}
		return null;
	}

	/**
	 * 
	 * @param input
	 *            需要转换的字符串
	 * @return 转换后的半角字符串
	 * @Method Descriptions: 全角转半角
	 * @Date Created:Nov 14, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	public static String toDBC(String input) {
		if (input != null) {
			char c[] = input.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '\u3000') {
					c[i] = ' ';
				} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
					c[i] = (char) (c[i] - 65248);
				}
			}
			String returnString = new String(c);
			return returnString;
		}
		return null;
	}

	/**
	 * 
	 * @param ua
	 *            浏览器标识
	 * @param s
	 *            需要转码的字符串
	 * @return 转码后的字符串
	 * @Method Descriptions: 根据浏览器的标识，将字符串转码，防止出现乱码
	 * @Date Created:Nov 14, 2011
	 * @Creater:hanzn
	 * 
	 * @Modifier:
	 * @Date Modifier:
	 * @Modification Reasons:
	 * 
	 * 
	 */
	public static String encode(String ua, String s) {
		String codes = null;
		try {
			if (ua.indexOf("MSIE") != -1) {// IE
				codes = URLEncoder.encode(s, "UTF-8");
			} else {// Firefox
				codes = new String(s.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codes;
	}
	
	/**
	 * 补位
	 */
	public static String leftLead(int str, int length) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(length);
		formatter.setGroupingUsed(false);
		return formatter.format(str);
	}
	/**
	 * 
	* @Title: 取在某一范围区间中不在集合中的一个最小值
	* @author：LF  feixiang_linfei@163.com
	* @Description: TODO 例如：
	* @param @param length 区间大小，如2表示两位
	* @param @param nums 在该区间的数据集合
	* @param @return    
	* @return  String   左侧不足区间长度的，用0补齐
	* @throws 
	* @date 2012-9-13 下午04:27:18
	 */
	public static String getBmString(int length, List<Integer>nums) {
		int last =(int)Math.pow(10, length+1);
		for(int i=1;i<last;i++){
			if(!nums.contains(i)){
			  return StringFormat.leftLead(i, length);
			}
		}
		 return null;
		}
}
