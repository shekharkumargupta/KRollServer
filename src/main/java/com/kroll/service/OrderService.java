package com.kroll.service;

import java.util.List;
import java.util.Optional;

import com.kroll.domain.CustomerOrder;

public interface OrderService {

    public CustomerOrder create(CustomerOrder customerOrder);

    public CustomerOrder update(CustomerOrder customerOrder);

    public Optional<CustomerOrder> findById(long orderId);

    public List<CustomerOrder> findAllByCustomerLoginId(String customerLoginId);
    
    public List<CustomerOrder> findAllByCompanyId(long companyId);

}
