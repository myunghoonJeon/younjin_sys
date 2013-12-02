package org.youngjin.net.upload;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UploadService {
	public String getFreeFilePath(String ... paths);
	
	public String transferFile(CommonsMultipartFile uploadedFile, String ... paths);
	
	
}
