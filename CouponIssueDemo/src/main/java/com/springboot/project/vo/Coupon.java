package com.springboot.project.vo;

public class Coupon 
{ 
	private String email;
    private String coupon_no;
	
    public void setEmail(String email) 
    {
        this.email = email;
    }
    
    public String getEmail() 
    {
        return email;
    }
    
    public void setConponNo(String coupon_no) 
    {        
        this.coupon_no = coupon_no;
    }

    public String getConponNo() 
    {
        return coupon_no;
    }
    
    public Coupon() 
    {
    }
    
}


