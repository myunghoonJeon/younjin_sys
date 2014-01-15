package org.youngjin.net.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
import org.youngjin.net.GBLAttachment;

@Component
public class DownloadView extends AbstractView {

	public DownloadView() {
        setContentType("application/download; utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		GBLAttachment attachment = (GBLAttachment)model.get("attachment");
		
		File file = new File(attachment.getFilePath());
		

		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		String userAgent = request.getHeader("User-Agent");
		
		boolean ie = userAgent.indexOf("MSIE") > -1;
		 
		String fileName = attachment.getFileName();
		
		if (ie) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else { 
			fileName = new String(fileName.getBytes("utf-8"));
		}
        
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		OutputStream responseOut = response.getOutputStream();
		
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(file);     
			FileCopyUtils.copy(fileIn, responseOut);     
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch(Exception e) {}
			}
		}
		responseOut.flush();
	}
}
