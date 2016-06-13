package com.ckc.cws.util;

import javax.annotation.Resource;

import com.ckc.cws.mapper.ParksMapper;

public class ParkInfoGenerator {

	@Resource
	ParksMapper parksMapper;
	
	private static ParkInfoGenerator parkInfoGenerator;
	
	static{
		parkInfoGenerator = new ParkInfoGenerator();
	}
	private ParkInfoGenerator(){
		
	}
	public static ParkInfoGenerator getInstance() {
		return parkInfoGenerator;
	}
	
	public void LoadParkInfoToCache() {
	}
}
