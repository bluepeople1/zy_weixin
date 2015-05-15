package action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by caizhongrui on 15/5/14.
 * ==============================================
 * 类说明：
 * ==============================================
 * 蔡仲瑞          15/5/14             创建
 * ==============================================
 * 青岛众应信息科技有限公司版权所有
 * ==============================================
 */
public class BaseAction extends ActionSupport{
    protected String json;// String of json
    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public ServletContext getServletContext() {
        return ServletActionContext.getServletContext();
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getSessionMap() {
        Map<String, String> session=(Map) ActionContext.getContext().get(ActionContext.SESSION);
        return session;
    }
    //java 添加用于copy属性
    protected void formMapping(Object o1, Object o2) {
        try {
            BeanUtils.copyProperties(o1, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected JSONObject getJsonObject(){
        return JSONObject.parseObject(json);
    }
    protected JSONArray getJsonArray(){
        return JSONArray.parseArray(json);
    }
    //java 添加

    public String getRealyPath(String path) {
        return getServletContext().getRealPath(path);
    }
    /**
     * 向客户端输出JSON对象
     * @param obj 此对象必须可以转化为JSON对象
     */
    public void outJSONObject(JSONObject obj){
        outString(obj.toString());
    }
    /**
     * 向客户端输出JSONArray对象
     * @param obj 此对象必须可以转化为JSON对象
     */
    public void outJSONArray(JSONArray obj){
        outString(obj.toString());
    }
    /**
     * 向客户端输出字符串
     * @param str
     */
    public void outString(String str) {
        try {
            ServletActionContext.getResponse().setContentType("text/javascript;charset=UTF-8");
            PrintWriter out = ServletActionContext.getResponse().getWriter();
            out.write(str);
            out.close();
        } catch (IOException e) {

        }
    }
}
