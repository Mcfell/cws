package com.ckc.cws.view;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Parks;
import com.ckc.cws.entity.locksList;
import com.ckc.cws.mapper.CarlocksMapper;
import com.ckc.cws.mapper.ParksMapper;

@Controller
@RequestMapping("/parks")
public class parkController {

	@Resource
    ParksMapper parksMapper;
    @RequestMapping("insertParks")
	public String insertParks(Parks parks){
    	parksMapper.insertSelective(parks);
		return "testInsert";
	}
    
	@Resource
    CarlocksMapper lockMapper;
    @RequestMapping("insertLocks")
    public @ResponseBody locksList insertLocks(locksList locks){
    	List<Carlocks> locks2 = locks.getCarlocks();
    	for (Carlocks lock : locks2) {
    		lockMapper.insertSelective(lock);
    	}
		return locks;
	}
}
