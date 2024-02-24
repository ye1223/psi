package com.roadjava.psi.context;


import com.roadjava.psi.bean.vo.UserVO;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class UserContext {
	private static  ThreadLocal<UserVO> threadLocal=new ThreadLocal<>();
	
	public static void set(UserVO user){
		threadLocal.set(user);
	}
	public static  UserVO get(){
		return threadLocal.get();
	}
	public static void remove(){
		threadLocal.remove();
	}
}
