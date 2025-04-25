package rem.mainBoard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rem.admin.board.qna.vo.QnaSetVO;
import rem.file.service.FileServiceImpl;
import rem.file.service.IFileService;
import rem.file.vo.ImgFileVO;
import rem.login.vo.MemberVO;
import rem.search.dao.SearchDaoImpl;
import rem.search.service.ISearchService;
import rem.search.service.SearchServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/main/qna/view.do")
public class MainQnaView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///로그인 세션정보 파악
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			return;
		}
		
		///문의글 번호로 서비스에서 VO를 가져옴
		int mem_no = loginInfo.getMem_no();
		int qna_no = Integer.parseInt(request.getParameter("qnaNo"));
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□MAIN_QNA_VIEW => qna_no: " +qna_no);
		
		
		Map<String, Integer> map = new HashMap<>();
		map.put("mem_no", mem_no);
		map.put("qna_no", qna_no);
		
		/*	답글이 달렸을 때, 달리지 않았을 때를 구분해야 하는데,
			그 조건 확인은 가져온 VO의 _com_status 컬럼값이다.(답변완료: 1, 처리중: 0)
			답 달렸을 때 두 번째 VO로 com값만 가져와서 원래VO에 할당시켰는데,
			이건 더 좋은 방법이 있을듯.
		 */
		ISearchService sservice = SearchServiceImpl.getInstance(SearchDaoImpl.getInstance());
		QnaSetVO vo = sservice.getQnaBoard(map);
		QnaSetVO vo2 = null;
		if(vo.getQna_com_status()==1) {
			vo2 = sservice.getQnaComments(qna_no);
			vo.setCmt_cont(vo2.getCmt_cont());
			vo.setCmt_at(vo2.getCmt_at());
		}
		
		/*	나중에 IMG도 입출력시킬 것 같아 대비용.
			급하면 건너뛰어도 됨.
			사진이 있을지도, 없을지도 모르니까,
			가져온 vo가 null이면 그냥 패쓰,
			vo가 존재하면 거기서 file_path 컬럼값만 attribute로 할당. 그거만 필요하니까.
		 */
		IFileService fservice = FileServiceImpl.getInstance();
		ImgFileVO imgVO = fservice.getQnaImg(qna_no);
		String qImgFile = "";
		if(imgVO!=null)
			 qImgFile = imgVO.getFile_path();
		
		request.setAttribute("qnaViewSet", vo);
		request.setAttribute("QimgFile", qImgFile);
		request.setAttribute("board", "qna");
		request.getRequestDispatcher("/WEB-INF/mainBoard/mainQnaView.jsp").forward(request, response);
		
	}



}
