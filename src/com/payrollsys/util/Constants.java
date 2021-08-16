package com.payrollsys.util;
/**
 * 常量工具类
 * @author Administrator
 *
 */
public class Constants {

	//报销单的状态
	public static final String EXPENSE_STATUS_NEW = "0"; //新创建
	public static final String EXPENSE_STATUS_AUDITING = "1";  //审核中
	public static final String EXPENSE_STATUS_PASS = "2"; //审核通过
	public static final String EXPENSE_STATUS_REJECT = "3"; //审核拒绝
	public static final String EXPENSE_STATUS_BACK = "4"; //审核打回
	public static final String EXPENSE_STATUS_CASHED = "5"; //已打款
	
	//特殊岗位的员工编号
	public static final String POSITION_CEOID= "gaoqi";
	public static final String POSITION_CFOID= "lifuying";
	
	
}
