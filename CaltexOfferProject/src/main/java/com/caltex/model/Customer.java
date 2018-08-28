package com.caltex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Customer_table")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;
	private String customerType = "Active";
	@NotBlank(message = "Customer Name cannot be Blank")
	private String customerName;
	@NotBlank(message = "Customer Email cannot be Blank")
	@Email
	private String customerEmail;
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
	@NotNull(message = "Customer Phone No. cannot be Blank")
	private String customerPhoneNo;
	@OneToOne
	private Transaction transaction;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	

	public Customer(long customerId, String customerType,
			@NotBlank(message = "Customer Name cannot be Blank") String customerName,
			@NotBlank(message = "Customer Email cannot be Blank") @Email String customerEmail,
			@Pattern(regexp = "(0/91)?[7-9][0-9]{9}") @NotNull(message = "Customer Phone No. cannot be Blank") String customerPhoneNo,
			Transaction transaction) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhoneNo = customerPhoneNo;
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerType=" + customerType + ", customerName="
				+ customerName + ", customerEmail=" + customerEmail + ", customerPhoneNo=" + customerPhoneNo
				+ ", transaction=" + transaction + "]";
	}

	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
