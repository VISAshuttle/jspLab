package controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FileService;

public class FileDownHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSubmit(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");

		String fileName = req.getParameter("file_name");
		System.out.println("fileName=" + fileName);

		String savePath = "upload";

		ServletContext context = req.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;
		System.out.println("sFilePath=" + sFilePath);

		byte b[] = new byte[4096];
		File oFile = new File(sFilePath);

		FileInputStream in = new FileInputStream(sFilePath);

		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);

		// octet-stream은 8비트로 된 일련의 데이터를 뜻합니다. 지정되지 않은 파일 형식을 의미합니다.
		if (sMimeType == null)
			sMimeType = "application/octet-stream";

		res.setContentType(sMimeType);

		// 한글 업로드 (이 부분이 한글 파일명이 깨지는 것을 방지해 줍니다.)
		String sEncoding = new String(fileName.getBytes("utf-8"), "8859_1");

		res.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);

		ServletOutputStream out2 = res.getOutputStream();
		int numRead;

		// 바이트 배열b의 0번 부터 numRead번 까지 브라우저로 출력
		while ((numRead = in.read(b, 0, b.length)) != -1) {
			out2.write(b, 0, numRead);
		}
		out2.flush();
		out2.close();
		in.close();

		return null;
	}

}
