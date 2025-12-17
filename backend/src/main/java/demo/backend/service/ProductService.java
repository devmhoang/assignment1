package demo.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import demo.backend.model.Product;
import demo.backend.repository.ProductRepo;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

     private ProductRepo productRepo;

     public ProductService(ProductRepo theProductRepo){
          productRepo = theProductRepo;
     } 

     // CREATE A PRODUCT
     @Transactional
     public Product create(Product newProduct){
          return productRepo.save(newProduct);
     }


     // RETRIEVE PRODUCT LIST AND DETAILS 
     public List<Product> findAll() {
          return productRepo.findAll();
     }

     // DELETE A PRODUCT
     @Transactional
     public void delete(UUID theProductId){
          productRepo.deleteById(theProductId);
     }
}
