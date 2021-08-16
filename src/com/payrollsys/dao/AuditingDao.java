package com.bjsxt.dao;

import com.bjsxt.entity.Auditing;

public interface AuditingDao {
	/**
	 *  添加审核记录
	 * @param auditing
	 */
	public void save(Auditing auditing);
}
