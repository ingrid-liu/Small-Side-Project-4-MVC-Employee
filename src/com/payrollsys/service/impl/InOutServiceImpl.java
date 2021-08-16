package com.payrollsys.service.impl;

import java.util.List;

import com.payrollsys.dao.IncomeDao;
import com.payrollsys.dao.PaymentDao;
import com.payrollsys.dao.impl.IncomDaoImpl;
import com.payrollsys.dao.impl.PaymentDaoImpl;
import com.payrollsys.service.InOutService;

public class InOutServiceImpl implements InOutService {

	@Override
	public String getBarData() {
		//调用DAO层获取收入数据（List）
		IncomeDao  icDao = new IncomDaoImpl();
		List<Object []> list = icDao.findStaticsData();
		
		//将List转换成jsonStr 
		StringBuilder icTypeArr = new StringBuilder("[");//['Mon1', 'Tue2', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
		StringBuilder amountArr = new StringBuilder("[");//[120, 200, 1500, 800, 70, 110, 130]
		for(int i=0;i<list.size();i++){
			Object [] arr = list.get(i);// {项目开发,400}
			if(i<list.size()-1){
				icTypeArr.append("\""+arr[0]+"\",");
				amountArr.append(arr[1]+",");
			}else{
				icTypeArr.append("\""+arr[0]+"\"");
				amountArr.append(arr[1]);
			}
		}
		icTypeArr.append("]");
		amountArr.append("]");
		
		//"[['Mon1', 'Tue2', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],[120, 200, 1500, 800, 70, 110, 130]]";
		String jsonStr = "["+icTypeArr.toString()+","+amountArr.toString()+"]";
		//返回jsonStr
		return jsonStr;
	}

	@Override
	public String getPieData(int type) {
		//调用DAO层获取支出入数据（List）
		PaymentDao  pmDao = new PaymentDaoImpl();
		List<Object []> list = pmDao.findStaticsData(type);
		
//		String jsonStr = "[{value:345, name:'直接访问'},{value:310, name:'邮件营销'},"
//		+ "{value:234, name:'联盟广告'},{value:135, name:'视频广告'},"
//		+ "{value:1548, name:'搜索引擎'}]";
		//将List转换成jsonStr 		
		StringBuilder jsonStr =new StringBuilder("[");
		for(int i=0;i<list.size();i++){
			Object [] arr = list.get(i);
			jsonStr.append("{");
			jsonStr.append("\"value\":"+arr[1]+",");
			jsonStr.append("\"name\":\""+arr[0]+"\"");
			
			if(i<list.size()-1){
				jsonStr.append("},");
			}else{
				jsonStr.append("}");
			}
			
		}
		jsonStr.append("]");
		
		//返回jsonStr
		return jsonStr.toString();
	}

}
