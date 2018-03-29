package UnitCode;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.project.controller.CouponController;
import com.springboot.project.util.CommonUtil;

@RunWith(MockitoJUnitRunner.class)
public class CouponIssueTest 
{

	@InjectMocks
	private CouponController controller;
	@Autowired
	private MockMvc mockMvc;

	
	@Before
    public void setup() {
		controller = new CouponController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

	@Test
	public void utilTest() throws Exception 
	{

		/*--------------------------------------------------------*/
		/* 유틸 함수 테스트   
		/*--------------------------------------------------------*/
		CommonUtil commonUtil = new CommonUtil();
		// 한글 포함여부 체크
		assertEquals(true,commonUtil.containsHangul("qweㅈㄴㅇ"));
		// 특수문자 제거 
		assertEquals("jungjimin",commonUtil.removeSpecialCharacters("jung-jimin"));
	}

	@Test
	public void controllerTest() throws Exception 
	{	
		/*--------------------------------------------------------*/
		/* 쿠폰 조회 테스트    
		/*--------------------------------------------------------*/		
	    mockMvc.perform(post("selectCouponList/").content("{\"pageNo\" :1\",\"rowCount\":3\" }"))  
		   .andExpect(status().isOk())
		   .andExpect(forwardedUrl(null)) 
	       .andExpect(model().attributeExists("list"));
		
		/*--------------------------------------------------------*/
		/* 쿠폰 발행 테스트    
		/*--------------------------------------------------------*/
		mockMvc.perform(post("/couponIssue/").contentType(MediaType.APPLICATION_JSON).content("{\"email\" :\"jungji@gmail.com\"}")) 
		  .andExpect(status().isOk())
		  .andExpect(forwardedUrl(null))
		  .andExpect(model().attributeExists("res_cd"))
		  .andExpect(model().attribute("res_cd","0000"));
	}
		
}
