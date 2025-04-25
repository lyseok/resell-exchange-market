package rem.product.service;


import java.util.List;
import java.util.Map;

import rem.product.dao.IProductDao;
import rem.product.dao.ProductDaoImpl;
import rem.product.vo.CateMainVO;
import rem.product.vo.CateNameVO;
import rem.product.vo.CateSubVO;
import rem.product.vo.NewViewVO;
import rem.product.vo.ProdImgVO;

import java.util.List;

import rem.product.dao.IProductDao;
import rem.product.dao.ProductDaoImpl;

import rem.product.vo.ProductVO;
import rem.product.vo.ViewCountVO;

public class ProductServiceImpl implements IProductService {
   private IProductDao dao;
   
   private static ProductServiceImpl service;
   
   private ProductServiceImpl() {
      dao = ProductDaoImpl.getInstance();
   }
   
   public static ProductServiceImpl getInstance() {
      if(service==null) service = new ProductServiceImpl();
      return service;
   }
   
   
   @Override
   public int insertProduct(ProductVO vo) {
      return dao.insertProduct(vo);
   }


   @Override
   public List<CateMainVO> getCateMain() {
      // TODO Auto-generated method stub
      return dao.getCateMain();
   }

   @Override
   public List<CateSubVO> getCateSub(int cate_main_id) {
      // TODO Auto-generated method stub
      return dao.getCateSub(cate_main_id);
   }

@Override
public ProductVO getProductDetail(Map<String, Object> param) {
	// TODO Auto-generated method stub
	return dao.getProductDetail(param);
}

@Override
public int getCountAllReview(int mem_no) {
	// TODO Auto-generated method stub
	return dao.getCountAllReview(mem_no);
}

@Override
public CateNameVO getCateName(int prod_no) {
	// TODO Auto-generated method stub
	return dao.getCateName(prod_no);
}

@Override
public int updateProductView(int prod_no) {
	// TODO Auto-generated method stub
	return dao.updateProductView(prod_no);
}

@Override
public int insertViewCount(ViewCountVO vo) {
	// TODO Auto-generated method stub
	return dao.insertViewCount(vo);
}

@Override
public List<ProdImgVO> selectAllMainPageProd() {
	// TODO Auto-generated method stub
	return dao.selectAllMainPageProd();
}

@Override
public int updateProdStatus(int prod_no) {
	// TODO Auto-generated method stub
	return dao.updateProdStatus(prod_no);
}

@Override
public int updateProduct(ProductVO vo) {
	// TODO Auto-generated method stub
	return dao.updateProduct(vo);
}

@Override
public List<ProdImgVO> selectProdRecommend(ProductVO vo) {
	// TODO Auto-generated method stub
	return dao.selectProdRecommend(vo);
}

@Override
public List<NewViewVO> selectNewView(int no) {
	return dao.selectNewView(no);
}

}
