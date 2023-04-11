package com.kh.marchpring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.marchpring.board.domain.Board;
import com.kh.marchpring.board.service.BoardService;

// 1. 실행 준비
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardServiceImplTest {
	
	@Autowired
	private BoardService bService;
	
	
	// 2. Test 어노테이션 작성
	@Test
	public void testRegisterBoard() {
		// 3. 클래스 작성
		Board board = new Board();
		board.setBoardTitle("Hello JUnit4!");
		board.setBoardContents("Hello Hi Board JUnit Test Go!");
		board.setBoardWriter("admin");
		board.setBoardFilename("JUnit4 Tutorial.jpg");
		board.setBoardFileRename("202304100935.jpg");
		board.setBoardFilepath("D://final//JUnit4");
		// 4. 검증 메소드 assert 작성 (JUnit에서 제공)
		assertEquals(1, bService.registerBoard(board));
		//bService.registerBoard(board);
	}
	
	@Test
	public void testSelectAllBoard() {
		// 게시물의 개수
		assertEquals(25, bService.selectAllBoard().size());
	}
	
	@Test
	public void testSelectOneBoard() {
		// 1) NULL인지 아닌지 여부 확인(DB에 값(BOARD_NO)이 존재하므로 JUnit 빨간색)
		//assertNull(bService.selectOneBoard(30));
		// 2) 입력되어 있는 값과 일치한지 (DB에 존재하는 값(BOARD_NO)과 일치하므로 초록색)
		assertSame(32, bService.selectOneBoard(32).getBoardNo());
	}
	
	
	
	
	
	// JUnit이란?
	// Java 언어를 사용하는 개발자들이 자동화된 단위 테스트를 작성할 수 있도록
	// 지원하는 오픈소스 테스트 프레임워크이다.
	// JUnit은 TDD 및 BDD와 같은 소프트웨어 개발 방법론에서 자주 사용된다.
	
	// TDD(Test De : 테스트 기반 개발을 통해 소프트웨어의 품질을 높이는 개발방법론
	// BDD(B
	
	// JUnit은 어노테이션을 활용하여 테스트 클래스, 메소드, 그리도 테스트 실행조건을 정의한다.
	// 다양한 검증 메소드를 제공하여 테스트 시행 결과를 검증하고 실패할 경우에는 예외를 발생시킨다.
	// 개발자가 테스트 코드를 더욱 쉽게 작성하고 테스트 코드를 더욱 간결하게 유지하도록 도와준다.
	
	// JUnit을 사용하기 위한 사전 셋팅  -> Annotiation으로 설정
	
	// JUnit 사용하기 위한 순서
	// 1. pom.xml에 dependency 추가
	// 2. test 클래스 작성 후
	// 3. test 메소드 실행
	
	// DB session을 통해 DB를 가져와 테스트할 수 있다.
	// 의존성 주입
	// test+메소드명
}
