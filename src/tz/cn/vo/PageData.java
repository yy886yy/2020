package tz.cn.vo;
//封装数据的一个类
public class PageData {
	private Integer code;  //解析接口状态
    private String msg;    //解析提示文本
    private Integer count; //解析数据长度
    private Object data;   //解析数据列表
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
