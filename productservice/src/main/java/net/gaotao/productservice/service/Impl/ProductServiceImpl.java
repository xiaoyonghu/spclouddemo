package net.gaotao.productservice.service.Impl;

import net.gaotao.productservice.Dao.ProductDao;
import net.gaotao.productservice.domain.Product;
import net.gaotao.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

  private static final Map<Integer,Product> daomap=new HashMap<>();
  private static final Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);

  @Autowired
  private ProductDao productDao;



    //  static {
//      Product p1=new Product(1,"手机",108,3434);
//      Product p2=new Product(2,"冰箱",108,3434);
//      Product p3=new Product(3,"电脑",108,3434);
//      Product p4=new Product(4,"椅子",108,3434);
//      Product p5=new Product(5,"吹风机",108,3434);
//      Product p6=new Product(6,"汽车",108,3434);
//      Product p7=new Product(7,"插板",108,3434);
//
//      daomap.put(p1.getId(),p1);
//      daomap.put(p2.getId(),p2);
//      daomap.put(p3.getId(),p3);
//      daomap.put(p4.getId(),p4);
//      daomap.put(p5.getId(),p5);
//      daomap.put(p6.getId(),p6);
//      daomap.put(p7.getId(),p7);
//
//  }




    @Override
    public List<Product> listProduct() {
//        Collection<Product> collection=daomap.values();
//        List<Product> list=new ArrayList<>(collection);
         // List<Product> list=productDao.listAll();
          List<Product> list= productDao.listAll();
          return list;
    }

    @Override
    public Product findById(int id) {
      //logger.info("product service findById");
      //return daomap.get(id);
        Product product=productDao.findById(id);
        return product;
    }
}
