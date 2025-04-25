package rem.file.service;

import java.util.List;

import rem.file.vo.ImgFileVO;

public interface IFileService {
	public int insertProfileImg(ImgFileVO imgVO);
	public ImgFileVO getProfileImg(int mem_no);
	public List<ImgFileVO> getProductfileImg(int prod_no);
	public ImgFileVO getProfileNull();
	public int countProfileImg(int mem_no);
	public int deleteProfileImg(int mem_no);
	public ImgFileVO getStoreTabProdImg(int prod_no);
	public int insertProductImg(ImgFileVO imgVO);
	public List<ImgFileVO> getStoreProdImg(int storeId);
	public List<ImgFileVO> getStoreReviewImg(int storeId);
	public List<ImgFileVO> getStoreSoldoutImg(int storeId);
    public List<ImgFileVO> getStoreWishImg(int storeId);
    public int deleteProductImg(int prod_no);
    public int insertQnaImg(ImgFileVO imgVO);
    public ImgFileVO getQnaImg(int qna_no);
    public int insertQnaCommentImg(ImgFileVO imgVO);
    public ImgFileVO getQnaCommentImg(int qna_no);
    
    public int insertReportImg(ImgFileVO imgVO);
}
