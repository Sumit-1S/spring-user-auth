package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.DiscountDAO;
import com.example.entity.Discount;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountDAO discountDAO;

	@Override
	public String addDiscount(Discount discount) throws Exception {
		discountDAO.save(discount);
		return "Discount Added..";
	}

	@Override
	public ArrayList<Discount> getAllDiscount(Integer policyId) {
		return (ArrayList<Discount>)discountDAO.findByPolicyId(policyId);
	}

}
