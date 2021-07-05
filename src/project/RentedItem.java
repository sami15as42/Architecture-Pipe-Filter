package project;

import java.util.Date;

public class RentedItem {
	private Client customer;
	private StockItem item;
	private Date dueDate;

	public RentedItem(Client customer, StockItem item, Date dueDate) {
		this.customer = customer;
		this.item = item;
		this.dueDate = dueDate;
	}

	public Client getCustomer() {
		return customer;
	}

	public void setCustomer(Client customer) {
		this.customer = customer;
	}

	public StockItem getItem() {
		return item;
	}

	public void setItem(StockItem item) {
		this.item = item;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
