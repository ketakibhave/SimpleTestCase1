package com.training.bean;

public class ReturnsData {
	private String OrderID;
	private String Customer;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Phone;
	private String Product;
	private String Model;
	
	public ReturnsData(String orderID, String customer, String firstName, String lastName, String email, String phone,
			String product, String model) {
		super();
		OrderID = orderID;
		Customer = customer;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Phone = phone;
		Product= product;
		Model = model;
	}

	public ReturnsData() {
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String OrderID) {
		this.OrderID = OrderID;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String Customer) {
		this.Customer = Customer;
	}
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
	public String getProduct() {
		return Product;
	}

	public void setProduct(String Product) {
		this.Product = Product;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String Model) {
		this.Model = Model;
	}
	@Override
	public String toString() {
		return "ReturnsData [OrderID=" + OrderID + ", Customer=" + Customer + ", FirstName="+ FirstName +", LastName="+ LastName + "Email= "+Email +"Phone= "+Phone + "Product= "+Product+ "Model"+ Model +"]";
	}
}
