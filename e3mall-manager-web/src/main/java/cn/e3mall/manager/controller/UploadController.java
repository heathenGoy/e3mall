package cn.e3mall.manager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.kindEditor.PictureResult;
import cn.e3mall.common.utils.FastDFSClient;

@Controller
public class UploadController {
	@Value("${server_url}")
	private String serverURL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Object upload(MultipartFile uploadFile) {
		try {
		String fileName = uploadFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		FastDFSClient client = new FastDFSClient("classpath:fds.properties");
		String picUrl = client.uploadFile(uploadFile.getBytes(), extName);
			
			PictureResult result = new PictureResult(0, serverURL + picUrl);
			
			
			return result;
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			PictureResult result = new PictureResult(1, null, "上传图片失败");
			
			return result;
		}
	}
	
	
}
