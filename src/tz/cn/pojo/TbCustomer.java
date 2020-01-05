package tz.cn.pojo;

public class TbCustomer {
	private Integer id;
	private String name;
	private String phone;
	private String wechat;
	private String wechatNetname;
	private String qq;
	private String qqNetname;
	private String address;
	private String sex;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getWechatNetname() {
		return wechatNetname;
	}
	public void setWechatNetname(String wechatNetname) {
		this.wechatNetname = wechatNetname;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getQqNetname() {
		return qqNetname;
	}
	public void setQqNetname(String qqNetname) {
		this.qqNetname = qqNetname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "TbCustomer [id=" + id + ", name=" + name + ", phone=" + phone + ", wechat=" + wechat
				+ ", wechatNetname=" + wechatNetname + ", qq=" + qq + ", qqNetname=" + qqNetname + ", address="
				+ address + ", sex=" + sex + "]";
	}
	
}
