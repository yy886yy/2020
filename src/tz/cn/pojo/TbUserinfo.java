package tz.cn.pojo;
import java.util.Date;
public class TbUserinfo {
	private Integer id;
	private String userName;
	private String passWord;
	private String email;
	private String phone;
	private String address;
	private String pic;
	private String sex;
	private Date newDate;
	private Integer state;
	private String province;
	private String city;
	private String area;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "TbUserinfo [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", pic=" + pic + ", sex=" + sex + ", newDate=" + newDate
				+ ", state=" + state + ", province=" + province + ", city=" + city + ", area=" + area + "]";
	}
}
