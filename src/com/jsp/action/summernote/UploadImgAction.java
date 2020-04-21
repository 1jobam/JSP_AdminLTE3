package com.jsp.action.summernote;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class UploadImgAction implements Action{

	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; // 2MB
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			return null;
		}
		
		//업로드를 위한 upload 환결설정 적용
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//저장을 위한 threshold memory 적용.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//임시 저장 위치 결정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		//업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//업로드 request크기 적용
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		String uploadPath = GetUploadPath.getUploadPath("board.img");
		
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패했습니다.");
		};
		
		try{
			List<FileItem> formItems = upload.parseRequest(request);
			//파일 개수 확인.
			if(formItems != null && formItems.size() > 0) {
				for(FileItem item : formItems) { // form items 반복하여 꺼내는 구문
					if(!item.isFormField()) { // 파일일 경우 해당
						//uuid+구분자+파일명
						String fileName = MakeFileName.toUUIDFileName(".jpg", "");
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						
						// local HDD 에 저장.
						item.write(storeFile);
						
						PrintWriter out = response.getWriter();
						//String outStr = urlPath+ "/" + fileName;
						String outStr = request.getContextPath() + "/getImg.do?fileName=" + fileName;
						out.print(outStr);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

}
