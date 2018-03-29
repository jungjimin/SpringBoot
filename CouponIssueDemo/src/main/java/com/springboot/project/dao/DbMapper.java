package com.springboot.project.dao;

import java.util.Collection;
import java.util.HashMap;

import com.springboot.project.vo.Coupon;
import com.springboot.project.vo.CouponList;

public interface DbMapper 
{
	//쿠폰 리스트 조회 
	public Collection<? extends HashMap<String, Object>> selectCouponList(CouponList list) throws Exception;

    //쿠폰 생성  
	public void insertCoupon(Coupon coupon) throws Exception;
	
    //이메일 조회 
	public int selectEmail(String email) throws Exception;
	
	//시퀀스 생성 
	public void updateSeq() throws Exception;
	
	//시퀀스 조회 
    public int selectCurrentSeq() throws Exception;

}


