package com.kroll.dao;

import com.kroll.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderDAO extends JpaRepository<CustomerOrder, Long> {

    public List<CustomerOrder> findAllByCustomerLoginId(String customerLoginId);

    public List<CustomerOrder> findAllByCompanyId(long companyId);

}
