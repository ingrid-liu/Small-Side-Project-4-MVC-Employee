package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Payment;

public interface PaymentDao {
	/**
	 * 添加支付记录
	 * @param pm
	 */
	public void save(Payment pm);
	/**
	 * 查询支出统计数据
	 * @param i
	 * @return
	 */
	public List<Object[]> findStaticsData(int i);
}
