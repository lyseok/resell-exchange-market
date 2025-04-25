package rem.admin.board.qna.service;

import java.util.List;
import java.util.Map;

import rem.admin.board.qna.vo.QnaBoardVO;

public interface IQnaService {
	public List<QnaBoardVO> selectQnaList();
	
	public QnaBoardVO seletQnaView(int qna_no);
	
	public int deleteQna(int qna_no);
	
	public List<QnaBoardVO> searchQna(Map<String, String> map);
	
	public int insertQna(QnaBoardVO vo);
	public int getQnaNoImmediately(int mem_no);
}
