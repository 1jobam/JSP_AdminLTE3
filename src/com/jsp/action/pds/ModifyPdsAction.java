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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dao.AttachDAO;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.service.PdsService;
import com.jsp.utils.CreatePageMaker;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class ModifyPdsAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService=pdsService;		
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="pds/modify_success";
		
		PdsVO pds = null;
		try {
			pds=modifyFile(request,response);
			pdsService.modify(pds);

		}catch(SQLException e) {
			e.printStackTrace();
			url="pds/modify_fail";
		}catch(Exception e) {
			e.printStackTrace();
			url="error/500_error";
		}finally {
			request.setAttribute("pds", pds);
		}
		
		
		return url;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	
	private PdsVO modifyFile(HttpServletRequest request, HttpServletResponse response)
								throws Exception{
		PdsVO pds = new PdsVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		// 업로드를 위한 upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 실제 저장 경로를 설정.
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");

		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		int pno = -1;
		String title = null;
		String content = null;
		String writer = null;
		
		SearchCriteria cri = new SearchCriteria();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		// request로 부터 받는 파일을 추출해서 저장.
		
		List<FileItem> formItems = upload.parseRequest(request);
		if (formItems != null && formItems.size() > 0) {			
			List<String> deleteFile = new ArrayList<String>();
			for (FileItem item : formItems) {
				if (!item.isFormField()) { // 파일가져오기
					//summernote의 files 를 제외함.
					if(!item.getFieldName().equals("uploadFile")) continue;
					
					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator
							+ fileName;
					File storeFile = new File(filePath);

					// local HDD 에 저장.
					item.write(storeFile);

					// DB에 저장할 attach에 file 내용 추가.
					AttachVO attach = new AttachVO();
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFileType(fileName.substring(fileName
							.lastIndexOf(".") + 1));

					attachList.add(attach);

					System.out.println("upload file : " + attach);

					request.setAttribute("message", "업로드 되었습니다.");
				}else {
					switch (item.getFieldName()) {
					case "page" :
						cri.setPage(Integer.parseInt(item.getString("utf-8")));
						break;
					case "perPageNum" :
						cri.setPerPageNum(Integer.parseInt(item.getString("utf-8")));
						break;
					case "keyword" :
						cri.setKeyword(item.getString("utf-8"));
						break;
					case "searchType" :
						cri.setSearchType(item.getString("utf-8"));
						break;
					case "title":
						title = item.getString("utf-8");
						break;
					case "writer":
						writer = item.getString("utf-8");
						break;
					case "content":
						content = item.getString("utf-8");
						break;
					case "pno":
						pno = Integer.parseInt(item.getString("utf-8"));
						break;
					case "deleteFile":
						int ano = Integer.parseInt(item.getString("utf-8"));
						
						AttachVO attach=attachDAO.selectAttachByAno(ano);
						
						String filePath = attach.getUploadPath() 
								+ File.separator + attach.getFileName();
						File targetFile = new File(filePath);
						
						targetFile.delete(); //파일 삭제
						
						attachDAO.deleteAttach(ano); //DB 삭제
						
						deleteFile.add(item.getString("utf-8"));
						
						break;
					}
				}
			}
			
			pds.setTitle(title);
			pds.setContent(content);
			pds.setWriter(writer);
			pds.setPno(pno);
			pds.setAttachList(attachList);		
			
			request.setAttribute("pageMaker", pageMaker);
			
			System.out.println("receive pds : \n" + pds);
			System.out.println("deleteFile : \n" + deleteFile);
		}
		return pds;				
	}
}








