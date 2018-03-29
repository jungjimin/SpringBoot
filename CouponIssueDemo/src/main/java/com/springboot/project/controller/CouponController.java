package com.springboot.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.dao.DbMapper;
import com.springboot.project.util.CommonUtil;
import com.springboot.project.vo.Coupon;
import com.springboot.project.vo.CouponList;

import net.minidev.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@RestController
public class CouponController 
{
	@Autowired
	private DbMapper dbMapper;

	/* ========================================================= */
	/* 쿠폰 전체 리스트 조회
	/*========================================================== */
	@Test
	@RequestMapping("/CouponList")
	public @ResponseBody JSONObject selectCouponList(CouponList paramCouponList) throws Exception 
	{
		JSONObject jsonObject = new JSONObject();
		CouponList couponList = new CouponList();

		couponList.setRowCount(paramCouponList.getRowCount());
		couponList.setStart(paramCouponList.getPageNo(), paramCouponList.getRowCount());
		couponList.setEnd(paramCouponList.getRowCount());

		jsonObject.put("list", dbMapper.selectCouponList(couponList));

		return jsonObject;
	}

	/* ========================================================= */
	/* 쿠폰 발행 요청 
	/*===========================================================*/
	@Test	
	@RequestMapping(value = "/CouponIssue", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> couponIssue(Coupon paramCoupon) throws Exception 
	{
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		CommonUtil commonUtil = new CommonUtil();
		Coupon coupon = new Coupon();

		String strEmail;
		boolean isUsed = false;
		int currentSeq;

		/*--------------------------------------------------------*/
		/* 이메일 체크 
		/*--------------------------------------------------------*/
		// 영문만 입력가능 (한글 거절처리)
		if (commonUtil.containsHangul(paramCoupon.getEmail()) == true) 
		{
			jsonObject.put("res_cd", "0200");
			jsonObject.put("res_msg", "이메일은 영문입력만 가능합니다.");

			return jsonObject;
		}

		// 이메일 조회 (존재하는 이메일 거절처리)
		if (dbMapper.selectEmail(paramCoupon.getEmail()) != 0)
			isUsed = true;

		if (isUsed) 
		{
			jsonObject.put("res_cd", "0201");
			jsonObject.put("res_msg", "이미 존재하는 이메일입니다.");

			return jsonObject;
		}

		// 이메일 길이 체크 (5자 이상만 입력가능)
		if (paramCoupon.getEmail().length() < 5) 
		{
			jsonObject.put("res_cd", "0202");
			jsonObject.put("res_msg", "이메일은 5자 이상만 입력가능합니다.");

			return jsonObject;
		}
		/*--------------------------------------------------------*/		

		/*--------------------------------------------------------*/
		/* 쿠폰번호 생성  
		/*--------------------------------------------------------*/
		// 다음 시퀀스 조회
		dbMapper.updateSeq();
		currentSeq = dbMapper.selectCurrentSeq();
		// 이메일 특수문자 제거
		strEmail = commonUtil.removeSpecialCharacters(paramCoupon.getEmail());
		// 쿠폰번호 생성
		String couponNo = commonUtil.makeCouponNo(strEmail, 5, currentSeq);

		coupon.setEmail(paramCoupon.getEmail());
		coupon.setConponNo(couponNo);

		// DB처리 (쿠폰리스트에 추가)
		dbMapper.insertCoupon(coupon);
		/*--------------------------------------------------------*/		

		// 응답처리
		jsonObject.put("res_cd", "0000");
		jsonObject.put("res_msg", "쿠폰이 발급되었습니다.");

		return jsonObject;
	}
}
