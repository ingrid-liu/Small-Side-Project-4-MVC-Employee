package com.payrollsys.dao;

import com.payrollsys.entity.Auditing;

public interface AuditingDao {
	/**
	 *  添加审核记录
	 * @param auditing
	 */
	public void save(Auditing auditing);
}
