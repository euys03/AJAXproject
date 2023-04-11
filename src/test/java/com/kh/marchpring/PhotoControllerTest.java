package com.kh.marchpring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PhotoControllerTest {
	
	private final static Logger logger = LoggerFactory.getLogger(PhotoControllerTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		// 클래스의 static() 메소드 사용하여 생성
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		//logger.debug("셋업 완료");	// debug - 디버깅 시 
		logger.info("셋업 완료");		// info - 서버 실행 시
	}
	
	@Test
	public void testViewPhotoInsert() {
		try {
			mockMvc.perform(post("/photo/insert")
					.param("photoWriter", "gpt")
					.param("photoContent", "무엇이든 물어보세요")
					.param("photoFilename", "chatgpt.png")
					.param("photoFileRename", "202304110934.png")
					.param("photoFilepath", "D://Final//JUnit"))
				.andDo(print())			// 콘솔출력
				.andExpect(status().is(302));	
					// 200 - 성공, 302 - 리다이렉트, 404 - Not Found,
					// 500 - Internal Server Error, 400 - 파라미터 개수 불일치, 
					// 405 - 메소드 불일치
			logger.info("테스트수행 성공");
		} catch (Exception e) {
			logger.info("테스트 수행 실패 : " + e.getMessage());
		}
	}
	
}
