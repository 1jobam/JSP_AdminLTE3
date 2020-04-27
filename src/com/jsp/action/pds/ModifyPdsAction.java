package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class ModifyPdsAction implements Action{

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService; 
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/modify_success";
	
		try {
			PdsVO pds = fileUpload(request);
			pdsService.modify(pds);
		}catch(Exception e) {
			e.printStackTrace();
			url = "pds/modify_fail";
		}
		
		return url;
	}
	
	//업로드 환결 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1023 * 3; //3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; //50MB
	
	private PdsVO fileUpload(HttpServletRequest request) throws Exception {
		PdsVO pds = new PdsVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		//1. 파라메터 데이터 저장 : rquest.getParameter() X -> enctype
		
		//업로드를 위한 upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//저장을 위한 threshold memory 적용.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//임시 저장 위치 결정.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		//업로드 파일 크기 적용
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//업로드  request 크기 적용
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//실제 저장 경로를 설정
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		try {
			List<FileItem> formItems = upload.parseRequest(request);
			List<Integer> deleteAttachList = new ArrayList<Integer>();
			
			String title = null;
			String content = null;
			String writer = null;
			int pno = -1;
			
			for(FileItem item : formItems) {
				//1.1필드
				if(item.isFormField()) {
					//파라메터 구분 (파레메터 이름)
					switch(item.getFieldName()) {
					case "title" :
						title = item.getString("utf-8");
						break;
					case "writer" :
						writer = item.getString("utf-8");
						break;
					case "content" :
						content = item.getString("utf-8");
						break;
					case "pno" :
						pno = Integer.parseInt(item.getString("utf-8"));
						break;
					case "deleteFile" :
						deleteAttachList.add(Integer.parseInt(item.getString("utf-8")));
						break;
					}
					
				}else {//1.2파일
					//summernote의 files를 제외함.
					if(!item.getFieldName().equals("uploadFile")) continue;
					
					String fileName = new File(item.getName()).getName();
					fileName =MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					
					//local HDD에 저장.
					try {
						item.write(storeFile);
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
					
					//DB에 저장할 attach에 file 내용 추가.
					AttachVO attach = new AttachVO();
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
					attachList.add(attach);

				}

			}
			
			PdsVO pdsList = pdsService.read(pno);
			List<AttachVO> deleteAttach = pdsList.getAttachList();
			
			for(AttachVO attachCheckList : deleteAttach) {
				
				for(Integer deleteCheckAno : deleteAttachList) {

					if(deleteCheckAno == attachCheckList.getAno()) {
						String deletePath = attachCheckList.getUploadPath() + File.separator + attachCheckList.getFileName();
						System.out.println(deletePath);
						File files = new File(deletePath);
						if(files.exists()) {
							files.delete();
						}
					}else {
						attachList.add(attachCheckList);
					}
					
				}
				
			}
			 
			//PdsVO 생성
			pds.setPno(pno);
			pds.setTitle(title);
			pds.setWriter(writer);
			pds.setContent(content);
			pds.setAttachList(attachList);
			
			request.setAttribute("pds", pds);
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw e;
		}
		
		
		
		return pds;
	}

}
