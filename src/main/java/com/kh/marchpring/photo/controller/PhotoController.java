package com.kh.marchpring.photo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.kh.marchpring.photo.domain.Photo;
import com.kh.marchpring.photo.service.PhotoService;

public class PhotoController {
	
	@Autowired
	private PhotoService pService;
	
//	@RequestMapping(value="/more", method = RequestMethod.POST, produces="application/json;charset=utf-8")
//	public String viewPhotoList(Integer start) {
//		List<Photo> pList = pService.morePhoto(start);
//		if(!pList.isEmpty()) {
//			return new Gson().toJson(pList);
//		}
//		return null;
//	}
}
