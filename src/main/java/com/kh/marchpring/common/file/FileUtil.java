package com.kh.marchpring.common.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileUtil {

	public Map<String, String> saveFile(
			MultipartFile multi
			, HttpServletRequest request) throws Exception {
		Map<String, String> result = null;
		String renameFilePath = null;
		String renameFileName = null;
		
		if(multi != null && !multi.equals("")) {
			result = new HashMap<String, String>();
			
			String originalFilename = multi.getOriginalFilename();
			// file을 server의 특정 위치에 저장
			// 경로를 가져와서 폴더의 경로 지정
			String wasRoot = request.getSession().getServletContext().getRealPath("resources");
			
			// 실제 저장 경로 ("폴더의 이름" 지정)
			String savePath = wasRoot + "\\" + "bFileUploads";
			
			// 저장할 폴더가 안만들어져 있다면 만들어줘야 함.
			File folder = new File(savePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 올린 시간을 파일명으로 지정
			renameFileName = sdf.format(new Date(System.currentTimeMillis())) + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			// ex) 파일명 : a.png 일 때 .(점)을 기준으로 자름 => substring. originalFILENAME에서 .의 인덱스를 구해 
			
			// .을 기준으로 자른 파일경로에 실제로 저장
			renameFilePath = savePath + "\\" + renameFileName;
			
	// 방법1. try-catch 사용하는 방법
			/*
			try {
				multi.transferTo(new File(filePath));	// 파일 저장
			} catch (IllegalStateException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			*/
			
	// 방법2. Add throws declaration 사용하는 방법
			multi.transferTo(new File(renameFilePath));	// 파일 저장
			
			result.put("original", originalFilename);
			result.put("rename", renameFileName);
			result.put("renameFilepath", renameFilePath);
		}
		return result;
	}
}
