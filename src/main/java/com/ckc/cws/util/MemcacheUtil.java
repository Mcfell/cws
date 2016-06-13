package com.ckc.cws.util;

import java.util.Properties;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;

public class MemcacheUtil {

	@Resource
	MemcachedClient memcachedClient;
	private MemcachedClient getConnectionClient() {
		return memcachedClient;
	}
}
