package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Discount {
	public Discount(Integer policyId, Double discount_price, Boolean active) {
		this.policyId = policyId;
		this.discountPrice = discount_price;
		this.active = active;
	}
	@Id
	@GeneratedValue
	private Integer discountId;
	private Integer policyId;
	private Double discountPrice;
	private Boolean active;
}
