package rem.product.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import rem.product.vo.CateMainVO;
import rem.product.vo.CateNameVO;
import rem.product.vo.CateSubVO;
import rem.product.vo.NewViewVO;
import rem.product.vo.ProdImgVO;

import rem.product.vo.ProductVO;
import rem.product.vo.ViewCountVO;
import utill.MyBatisUtil;

public class ProductDaoImpl implements IProductDao {
   
   private static ProductDaoImpl dao;
   
   private ProductDaoImpl() {};
   
   public static ProductDaoImpl getInstance() {
      if(dao==null) dao = new ProductDaoImpl();
      return dao;
   }

   
   @Override
   public int insertProduct(ProductVO vo) {
      SqlSession session = null;
      int rec = 0;
      try {
         session = MyBatisUtil.getSqlSession();
         rec = session.insert("product.insertProduct", vo);
         if(rec>0) session.commit();
      }
      catch(Exception e) {e.printStackTrace();}
      finally {if(session!=null) session.close();}
      return rec;
   }

   

   @Override
   public List<CateMainVO> getCateMain() {
      SqlSession session = null;
      List<CateMainVO> list = null;
      
      try {
         session = MyBatisUtil.getSqlSession();
         
         list = session.selectList("product.getCateMain");
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if(session!=null) session.close();
      }
      return list;
   }

   @Override
   public List<CateSubVO> getCateSub(int cate_main_id) {
      SqlSession session = null;
      List<CateSubVO> list = null;
      
      try {
         session = MyBatisUtil.getSqlSession();
         
         list = session.selectList("product.getCateSub", cate_main_id);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if(session!=null) session.close();
      }
      return list;
   }

	@Override
	public ProductVO getProductDetail(Map<String, Object> param) {
		SqlSession session = null;
		ProductVO pvo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			pvo = session.selectOne("product.getProductDetail", param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return pvo;
	}
	
	@Override
	public int getCountAllReview(int mem_no) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.selectOne("product.getCountAllReview", mem_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return cnt;
	}
	
	@Override
	public CateNameVO getCateName(int prod_no) {
		SqlSession session = null;
		CateNameVO nvo = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			nvo = session.selectOne("product.getCateName", prod_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return nvo;
	}
	
	@Override
	public int updateProductView(int prod_no) {
		 SqlSession session = null;
	     int rec = 0;
	     try {
	        session = MyBatisUtil.getSqlSession();
	        rec = session.update("product.updateProductView", prod_no);
	        if(rec>0) session.commit();
	     }
	     catch(Exception e) {e.printStackTrace();}
	     finally {if(session!=null) session.close();}
	     return rec;
	}
	
	@Override
	public int insertViewCount(ViewCountVO vo) {
		 SqlSession session = null;
	     int rec = 0;
	     try {
	        session = MyBatisUtil.getSqlSession();
	        rec = session.insert("product.insertViewCount", vo);
	        if(rec>0) session.commit();
	     }
	     catch(Exception e) {e.printStackTrace();}
	     finally {if(session!=null) session.close();}
	     return rec;
	}
	
	@Override
	public List<ProdImgVO> selectAllMainPageProd() {
		SqlSession session = null;
		List<ProdImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("product.selectAllMainPageProd");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}
	
	@Override
	public int updateProdStatus(int prod_no) {
		SqlSession session = null;
	    int rec = 0;
	    try {
	       session = MyBatisUtil.getSqlSession();
	       rec = session.update("product.updateProdStatus", prod_no);
	       if(rec>0) session.commit();
	    }
	    catch(Exception e) {e.printStackTrace();}
	    finally {if(session!=null) session.close();}
	    return rec;
	}
	
	@Override
	public int updateProduct(ProductVO vo) {
		SqlSession session = null;
		int rec = 0;
		try {
		       session = MyBatisUtil.getSqlSession();
		       rec = session.update("product.updateProduct", vo);
		       if(rec>0) session.commit();
		    }
		    catch(Exception e) {e.printStackTrace();}
		    finally {if(session!=null) session.close();}
		    return rec;
	}
	
	@Override
	public List<ProdImgVO> selectProdRecommend(ProductVO vo) {
		SqlSession session = null;
		List<ProdImgVO> list = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("product.selectProdRecommend", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}
	
	@Override
	public List<NewViewVO> selectNewView(int no) {
		SqlSession session = null;
		List<NewViewVO> list = null;
		try {
			session = MyBatisUtil.getSqlSession();
			list = session.selectList("product.selectNewViewList", no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}

}
