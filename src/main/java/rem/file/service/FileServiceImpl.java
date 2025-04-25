package rem.file.service;

import java.util.List;

import rem.file.dao.FileDaoImpl;
import rem.file.dao.IFileDao;
import rem.file.vo.ImgFileVO;

public class FileServiceImpl implements IFileService {
	private IFileDao dao;
	private static FileServiceImpl service;
	private FileServiceImpl() {
		dao = FileDaoImpl.getInstance();
	}
	public static FileServiceImpl getInstance() {
		if(service==null) service = new FileServiceImpl();
		return service;
	}
	@Override
	public int insertProfileImg(ImgFileVO imgVO) {
		return dao.insertProfileImg(imgVO);
	}
	@Override
	public ImgFileVO getProfileImg(int mem_no) {
		return dao.getProfileImg(mem_no);
	}
	@Override
	public ImgFileVO getProfileNull() {
		return dao.getProfileNull();
	}
	@Override
	public int countProfileImg(int mem_no) {
		return dao.countProfileImg(mem_no);
	}
	@Override
	public int deleteProfileImg(int mem_no) {
		return dao.deleteProfileImg(mem_no);
	}
	@Override
	public ImgFileVO getStoreTabProdImg(int prod_no) {
		return dao.getStoreTabProdImg(prod_no);
	}
	@Override
	public int insertProductImg(ImgFileVO imgVO) {
		// TODO Auto-generated method stub
		return dao.insertProductImg(imgVO);
	}
	@Override
	public List<ImgFileVO> getStoreProdImg(int storeId) {
		return dao.getStoreProdImg(storeId);
	}
	@Override
	public List<ImgFileVO> getStoreReviewImg(int storeId) {
		return dao.getStoreReviewImg(storeId);
	}
	@Override
	public List<ImgFileVO> getStoreSoldoutImg(int storeId) {
		return dao.getStoreSoldoutImg(storeId);
	}
	@Override
	public List<ImgFileVO> getStoreWishImg(int storeId) {
		return dao.getStoreWishImg(storeId);
	}
	@Override
	public List<ImgFileVO> getProductfileImg(int prod_no) {
		// TODO Auto-generated method stub
		return dao.getProductfileImg(prod_no);
	}
	@Override
	public int deleteProductImg(int prod_no) {
		// TODO Auto-generated method stub
		return dao.deleteProductImg(prod_no);
	}
	@Override
	public int insertQnaImg(ImgFileVO imgVO) {
		return dao.insertQnaImg(imgVO);
	}
	@Override
	public ImgFileVO getQnaImg(int qna_no) {
		return dao.getQnaImg(qna_no);
	}
	@Override
	public int insertQnaCommentImg(ImgFileVO imgVO) {
		return dao.insertQnaCommentImg(imgVO);
	}
	@Override
	public ImgFileVO getQnaCommentImg(int qna_no) {
		return dao.getQnaCommentImg(qna_no);
	}
	@Override
	public int insertReportImg(ImgFileVO imgVO) {
		return dao.insertReportImg(imgVO);
	}
}
