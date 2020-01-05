package tz.cn.utils;
import java.io.File;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
public class UploadPic {
	//文件上传
	public static String upload(MultipartFile file) throws Exception{
		//1.获取文件原始名
		String fName = file.getOriginalFilename();
		//2.截取文件名的后缀
		String suffix=fName.substring(fName.lastIndexOf("."));
		//3.获取一个随机字符串
		String strs=UUID.randomUUID().toString().replace("-","");
		//4.将随机字符串和后缀重新拼接
		String fileName=strs+suffix;
		//5.上传文件
		File f=new File("d:\\image\\"+fileName);
		file.transferTo(f);
		return fileName;
	}
	//删除文件
	public static boolean delPic(String fileName) {
		boolean flag=false;
		File file=new File("d:\\image\\"+fileName);
		if(file.exists()&&file.isFile()) {
			file.delete();
			flag=true;
		}
		return flag;
	}
}
