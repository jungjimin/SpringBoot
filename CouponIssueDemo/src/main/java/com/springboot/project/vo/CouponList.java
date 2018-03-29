package com.springboot.project.vo;

public class CouponList 
{
	
	private int pageNo;
    private int rowCount;
	private int start;
    private int end;
	
    public void setPageNo(int pageNo) 
    {
    	this.pageNo = pageNo;     
    }

    public int getPageNo() 
    {
        return pageNo;
    }
    
    public void setRowCount(int rowCount) 
    {
        this.rowCount = rowCount;
    }
    
	public int getRowCount() 
	{
        return rowCount;
	}
   
    public void setStart(int pageNo,int rowCount) 
    {
    	this.start = (pageNo-1)*rowCount;     
    }

    public int getStart() 
    {
        return start;
    }
    
    public void setEnd(int rowCount) 
    {
    	this.end = rowCount;     
    }
   
    public int getEnd() 
    {
        return end;
    }
    
    public CouponList() 
    {
    }


}
