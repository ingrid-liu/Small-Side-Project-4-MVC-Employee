package com.bjsxt.entity;

import java.util.Date;

/**
 * 审核类
 * @author Administrator
 *
 */
public class Auditing {

	private int auditId; //审核编号
	private int expId; //报销单编号
	private String empId;//审核人的编号
	
	private String result;//审核结果   通过   打回  拒绝
	private String auditDesc; //审核意见
	
	private Employee auditor;//审核人
	private Expense exp;//报销单
	private Date  auditTime; //yyyyMMddhhmmss 
	
	public Auditing() {
		super();
	}
	
	
	
	public Auditing(int expId, String result, String auditDesc,
			Employee auditor, Date auditTime) {
		super();
		this.expId = expId;
		this.result = result;
		this.auditDesc = auditDesc;
		this.auditor = auditor;
		this.auditTime = auditTime;
	}



	public Auditing(int auditId, int expId, String empId, String result,
			String auditDesc, Employee auditor, Expense exp, Date auditTime) {
		super();
		this.auditId = auditId;
		this.expId = expId;
		this.empId = empId;
		this.result = result;
		this.auditDesc = auditDesc;
		this.auditor = auditor;
		this.exp = exp;
		this.auditTime = auditTime;
	}



	public int getAuditId() {
		return auditId;
	}
	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}
	public int getExpId() {
		return expId;
	}
	public void setExpId(int expId) {
		this.expId = expId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public Employee getAuditor() {
		return auditor;
	}
	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}
	public Expense getExp() {
		return exp;
	}
	public void setExp(Expense exp) {
		this.exp = exp;
	}


	public Date getAuditTime() {
		return auditTime;
	}


	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	
}
