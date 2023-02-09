package eshop.productservice.mappers;

import eshop.productservice.entities.Product;
import eshop.productservice.model.CreateProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product createProductDTOToProduct(CreateProductDTO createProductDTO);
}