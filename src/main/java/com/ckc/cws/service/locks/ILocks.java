package com.ckc.cws.service.locks;

import java.util.List;

import com.ckc.cws.bean.Carlocks;
import com.ckc.cws.bean.Parks;
import com.ckc.cws.entity.Message;

public interface ILocks {

	public Message<Integer, List<Carlocks>> checkCarlocks(Parks parks);

	public Message<Integer, String> getLockAuth(Carlocks carlock);

}
