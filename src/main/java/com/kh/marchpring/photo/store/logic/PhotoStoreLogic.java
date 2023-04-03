package com.kh.marchpring.photo.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.marchpring.photo.domain.Photo;
import com.kh.marchpring.photo.store.PhotoStore;


@Repository
public class PhotoStoreLogic implements PhotoStore{

	
	/* 사진 등록 StoreLogic */
	@Override
	public int insertPhoto(SqlSession session, Photo photo) {
		int result = session.insert("PhotoMapper.insertPhoto", photo);
		return result;
	}

	/* 사진 목록 StoreLogic */
	@Override
	public List<Photo> morePhoto(SqlSession session, Integer start) {
		// namespace와 name값, rowBounds 사용하여 페이징처리하였다
		int limit = 3;	// 한페이지에 가져올 페이징 개수
		int offset = (start - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Photo> pList = session.selectList("PhotoMapper.morePhotoList", null, rowBounds);
		return pList;
	}

}
