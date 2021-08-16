package com.bjsxt.service;

public interface InOutService {
	/**
	 * 返回收入的柱状图数据
	 * @return
	 */
	String getBarData();
	/**
	 * 返回支出的饼图数据
	 * @param i
	 * @return
	 */
	String getPieData(int i);

}
