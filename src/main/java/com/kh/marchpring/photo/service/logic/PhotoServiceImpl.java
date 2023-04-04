package com.kh.marchpring.photo.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.marchpring.photo.domain.Photo;
import com.kh.marchpring.photo.service.PhotoService;
import com.kh.marchpring.photo.store.PhotoStore;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	private PhotoStore pStore;
	
	@Autowired
	private SqlSession session;
	
	
	/* 사진 등록 ServiceImpl */
	@Override
	public int insertPhoto(Photo photo) {
		pStore.insertPhoto(session, photo);
		int result = pStore.insertPhoto(session, photo);
		return result;
	}


	/* 사진 목록 ServiceImpl */
	@Override
	public List<Photo> morePhoto(Integer start) {
		List<Photo> pList = pStore.morePhoto(session, start);
		return pList;
	}

}
