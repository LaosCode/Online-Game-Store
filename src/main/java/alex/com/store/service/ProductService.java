package alex.com.store.service;

import alex.com.store.constants.ErrorMessage;
import alex.com.store.constants.SuccessMessage;
import alex.com.store.dto.request.ProductRequest;
import alex.com.store.dto.response.MessageResponse;
import alex.com.store.model.Product;
import alex.com.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;

    private final ModelMapper modelMapper;

    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_NOT_FOUND));
    }

    public List<Product> getBestSellingProducts() {
        return productRepository.getProductsByCategory("best_seller");
    }

    public List<Product> getOnSalesProducts() {
        return productRepository.getProductsByCategory("on_sales");
    }

    public List<Product> getNewArrivals() {
        return productRepository.getProductsByCategory("new_arrivals");
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public MessageResponse addProduct(ProductRequest productRequest) {
        MultipartFile file = productRequest.getMultipartFile();
        Product product = modelMapper.map(productRequest, Product.class);
        if (file.getSize() == 0) {
            return new MessageResponse("imageError", ErrorMessage.NO_IMAGE_ATTACHED);
        }
        String imageName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        imageService.saveFile(imageName, file);
        product.setImage(imageName);
        productRepository.save(product);
        return new MessageResponse("success", SuccessMessage.PRODUCT_CREATED);
    }

    @Transactional
    public MessageResponse updateProduct(Long id, ProductRequest productRequest) {
        MultipartFile file = productRequest.getMultipartFile();
        Product product = modelMapper.map(productRequest, Product.class);
        Product productToBeUpdated = productRepository.findById(Math.toIntExact(id)).orElse(null);
        if (file == null) {
            product.setImage(productToBeUpdated.getImage());
        }
        if (file != null) {
            String imageName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            imageService.saveFile(imageName, file);
            product.setImage(imageName);
        }
        product.setId(productToBeUpdated.getId());
        productRepository.save(product);
        return new MessageResponse("success", SuccessMessage.PRODUCT_UPDATED);
    }

    @Transactional
    public void delete(long id) {
        productRepository.deleteById((int) id);
    }

    @Transactional
    public void deleteImage(Long id) {
        Product productToBeUpdated = productRepository.findById(Math.toIntExact(id)).get();
        if (!productToBeUpdated.getImage().isEmpty()) {
            productRepository.deleteById(Math.toIntExact(id));
            imageService.deleteImage(productToBeUpdated.getImage());
        }
    }

}
