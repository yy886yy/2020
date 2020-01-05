package tz.cn.vo;
import java.util.Date;

import tz.cn.pojo.TbCustomer;
public class OrderVO {
	private Integer id;
	private String orderNumber;
	private String OrderStatus;
	private Integer productCount;
	private double productAmounTotal;
	private double orderAmountTotal;
	private double logisticsFee;
	private String address;
	private String orderlogisticsId;
	private String paymentMethod;
	private String paymentPic;
	private String phone;
	private String orderRemark;
	private String orderConsignee;
	private Date newDate;
	private Date paymentTime;
	private Date deliveryTime;
	private TbCustomer customer;
	public TbCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(TbCustomer customer) {
		this.customer = customer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public double getProductAmounTotal() {
		return productAmounTotal;
	}
	public void setProductAmounTotal(double productAmounTotal) {
		this.productAmounTotal = productAmounTotal;
	}
	public double getOrderAmountTotal() {
		return orderAmountTotal;
	}
	public void setOrderAmountTotal(double orderAmountTotal) {
		this.orderAmountTotal = orderAmountTotal;
	}
	public double getLogisticsFee() {
		return logisticsFee;
	}
	public void setLogisticsFee(double logisticsFee) {
		this.logisticsFee = logisticsFee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderlogisticsId() {
		return orderlogisticsId;
	}
	public void setOrderlogisticsId(String orderlogisticsId) {
		this.orderlogisticsId = orderlogisticsId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentPic() {
		return paymentPic;
	}
	public void setPaymentPic(String paymentPic) {
		this.paymentPic = paymentPic;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getOrderConsignee() {
		return orderConsignee;
	}
	public void setOrderConsignee(String orderConsignee) {
		this.orderConsignee = orderConsignee;
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	@Override
	public String toString() {
		return "OrderVO [id=" + id + ", orderNumber=" + orderNumber + ", OrderStatus=" + OrderStatus + ", productCount="
				+ productCount + ", productAmounTotal=" + productAmounTotal + ", orderAmountTotal=" + orderAmountTotal
				+ ", logisticsFee=" + logisticsFee + ", address=" + address + ", orderlogisticsId=" + orderlogisticsId
				+ ", paymentMethod=" + paymentMethod + ", paymentPic=" + paymentPic + ", phone=" + phone
				+ ", orderRemark=" + orderRemark + ", orderConsignee=" + orderConsignee + ", newDate=" + newDate
				+ ", paymentTime=" + paymentTime + ", deliveryTime=" + deliveryTime + ", customer=" + customer + "]";
	}
	
}
