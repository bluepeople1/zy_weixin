package dao.impl;

import com.alibaba.fastjson.JSONArray;
import dao.CommonDAO;

/**
 * Created by caizhongrui on 15/5/15.
 * ==============================================
 * 类说明：
 * ==============================================
 * 蔡仲瑞          15/5/15             创建
 * ==============================================
 * 青岛特来电版权所有
 * ==============================================
 */
public class CommonDAOImpl extends GenericDaoImpl implements CommonDAO{
    @Override
    public JSONArray getAllContestant(){
        String sql = "";
        return this.getJsonArray(sql);
    }
}
