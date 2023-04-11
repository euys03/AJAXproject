package com.kh.marchpring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.marchpring.photo.domain.Photo;
import com.kh.marchpring.photo.service.PhotoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
								, "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PhotoServiceImplTest {

	// 실습
	// 1. insertPhoto 메소드가 잘 동작하는지 테스트 해보세요
	@Autowired
	private PhotoService pService;
	
	@Test
	public void testInsertPhoto() {
		Photo photo = new Photo();
		photo.setPhotoWriter("JUnit4");
		photo.setPhotoContent("JUnit4 Photo Insert Test");
		photo.setPhotoFilename("JUnit4 Photo.png");
		photo.setPhotoFileRename("202304101112.png");
		photo.setPhotoFilepath("D://final//JUnit4//Test");
		//pService.insertPhoto(photo);
		// 검증메소드
		assertEquals(1, pService.insertPhoto(photo));
	}
	
	// 2. updatePhoto 비즈니스 로직 작성 후 JUnit 테스트 해보세요
	@Test
	public void testUpdatePhoto() {
		Photo photo = new Photo();
		photo.setPhotoWriter("JUnit5");
		photo.setPhotoContent("JUnit5 Photo Insert Test");
		photo.setPhotoFilename("JUnit5 Photo.png");
		photo.setPhotoFileRename("202304101142.png");
		photo.setPhotoFilepath("D://final//JUnit5//Test");
		// DB에 존재하는 PHOTO_NO 하나 작성
		photo.setPhotoNo(22);
		// 검증메소드
		assertEquals(1, pService.updatePhoto(photo));
	}
	
	
	
}
