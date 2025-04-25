package rem.transaction.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rem.file.service.FileServiceImpl;
import rem.file.service.IFileService;
import rem.file.vo.ImgFileVO;
import rem.login.vo.MemberVO;
import rem.product.vo.ProductVO;
import rem.transaction.service.ITransactionService;
import rem.transaction.service.TransactionServiceImpl;
import rem.transaction.vo.ProdTransactionVO;
import rem.transaction.vo.TransactionImgVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/getTransactionData.do")
public class GetTransactionData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		int status = Integer.parseInt(request.getParameter("status"));
				
		
        if (category == null) {
           System.out.println("category값이 비어있습니다");
        }

		
		
		MemberVO loginInfo = (MemberVO) request.getSession().getAttribute("loginInfo");
	    if (loginInfo == null) {
	        response.sendRedirect("/loginPage.jsp");
	        return;
	    }
	   
	    int memberNo = loginInfo.getMem_no(); 
		
	    System.out.println("memberNo:  " + memberNo);
	    
	    ITransactionService service = TransactionServiceImpl.getInstance();
	 
	    Gson gson = new Gson();
	    String result = null;
	    
	    List<TransactionImgVO> imgList = null;
	    
	    TransactionImgVO tvo = new TransactionImgVO();
	    tvo.setMem_no(memberNo);
	    tvo.setTxn_status(status);
	    
	    
	 
	    
	    
	
	    if(status == 4) {
	    	 if (category.equals("상품관리")) {
	    		 imgList = service.getProdImg(memberNo);
	    		 result = gson.toJson(imgList); 
	 		}
	 	    else if (category.equals("구매관리")) {

	 	    	 imgList = service.getBuyImg(memberNo);
	 	    	 result = gson.toJson(imgList); 
	 		}
	 	    else if (category.equals("판매관리")) {
	 	    	
	 	    	imgList = service.getSellImg(memberNo);
	 	    	 result = gson.toJson(imgList); 
	 	    }
	    }
	    
	    else {
	    	if (category.equals("상품관리")) {
	 			imgList = service.getStatusAllProd(tvo);
	 			 System.out.println("imgList: " + imgList);
	 			 result = gson.toJson(imgList); 
	 			 
	 		}
	 	    else if (category.equals("구매관리")) {
	 	    	imgList = service.getStatusBuyProd(tvo);
	 			 System.out.println("imgList: " + imgList);
	 			 result = gson.toJson(imgList); 
	 		}
	 	    else if (category.equals("판매관리")) {
	 	    	 imgList = service.getStatusSellProd(tvo);
	 			 System.out.println("imgList: " + imgList);
	 			 result = gson.toJson(imgList); 
	 	    }
	    	
	    }
	    
	    
	  
	    System.out.println("result: " + result);
	   
	    
	   
	    
	    response.setContentType("application/json;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(result); 
	    out.flush();

	    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
