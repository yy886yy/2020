package tz.cn.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tz.cn.pojo.TbArea;
import tz.cn.pojo.TbCity;
import tz.cn.pojo.TbProvince;
import tz.cn.service.ProService;
import tz.cn.utils.UploadPic;
import tz.cn.vo.PicVO;
@Controller
@RequestMapping("address")
public class ProController {
	@Autowired
	private ProService proService;
	
	//查询省级信息
	@RequestMapping("/findProvince")
	@ResponseBody
	public List<TbProvince> findProvince(){
		return proService.findProvince();
	}
	//查询市级信息
	@RequestMapping("/findCityByCode")
	@ResponseBody
	public List<TbCity> findCityByCode(String provincecode){
		return proService.findCityByCode(provincecode);
	}
	//查询县区级信息
	@RequestMapping("/findAreaByCode")
	@ResponseBody
	public List<TbArea> findAreaByCode(String citycode){
		return proService.findAreaByCode(citycode);
	}
	//上传图片(file文件自动从前台传过来)
	@RequestMapping("/uploadPic")
	@ResponseBody				
	public PicVO uploadPic(MultipartFile file) {
		PicVO pvo=new PicVO();
		try {
			String fileName = UploadPic.upload(file);
			pvo.setData(fileName);
			return pvo;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pvo;
	}
}
