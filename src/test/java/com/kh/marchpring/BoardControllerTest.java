package com.kh.marchpring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// Controller 및 웹 환경에서 사용되는 빈을 자동 생성 등록 (WebApplicationContext 의존성 주입)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
									, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
	}
	
	// test
	@Test
	public void testDoBoardInsert() {
		try {
			mockMvc.perform(
					post("/board/register")
					.param("boardTitle", "JUnit-MockMVC Controller Test")
					.param("boardContents", "MockMVC Board Register Test!")
					.param("boardWriter", "MockMVC Writer")
					
					.param("boardFilename", "MockMVC Strat.png")
					.param("boardFileRename", "202304101128.png")
					.param("boardFilepath", "D://final//JUnit//MockMVC"))
			.andDo(print())
//			.andExpect(status().isOk());	// 해당 코드는 200(성공)을 체크하는 것이므로 빨간불
				// 200 - 성공, 302 - 리다이렉트, 404 - Not Found,
				// 500 - Internal Server Error, 400 - 파라미터 개수 불일치, 
				// 405 - 메소드 불일치
			.andExpect(status().is(302));
			System.out.println(">>>>>>>> 테스트 수행 성공 <<<<<<<<");
			
		} catch (Exception e) {
			System.out.println(">>>>>>>> 테스트 수행 실패 <<<<<<<<" + e.getMessage());
		}
	}

}
