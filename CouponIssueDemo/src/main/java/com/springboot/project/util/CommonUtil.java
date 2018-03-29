package com.springboot.project.util;

public class CommonUtil 
{	
	/*--------------------------------------------------------*/
	/* 한글 포함여부 체크  
	/*--------------------------------------------------------*/
	public Boolean containsHangul(String str) 
	{
		Boolean bHangul = false;
		
		if( str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
			bHangul = true;

		return bHangul;
	}

	/*--------------------------------------------------------*/
	/* 특수문자 제거   
	/*--------------------------------------------------------*/
    public String removeSpecialCharacters(String str) 
    {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str = str.replaceAll(match, "");
		return str;
	}
    
	/*--------------------------------------------------------*/
	/* 쿠폰번호 생성   
	/*--------------------------------------------------------*/
    public String makeCouponNo(String str, int length, int seq ) 
    {    	  
    	  String newStr;  
    	  String inTime; 
    	  String suffix;  
    	  
    	  newStr = str.substring(0, length);        
          inTime = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
  	      suffix = String.format("%05d", seq); 
          
          return newStr + inTime + suffix;
	} 
}
