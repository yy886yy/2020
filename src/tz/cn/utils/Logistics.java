package tz.cn.utils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
//查看快递的一个类
public class Logistics {
	static Map<String,String> map=new HashMap<>();
	static {
		map.put("圆通","yuantong");
		map.put("申通","shentong");
		map.put("中通","zhongtong");
		map.put("韵达","yunda");
		map.put("顺丰","shunfeng");
		map.put("汇通快递","huitongkuaidi");
		map.put("EMS","ems");
		map.put("京东", "jd");
	}
	public static String getLogisticsContent(String logisticsName,String orderlogisticsId){
		String resp="";
		String type=map.get(logisticsName);
		 String url = "http://www.kuaidi100.com/query?"
	        		+ "type="+type+"&postid="+orderlogisticsId+"&"
	        		+ "id=1&valicode=&temp=0.3015635129995644";
		/* if(type.equals("shunfeng")){
			 phone=phone.substring(phone.length()-4,phone.length());
			 url+="&phone="+phone;
		 }*/
		 try {  
	            HttpURLConnection.setFollowRedirects(true);  
	            HttpURLConnection http = (HttpURLConnection) (new URL(url).openConnection());  
	            http.setDoOutput(true);  
	            http.setDoOutput(true);  
	            http.setInstanceFollowRedirects(true);  
	            http.setRequestMethod("GET");  
	            http.setRequestProperty("Connection", "keep-alive");  
	            http.setRequestProperty("X-Requested-With", "XMLHttpRequest");  
	            http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.92 Safari/537.1 LBBROWSER");  
	            http.setRequestProperty("Accept", "*/*");  
	            http.setRequestProperty("Referer", "http://www.kuaidi100.com/");  
	            http.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");  
	            http.setRequestProperty("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");  
	            http.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");  
//	            System.out.println("response  is : "+http.getResponseCode()+" "+http.getResponseMessage());  
	            String contentEncoding = http.getContentEncoding();  
//	            System.out.println("response  encoding is : "+contentEncoding);  
	            InputStream  in = null;  
	            if("gzip".equalsIgnoreCase(contentEncoding)){  
	                in = new GZIPInputStream(http.getInputStream());   
	            }else{  
	                in = http.getInputStream();  
	            }  
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	            int data = -1;  
	            while((data = in.read())!=-1){  
	                baos.write(data);  
	            }  
	             resp = baos.toString("utf8");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
		return resp;
	}
	
}
