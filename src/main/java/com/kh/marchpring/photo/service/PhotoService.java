package com.kh.marchpring.photo.service;

import java.util.List;

import com.kh.marchpring.photo.domain.Photo;

public interface PhotoService {

	/* 사진 등록 Service */
	public int insertPhoto(Photo photo);
	
	public int updatePhoto(Photo photo);

	/* 사진 목록 Service */
	public List<Photo> morePhoto(Integer start);

}
