package com.springboot.project;

import java.io.IOException;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.springboot.project")
public class CouponIssueDemoApplication 
{
	public static void main(String[] args) throws IOException 
	{
		SpringApplication.run(CouponIssueDemoApplication.class, args);
	    System.out.println("/*===========================================================*/");
	    System.out.println("  [ver0.1] COUPON ISSUE SERVICE DAEMON STARTED!! by jungjimin  ");
	    System.out.println("/*===========================================================*/");
	}
}
