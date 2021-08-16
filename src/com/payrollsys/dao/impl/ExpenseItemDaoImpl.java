package com.payrollsys.dao.impl;

import com.payrollsys.dao.ExpenseItemDao;
import com.payrollsys.entity.ExpenseItem;
import com.payrollsys.util.DBUtil2;

public class ExpenseItemDaoImpl implements ExpenseItemDao {

	@Override
	public void save(ExpenseItem item) {
		String sql = "insert into expenseitem values(seq_item.nextval,?,?,?,?)";
		Object [] params ={item.getExpId(),item.getType(),item.getAmount(),item.getItemDesc()};
		DBUtil2.executeUpdate(sql, params);
		
	}

}
