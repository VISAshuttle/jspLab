package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TblAttachList;
import service.FileService;

public class FileViewHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/file/fileView.jsp";
	private FileService fileService = new FileService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return processForm(req, res);
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		int bno = Integer.parseInt(req.getParameter("bno"));
		TblAttachList tblAttachList = fileService.view(bno);
		
		req.getSession().setAttribute("tblAttachList", tblAttachList);
		
		return FORM_VIEW;
	}

}
