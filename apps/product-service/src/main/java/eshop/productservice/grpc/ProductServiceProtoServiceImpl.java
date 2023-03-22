package eshop.productservice.grpc;

import eshop.api.exceptions.NotFoundException;
import eshop.productservice.service.ProductService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ProductServiceProtoServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {
    private final ProductService productService;

    @Override
    public void getProductById(GetProductByIdRequest request, StreamObserver<Product> responseObserver) {
        eshop.productservice.entities.Product product = productService.findProductById(
                UUID.fromString(request.getProductId())).orElseThrow(NotFoundException::new);

        Product response = Product.newBuilder()
                .setId(product.getId().toString())
                .setPrice(product.getPrice().doubleValue())
                .setDescription(product.getDescription())
                .setName(product.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProducts(GetProductsRequest request, StreamObserver<GetProductsResponse> responseObserver) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("id"));
        Page<eshop.productservice.entities.Product> pageResult = productService.getAllProducts(pageable);

        GetProductsResponse.Builder responseBuilder = GetProductsResponse.newBuilder();
        if (pageResult.hasContent()) {
            pageResult.getContent().forEach(product -> {
                Product productResponse = Product.newBuilder()
                        .setId(product.getId().toString())
                        .setPrice(product.getPrice().doubleValue())
                        .setDescription(product.getDescription())
                        .setName(product.getName())
                        .build();
                responseBuilder.addProducts(productResponse);
            });
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}