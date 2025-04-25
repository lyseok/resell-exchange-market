package rem.product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import rem.file.service.FileServiceImpl;
import rem.file.service.IFileService;
import rem.file.vo.ImgFileVO;
import rem.login.vo.MemberVO;
import rem.product.service.IProductService;
import rem.product.service.ProductServiceImpl;
import rem.product.vo.ProductVO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;


@WebServlet("/product/insertProduct.do")
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "remImg";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/product/insertProduct.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
		int memId = loginInfo.getMem_no();
		List<ImgFileVO> list = new ArrayList<ImgFileVO>();
		
		String uploadPath = "d:" + File.separator + UPLOAD_DIR;
		System.out.println(uploadPath);
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		if(loginInfo==null) {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
		}else {
			int cate_sub_id = Integer.parseInt(request.getParameter("cate_sub_id"));
			String prod_name = request.getParameter("prod_name");
			int prod_condition = Integer.parseInt(request.getParameter("prod_condition"));
			String prod_content = request.getParameter("prod_content");
			int prod_price = Integer.parseInt(request.getParameter("prod_price"));
			int price_offer = Integer.parseInt(request.getParameter("price_offer"));
			int prod_approach = Integer.parseInt(request.getParameter("prod_tr_approach"));
			
			ProductVO pvo = new ProductVO();
			pvo.setMem_no(memId);
			pvo.setCate_sub_id(cate_sub_id);
			pvo.setProd_name(prod_name);
			pvo.setProd_condition(prod_condition);
			pvo.setProd_content(prod_content);
			pvo.setProd_price(prod_price);
			pvo.setPrice_offer(price_offer);
			pvo.setProd_tr_approch(prod_approach);
			System.out.println(pvo.toString());
			
			IProductService service = ProductServiceImpl.getInstance();
			
			int cnt = service.insertProduct(pvo);
			int file_total = 0;
			
			int prod_no = pvo.getProd_no();
			
			if(cnt > 0) {
				for(Part imgPart : request.getParts()) {
					String fileName = extractFileName(imgPart);
					
					if(fileName.equals("")) {
						continue;
					}
					
					String checkImg = request.getParameter("checkImg");
					if(checkImg.equals("NEW-IMG")) {
						ImgFileVO imgVO = new ImgFileVO();
						file_total++;
						try {
							System.out.println(imgPart.getSubmittedFileName());
		
							String imgUUIDName = UUID.randomUUID().toString() + "_" + fileName;
							String savingFilePath = uploadPath + File.separator + imgUUIDName;
							String scriptFilePath = "/" + uploadPath.substring(3) + File.separator + imgUUIDName;
							String imgExtension = "";
							int index = fileName.lastIndexOf(".");
							if(index > 0) {
								imgExtension = fileName.substring(index + 1);
							}
							
							imgVO.setFile_org_name(fileName);
							imgVO.setFile_save_name(imgUUIDName);
							imgVO.setFile_path(scriptFilePath);
							imgVO.setFile_size((int)Math.ceil(imgPart.getSize() / 1024.0 / 1024.0));
							imgVO.setFile_type(imgExtension);
							imgVO.setFile_no(prod_no);
							imgVO.setFile_total(file_total);
							
							imgPart.write(savingFilePath);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						list.add(imgVO);
					}
				}
				IFileService fservice = FileServiceImpl.getInstance();
				
				for(ImgFileVO fvo : list) {
					fservice.insertProductImg(fvo);
				}
				System.out.println("등록 성공");
				Gson gson = new Gson();
				String result = gson.toJson(prod_no);
				
				PrintWriter out = response.getWriter();
				out.println(result);
				out.flush();
			}
			
		}
	}
	
	private String extractFileName(Part part) {
		String fileName = "";
		
		String dispositionValue = part.getHeader("content-disposition");
		String[] items = dispositionValue.split(";");
		
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length()-1);
			}
		}
		
		return fileName;
	}

}
