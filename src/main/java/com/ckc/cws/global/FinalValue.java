package com.ckc.cws.global;


public class FinalValue
{
		//返回码常用值
		public static final int NULL = 404;//请求资源不存在
		public static final int FAILED = 400;//请求失败
		public static final int SUCCESS = 200;//请求成功
		
		//初始化默认值
		public static final int INIT_VALUE = 0;
		
		//用户权限值
		public static final int NOT_AVAILABLE = -1;//用户不可用
		public static final int REG_FIRST_STEP = 1;//普通用户
		public static final int REG_SECOND_STEP = 2;//VIP用户
		public static final	int AVAILABLE = 3;//可用的，管理员通过审核或者取消黑名单
		
		//校验类型
		public static final	int PHONE = 99;//可用的，管理员通过审核或者取消黑名单
		public static final	int PWD = 98;//可用的，管理员通过审核或者取消黑名单
		//注册失败信息
		public static final	int NULL_PARAMETERS = 100;//注册参数有空值
		public static final	int PHONE_HASREGISGER = 101;//手机已经被注册
		public static final	int PHONE_ERROR = 102;	//手机验证失败
		public static final	int PWD_ERROR = 103;	//密码验证失败
}
