package com.payrollsys.dao.impl;

import com.payrollsys.dao.AuditingDao;
import com.payrollsys.entity.Auditing;
import com.payrollsys.util.DBUtil2;

public class AuditingDaoImpl implements AuditingDao{

	@Override
	public void save(Auditing auditing) {
		String sql = "insert into auditing values(seq_audit.nextval,?,?,?,?,?)";
		Object [] params = {auditing.getExpId(),auditing.getAuditor().getEmpId(),
				auditing.getResult(),auditing.getAuditDesc(),
				new  java.sql.Timestamp(auditing.getAuditTime().getTime())};//Timestamp yyyyMMddhhmmss
		DBUtil2.executeUpdate(sql, params);
		
	}

}
