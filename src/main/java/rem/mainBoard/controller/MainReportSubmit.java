package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import rem.admin.board.report.dao.ReportBoardDaoImpl;
import rem.admin.board.report.service.IReportBoardService;
import rem.admin.board.report.service.ReportBoardServiceImpl;
import rem.admin.board.report.vo.ReportBoardVO;
import rem.file.service.FileServiceImpl;
import rem.file.service.IFileService;
import rem.file.vo.ImgFileVO;
import rem.login.vo.MemberVO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;


@WebServlet("/main/report/submit.do")
@MultipartConfig
public class MainReportSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "remImg"; 

       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
				
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		
		int reportProdNo = Integer.parseInt(request.getParameter("reportProdNo"));
		
		String returnData = "";
		
		if (loginInfo == null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
		}
		else {
			int mem_no = loginInfo.getMem_no();
			String checkNewImg = request.getParameter("checkNewImg");
			String reportTitle = request.getParameter("reportTitle");
			String reportCont = request.getParameter("reportCont");
			int reportType = Integer.parseInt(request.getParameter("reportType"));
			
			ReportBoardVO vo = new ReportBoardVO();
			vo.setMem_no(mem_no);
			vo.setRpt_type(reportType);
			vo.setRpt_text(reportCont);
			vo.setRpt_idx_no(reportProdNo);
			vo.setRpt_title(reportTitle);
			
			
			IReportBoardService service = ReportBoardServiceImpl.getInstance(ReportBoardDaoImpl.getInstance());
			
			int rec = service.insertReportBoard(vo);
			
			if(checkNewImg.equals("OK")) {
				ImgFileVO imgVO = new ImgFileVO();
				
				try {
				    String uploadPath = "d:" + File.separator + UPLOAD_DIR ;
					System.out.println("Upload Path: " + uploadPath);
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						 uploadDir.mkdirs();
					}
					
					Part imgPart = request.getPart("reportImg");
					
					String imgOriginalName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
		            String imgUUIDName = UUID.randomUUID().toString() + "_" + imgOriginalName;
		            String savingFilePath = uploadPath + File.separator + imgUUIDName;
		            String scriptFilePath = "/" + uploadPath.substring(3) + File.separator + imgUUIDName;
		            String imgExtension = "";
		            int index = imgOriginalName.lastIndexOf(".");
		            if(index > 0) imgExtension = imgOriginalName.substring(index + 1);
		            
		            imgVO.setFile_org_name(imgOriginalName);
		            imgVO.setFile_save_name(imgUUIDName);
		            imgVO.setFile_path(scriptFilePath);
		            imgVO.setFile_size((int)Math.ceil(imgPart.getSize() / 1024.0));
		            imgVO.setFile_type(imgExtension);
		            imgVO.setFile_source(100);
		            imgVO.setFile_no(service.getReportNoImmediately(mem_no));
		            imgVO.setFile_total(1);
		            
		            imgPart.write(savingFilePath);
					
				}
				
				catch (Exception e) {
					e.printStackTrace();
				}
				
				IFileService fservice = FileServiceImpl.getInstance();
				int recFile = fservice.insertReportImg(imgVO);
				System.out.println("MypageAccess->rec_file : " + recFile);
			}
			

			if(rec>0) {			
				Gson gson = new Gson();
				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("result", "success");
				
				String json = gson.toJson(map);
				
				PrintWriter out = response.getWriter();
				out.print(json);
				out.flush();
			} else {
				Gson gson = new Gson();
				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("result", "failed");
				
				String json = gson.toJson(map);
				
				PrintWriter out = response.getWriter();
				out.print(json);
				out.flush();
			}
			
		}
	}



}
