package com.codingbamboo.miniproject.attach.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.codingbamboo.miniproject.attach.service.AttachService;
import com.codingbamboo.miniproject.common.FileUploadUtils;
import com.codingbamboo.miniproject.notice.service.NoticeService;

@Controller
public class AttachController {
	@Autowired
	AttachService attachService;
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	FileUploadUtils fileUploadUtils;
	
}
