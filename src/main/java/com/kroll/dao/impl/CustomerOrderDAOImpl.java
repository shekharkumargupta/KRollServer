package com.kroll.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.CustomerOrderDAO;
import com.kroll.domain.CustomerOrder;

@Repository
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public CustomerOrder create(CustomerOrder customerOrder) {
		getSession().save(customerOrder);
		return customerOrder;
	}

	@Override
	public CustomerOrder update(CustomerOrder customerOrder) {
		getSession().update(customerOrder);
		return customerOrder;
	}

	@Override
	public CustomerOrder findById(long orderId) {
		return (CustomerOrder) getSession().get(CustomerOrder.class, orderId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> findAllByCustomerLoginId(String customerLoginId) {
		Query query = getSession().createQuery("Select o from CustomerOrder o where o.customer.loginId = ?")
				.setParameter(0, customerLoginId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> findAllByCompanyId(long companyId) {
		System.out.println("HibernateSession isConnected: "+getSession().isConnected());
		Query query = getSession().createQuery("Select o from CustomerOrder o where o.customer.company.id = ?")
				.setParameter(0, companyId);
		return query.list();

	}

}
