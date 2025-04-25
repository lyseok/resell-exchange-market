package rem.transaction.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ibatis.sqlmap.engine.mapping.sql.Sql;

import rem.product.vo.ProductVO;
import rem.review.vo.ReviewImgVO;
import rem.review.vo.ReviewVO;
import rem.transaction.vo.ProdTransactionVO;
import rem.transaction.vo.ReviewTransactionVO;
import rem.transaction.vo.ShippingVO;
import rem.transaction.vo.TransactionImgVO;
import rem.transaction.vo.TransactionVO;
import utill.MyBatisUtil;

public class TransactionDaoImpl implements ITransactionDao {
	private static TransactionDaoImpl dao;
	
	private TransactionDaoImpl() {}
	
	
	public static TransactionDaoImpl getInstance() {
		if (dao == null) {
			dao = new TransactionDaoImpl();
		}
		return dao;
	}


	@Override
	public List<ProductVO> getProd(int memberId) {
		SqlSession session = null;
		List<ProductVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getProd", memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}


	@Override
	public int deleteProd(ProductVO prodVo) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.update("trans.deleteProd", prodVo);
			
		
			if (count > 0) {
				session.commit();
			} else {
				System.out.println("실패..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.commit();
			}
		}
		
		return count;
		
	}


	@Override
	public int updateNewing(ProductVO prodVo) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.update("trans.updateNewing", prodVo);
			
			
			if (count > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}
	
	
	
	@Override
	public List<ProdTransactionVO> getBuyProd(int memberId) {
		SqlSession session = null;
		List<ProdTransactionVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getBuyProd", memberId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public List<ProdTransactionVO> getSellProd(int memberId) {
		SqlSession session = null;
		List<ProdTransactionVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getSellProd", memberId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public int insertTrackInfo(ShippingVO shipVo) {
		SqlSession session = null;
		int count = 0;
		
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.insert("trans.insertTrackInfo", shipVo);
			if(count > 0 ) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		
		return count;
	}


	@Override
	public ReviewTransactionVO getSellProdReview(int txnNo) {
		SqlSession session = null;
		ReviewTransactionVO rvo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			rvo = session.selectOne("trans.getSellProdReview", txnNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return rvo;
	}


	@Override
	public ShippingVO getTrackInfo(int txnNo) {
		SqlSession session = null;
		ShippingVO svo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			svo = session.selectOne("trans.getTrackInfo", txnNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return svo;
	}


	@Override
	public int updateConfrimProd(Map<String, Object> param) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.update("trans.updateConfrimProd", param);
			
			
			if (count > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}


	@Override
	public int insertReview(ReviewImgVO rvo) {
		SqlSession session = null;
		int count = 0;
		
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.insert("trans.insertReview", rvo);
			if(count > 0 ) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		
		return count;
	}


	@Override
	public int insertTransaction(TransactionVO vo) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.insert("trans.insertTransaction", vo);
			if(count >0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return count;
	}



	@Override
	public List<TransactionImgVO> getProdImg(int memberId) {
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getProdImg", memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public List<TransactionImgVO> getBuyImg(int memberId) {
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getBuyImg", memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public List<TransactionImgVO> getSellImg(int memberId) {
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getSellImg", memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	
	
	
	@Override
	public List<TransactionImgVO> getStatusAllProd(TransactionImgVO tvo) {
		
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getStatusAllProd", tvo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}
	
	
	
	

	@Override
	public List<TransactionImgVO> getStatusBuyProd(TransactionImgVO tvo) {
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getStatusBuyProd", tvo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public List<TransactionImgVO> getStatusSellProd(TransactionImgVO tvo) {
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getStatusSellProd", tvo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public ReviewImgVO getReviewInfo(int txnNo) {
		SqlSession session = null;
		ReviewImgVO rvo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			rvo = session.selectOne("trans.getReviewInfo", txnNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return rvo;
	}


	@Override
	public TransactionImgVO getProdInfo(int txnNo) {
		SqlSession session = null;
		TransactionImgVO pvo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			pvo = session.selectOne("trans.getProdInfo", txnNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return pvo;
	}


	@Override
	public int updateProdStatus(Map<String, Object> param) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			count = session.update("trans.updateProdStatus", param);
			
			if (count > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}


	@Override
	public List<TransactionImgVO> getProdTrStatus(TransactionImgVO tvo2) {
		SqlSession session = null;
		List<TransactionImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("trans.getProdTrStatus", tvo2);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}


	@Override
	public int isThereReview(int txnNo) {
		SqlSession session = null;
		int rec = 0;
		try {
			session = MyBatisUtil.getSqlSession();
			rec = session.selectOne("trans.isThereReview", txnNo);
		}
		catch(Exception e) {e.printStackTrace();}
		finally {if(session!=null) session.close();}
		return rec;
	}


	



	
	
	


	

	

}


