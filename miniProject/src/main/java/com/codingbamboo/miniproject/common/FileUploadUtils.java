package com.codingbamboo.miniproject.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.codingbamboo.miniproject.attach.dto.AttachDTO;

@Component
public class FileUploadUtils {

	@Value("#{util['file.upload.path']}")
	private String uploadPath;
	
	public List<AttachDTO> getAttachListByMultiparts(MultipartFile[] boFileArray) throws IOException{
		List<AttachDTO> attachList = new ArrayList<>();
		
		for(MultipartFile boFile : boFileArray) {
			if(!boFile.isEmpty()) {
				AttachDTO attach = getAttachByMultipart(boFile);
				attachList.add(attach);
			}
		}
		
		return attachList;
	}
	
	public AttachDTO getAttachByMultipart(MultipartFile boFile) throws IOException {
		String fileName = UUID.randomUUID().toString();
		
		File uploadFolder = new File(uploadPath);
		uploadFolder.mkdir();
		
		String filePath = uploadPath + File.separatorChar + fileName;
		
		boFile.transferTo(new File(filePath));
		
		AttachDTO attach = new AttachDTO();
		attach.setAtchFileName(fileName);
		attach.setAtchName(boFile.getOriginalFilename());
		attach.setAtchFileSize(boFile.getSize());
		attach.setAtchFancySize(transferFancySize(boFile.getSize()));
		attach.setAtchContentType(boFile.getContentType());
		attach.setAtchPath(filePath);
		
		return attach;
	}
	
	private String transferFancySize(long size) {
		DecimalFormat df = new DecimalFormat("#,###.0");
		
		if(size < 1024) return size + "B";
		if (size < 1024 * 1024) return df.format(size/1024.0) + "KB";
		if (size < 1024 * 1024 * 1024) return df.format(size/(1024.0 * 1024.0)) + "MB";
		if (size < 1024 * 1024 * 1024) return df.format(size/(1024.0 * 1024.0 * 1024.0)) + "GB";
		return "Å« ÆÄÀÏ";
		
	}
}
