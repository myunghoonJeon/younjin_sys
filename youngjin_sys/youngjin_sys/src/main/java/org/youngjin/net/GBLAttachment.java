package org.youngjin.net;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GBLAttachment {

	private Integer seq;
	private String gblNo;
	private String fileName;
	private String filePath;
	private String fileExtension;
	private String mimeType;
	private long fileSize;

	private String gblUpdateDate;

	private Integer gblFileNo;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getGblNo() {
		return gblNo;
	}

	public void setGblNo(String articleId) {
		this.gblNo = articleId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getGblFileNo() {
		return gblFileNo;
	}

	public void setGblFileNo(Integer gblFileNo) {
		this.gblFileNo = gblFileNo;
	}

	private CommonsMultipartFile commonsMultipartFile;

	public CommonsMultipartFile getCommonsMultipartFile() {
		return commonsMultipartFile;
	}

	public void setCommonsMultipartFile(
			CommonsMultipartFile commonsMultipartFile) {
		this.commonsMultipartFile = commonsMultipartFile;
	}

	public String getGblUpdateDate() {
		return gblUpdateDate;
	}

	public void setGblUpdateDate(String gblUpdateDate) {
		this.gblUpdateDate = gblUpdateDate;
	}

	public GBLAttachment(CommonsMultipartFile commonsMultipartFile) {
		super();
		this.commonsMultipartFile = commonsMultipartFile;

		setFileName(commonsMultipartFile.getOriginalFilename());
		setFileExtension(getExtension(commonsMultipartFile
				.getOriginalFilename()));
		setMimeType(commonsMultipartFile.getContentType());
		setFileSize(commonsMultipartFile.getSize());
	}

	public GBLAttachment() {
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		String ext = null;
		int i = fileName.lastIndexOf('.');

		if (i > 0 && i < fileName.length() - 1) {
			ext = fileName.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
