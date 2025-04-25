package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import rem.admin.board.qna.service.IQnaService;
import rem.admin.board.qna.service.QnaServiceImpl;
import rem.admin.board.qna.vo.QnaBoardVO;
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

@WebServlet("/main/qna/submit.do")
public class MainQnaSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "remImg"; // 업로드 디렉터리 설정


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
		} else {
			int mem_no 			= loginInfo.getMem_no();
			String checkNewImg 	= request.getParameter("checkNewImg");
			String qna_title 	= request.getParameter("qnaTitle");
			String qna_cont 	= request.getParameter("qnaCont");
			int qna_type 		= Integer.parseInt(request.getParameter("qnaType"));
			
			QnaBoardVO vo = new QnaBoardVO();
			vo.setMem_no(mem_no);
			vo.setQna_cont(qna_cont);
			vo.setQna_title(qna_title);
			vo.setQna_type(qna_type);
			
			IQnaService qservice = QnaServiceImpl.getInstance();
			int rec = qservice.insertQna(vo);
			
			if(checkNewImg.equals("OK")) {
				ImgFileVO imgVO = new ImgFileVO();
				
				try {
					// 이미지 업로드 경로 설정 (서버 실행 경로 기준)
			        String uploadPath = "d:" + File.separator + UPLOAD_DIR;
			        System.out.println("Upload Path: " + uploadPath);
			        File uploadDir = new File(uploadPath);
			        if (!uploadDir.exists()) uploadDir.mkdirs(); // 폴더 없으면 생성
			        
					Part imgPart = request.getPart("qnaImg");
					System.out.println("Received File Name: " + imgPart.getSubmittedFileName());
					
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
		            imgVO.setFile_size((int)Math.ceil(imgPart.getSize() / 1024.0)); //<-KB단위로 저장
		            imgVO.setFile_type(imgExtension);
		            imgVO.setFile_source(100); //<-mapper에 소스별 메소드 구현할 거라 쓰이진 않는 필드.
		            imgVO.setFile_no(qservice.getQnaNoImmediately(mem_no));
		            imgVO.setFile_total(1);

		            imgPart.write(savingFilePath);
		        }
				catch (Exception e) {e.printStackTrace();}
				
				
				IFileService fservice = FileServiceImpl.getInstance();
				int recFile = fservice.insertQnaImg(imgVO);
				System.out.println("MypageAccess->rec_file : " + recFile);
				
			}
			else {}
			
			///ajax success문에서 받을 데이터
			/// 성공이면 success, 실패면 failed
			/// { A : B } 구조의 스크립트객체를 json타입으로 보내기 때문에, jsp에서 key값 호출로 바로 사용가능.
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
