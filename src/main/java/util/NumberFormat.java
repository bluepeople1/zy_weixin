package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberFormat {
	// 数字千分位格式化变量
	static DecimalFormat dfQFW = new DecimalFormat("#,##0.00");
	// 日期格式化变量
	
	// 日期格式化变量
	
	// 日期格式化变量
		
	
	/**
	 * 格式化Double类型的数字(格式化为千分位表示)，如果参数为0则返回空格字符串&nbsp;
	 * 
	 * @param d
	 * @param 保留的小数位数:只能输入0-6，其他数字自动保留2位小数
	 * @return
	 */
	public static String numberFormatZero(Double d, int n) {
		DecimalFormat df = null;
		switch (n) {
		case 0:
			df = new DecimalFormat("#,##0");
			break;
		case 1:
			df = new DecimalFormat("#,##0.0");
			break;
		case 3:
			df = new DecimalFormat("#,##0.000");
			break;
		case 4:
			df = new DecimalFormat("#,##0.0000");
			break;
		case 5:
			df = new DecimalFormat("#,##0.00000");
			break;
		case 6:
			df = new DecimalFormat("#,##0.000000");
			break;
		default:
			df = new DecimalFormat("#,##0.00");
			break;
		}
		return d == null ? "&nbsp;" : df.format(d);
	}

	/**
	 * 格式化Double类型的数字(格式化为千分位表示)
	 * 
	 * @param d
	 * @param 保留的小数位数:只能输入0-6，其他数字自动保留2位小数
	 * @return
	 */
	public static String numberFormat(Double d, int n) {
		DecimalFormat df = null;
		switch (n) {
		case 0:
			//日期：2012-4-2 修改人：赵健
			//说明：展示数据时取消逗号分隔符	
			df = new DecimalFormat("###0");
			//日期：2012-4-2 修改人：赵健
			//说明：展示数据时取消逗号分隔符>>			
			return d == null ? "0" : df.format(d);
		case 1:
			df = new DecimalFormat("#,##0.0");
			return d == null ? "0.0" : df.format(d);
		case 3:
			df = new DecimalFormat("#,##0.000");
			return d == null ? "0.000" : df.format(d);
		case 4:
			df = new DecimalFormat("#,##0.0000");
			return d == null ? "0.0000" : df.format(d);
		case 5:
			df = new DecimalFormat("#,##0.00000");
			return d == null ? "0.00000" : df.format(d);
		case 6:
			df = new DecimalFormat("#,##0.000000");
			return d == null ? "0.000000" : df.format(d);
		default:
			df = new DecimalFormat("#,##0.00");
			return d == null ? "0.00" : df.format(d);
		}
	}
//plf add numberFormatI
	/**
	 * 格式化Double类型的数字(格式化为千分位表示)
	 * 
	 * @param d
	 * @param 保留的小数位数:只能输入0-6，其他数字自动保留2位小数
	 * @return
	 */
	public static String numberFormatI(Double d, int n) {
		DecimalFormat df = null;
		switch (n) {
		case 0:
			df = new DecimalFormat("###0");
			return d == null ? "0" : df.format(d);
		case 1:
			df = new DecimalFormat("###0.0");
			return d == null ? "0.0" : df.format(d);
		case 3:
			df = new DecimalFormat("###0.000");
			return d == null ? "0.000" : df.format(d);
		case 4:
			df = new DecimalFormat("###0.0000");
			return d == null ? "0.0000" : df.format(d);
		case 5:
			df = new DecimalFormat("###0.00000");
			return d == null ? "0.00000" : df.format(d);
		case 6:
			df = new DecimalFormat("###0.000000");
			return d == null ? "0.000000" : df.format(d);
		default:
			df = new DecimalFormat("###0.00");
			return d == null ? "0.00" : df.format(d);
		}
	}
	//plf add numberFormatI
	/**
	 * 格式化Double类型的数字(固定为2位小数)
	 * 
	 * @param d
	 * @return
	 */	
	public static String numberFix2Format(Double d)
	{
		DecimalFormat df = null;
		df = new DecimalFormat("###0.00");
		return d == null ? "0.00" : df.format(d);

	}
	
	/**
	 * 格式化Double类型的数字，返回字符串格式
	 * 
	 * @param d
	 * @return
	 */	
	public static String numberFixNFormat(Double d,int n)
	{
		DecimalFormat df = null;
		String num = "";
		// 不要小数位数
		if (0 == n) {
			df = new DecimalFormat("###0");
		} else {
			for(int i=n;i>=1;i--)
			{
				num+="0";
			}
			df = new DecimalFormat("###0."+num);
		}
		return d == null ? "0.00" : df.format(d);

	}	
	
	/**
	 * 保留两位小数--尽量避免使用
	 * @param val
	 * @param precision
	 * @return
	 */
	public static Double roundDouble(double val, int precision){
        Double ret = null;   
        try {   
            double factor = Math.pow(10, precision);   
            ret = Math.floor(val * factor + 0.5) / factor;   
        } catch (Exception e) {   
            e.printStackTrace();   
        }
        return ret;   
    }  	
	
	/**
	 * 格式化Double类型的数字(固定为4位小数)
	 * 
	 * @param d
	 * @return
	 */	
	public static String numberFix4Format(Double d)
	{
		return Math.round(d*10000)/10000.00+"";
	}	
	
	/**
	 * 分析字符串中的文本，以生成一个 Number
	 * 
	 * @param s
	 * @return
	 */
	public static Number parse(String s) {
		try {
			return dfQFW.parse(s);
		} catch (ParseException e) {
			System.out.println("Wanging:ParseException--" + e.getMessage());
			return null;
		}
	}

	/**
	 * 将数值对象格式化为int
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */
	public static int intFromat(Object o) {
		if(o!=null&&!"".equals(o)){
			String str=o.toString();
			int index=str.indexOf(".");
			if(index>0){
				double d = Double.valueOf(o.toString());
				Long l = Math.round(d);
				int i = l.intValue();
				return i;
			}
			return Integer.valueOf(o.toString());
		}
		return 0;
	}
	/**
	 * 将数值对象格式化为double
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */
	public static double dblFromat(Object o) {
		return o == null ? 0d : Math.round(Double.valueOf("".equals(o.toString())?"0":o.toString())*100)/100.00;
	}
	
	/**
	 * 将数值对象格式化为double
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */
	public static double dblFromat(Object o,int jd) {
		return o == null ? 0d : Math.round(Double.valueOf("".equals(o.toString())?"0":o.toString())*Math.pow(10, jd))/Math.pow(10, jd);
	}
	
	/**
	 * 将数值对象采用科学计数法格式化
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */	
	public static String dblScienceFormat(Object o,int jd)
	{
		String zeros = "";
		for(int i=jd;i>=1;i--)
		{
			zeros+="0";
		}
		BigDecimal    bd = new BigDecimal(Double.valueOf(o.toString()));
		DecimalFormat df = new DecimalFormat("0."+zeros); 
		return df.format(o); 
	}
	/**
	 * 将数值对象采用科学计数法格式化
	 * 
	 * @param o
	 *            对象
	 * @throws
	 */	
	public static String dblBigFormat(Object o,int jd)
	{
		String zeros = "";
		for(int i=jd;i>=1;i--)
		{
			zeros+="0";
		}
		BigDecimal    bd = new BigDecimal(Double.valueOf(o.toString()));
		DecimalFormat df = new DecimalFormat("0."+zeros); 
		String big = df.format(bd);
		return big.substring(0,big.indexOf('.')+3);
	}
	/**
	 * 根据除数和被除数计算商值(被除数÷除数=商);出现异常返回0
	 * 
	 * @param o1 被除数
	 * @param o2 除数
	 * @param xsw 小数位(返回的小数位)
	 * @throws
	 */
	public static double getDivided(Object o1,Object o2,int xsw) {
		try
		{
			double bcs = Double.valueOf("".equals(o1==null?"":o1.toString())?"0":o1.toString());
			double cs = Double.valueOf("".equals(o2==null?"":o2.toString())?"0":o2.toString());
			double xs = Math.pow(10, xsw);
			if(cs==0)
			{
				return 0.00;
			}
			else
			{
				return Math.round(bcs*xs/cs)/xs;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return 0d;
	}	
	/**
	 * 对象格式化为数值(将%去掉)
	 * @author zhaoj
	 * @param o
	 * @return
	 */
	public static  double FormatD(Object o)
	{
		String str = o.toString();
		str = str.replace("%", "");
		return Double.valueOf(str)/100.00;
	}	

	/**
	 * 处理后台数值过大不能正常显示5.7286882E7
	 * 
	 */
	public static String DecimalFormat(Double d){
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(d);
	}	
}
