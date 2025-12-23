package demo.backend;

import demo.backend.model.Product;
import demo.backend.repository.ProductRepo;
import demo.backend.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setId(77);
        testProduct.setProductName("Test Product");
        testProduct.setProductPrice(10000L);
        testProduct.setProductStock(10);
    }

    @Test
    void create_ShouldSaveAndReturnProduct() {
        when(productRepo.save(any(Product.class))).thenReturn(testProduct);
        Product result = productService.create(testProduct);
        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepo, times(1)).save(testProduct);
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        when(productRepo.findAll()).thenReturn(Arrays.asList(testProduct));
        List<Product> result = productService.findAll();
        assertEquals(1, result.size());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void findProductById_ShouldThrowException_WhenNotFound() {
        when(productRepo.findById(999)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productService.findProductById(999));
    }

    @Test
    void updateStock_ShouldDeleteProduct_WhenStockBecomesZero() {
        testProduct.setProductStock(5);
        productService.updateStock(testProduct, 5);
        verify(productRepo, times(1)).deleteById(77);
        verify(productRepo, never()).save(any());
    }

    @Test
    void delete_ShouldDeleteProduct() {
        productService.delete(77);
        verify(productRepo, times(1)).deleteById(77);
    }
}
