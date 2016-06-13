package com.areateast;

import com.casic.entity.map.GenerateRigons;
import com.casic.entity.map.SingleGps;

import junit.framework.TestCase;

public class AreaTestCase extends TestCase{
	
	public void testSelectArea(){
    	SingleGps p1 = new SingleGps(116.259069,39.872615);//西北角
    	SingleGps p2 = new SingleGps(116.535787,39.945826);//东南角
    	int[] regions = GenerateRigons.getRegions(p1, p2);
    	//System.out.println(in[0].toString());
    	
    }
    public void testGetRegions(){
    	int REGION_SIZE = 100;
		int startArea = GenerateRigons.Match_AreaId(116.259069,39.872615);
		int endArea = GenerateRigons.Match_AreaId(116.535787,39.945826);
		int y = endArea%REGION_SIZE-startArea%REGION_SIZE;
		int x = endArea/REGION_SIZE-startArea/REGION_SIZE;
		int[] regs = new int[(x+1)*(y+1)];
		for(int i=0,k=0;i<=x;i++){
			for(int j=0;j<=y;j++){
				regs[k++]=startArea+i*REGION_SIZE+j;
			}
		}
	}
	
}
