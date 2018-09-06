package com.kroll.dao;

import java.util.List;

import com.kroll.domain.CustomerOrder;

public interface CustomerOrderDAO {

	public CustomerOrder create(CustomerOrder customerOrder);

	public CustomerOrder update(CustomerOrder customerOrder);

	public CustomerOrder findById(long orderId);

	public List<CustomerOrder> findAllByCustomerLoginId(String customerLoginId);

	public List<CustomerOrder> findAllByCompanyId(long companyId);

}
