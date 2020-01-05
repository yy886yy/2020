package tz.cn.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tz.cn.dao.SynthesizeDao;
import tz.cn.service.SynthesizeService;
@Service
public class SynthesizeImpl implements SynthesizeService{
	@Autowired
	private SynthesizeDao dao;
	@Override
	public List<Map<String, Object>> findYear() {
		return dao.findYear();
	}
	@Override
	public List<Map<String, Object>> findQuarter() {
		return dao.findQuarter();
	}
	@Override
	public List<Map<String, Object>> findMonth() {
		return dao.findMonth();
	}

}
