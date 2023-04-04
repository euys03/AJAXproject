package com.kh.marchpring.photo.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.marchpring.photo.domain.Photo;

public interface PhotoStore {

	/* 사진 등록 Store */
	public int insertPhoto(SqlSession session, Photo photo);

	/* 사진 목록 Store */
	public List<Photo> morePhoto(SqlSession session, Integer start);

}
