package com.bjsxt.dao.impl;

import com.bjsxt.dao.AuditingDao;
import com.bjsxt.entity.Auditing;
import com.bjsxt.util.DBUtil2;

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
