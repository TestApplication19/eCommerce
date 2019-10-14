package ecommerce.maper;

import ecommerce.domain.CartEntity;
import ecommerce.domain.CartProduct;
import ecommerce.domain.UserEntity;
import ecommerce.domain.dto.CartDto;
import ecommerce.domain.dto.CartProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {
    public CartEntity mapToCart(CartDto cartDto, UserEntity user) {
        CartEntity cart = createCart(cartDto);
        cart.setUser(user);
        return cart;
    }

    private CartEntity createCart(CartDto cartDto) {
        return (cartDto.getId() == null) ? new CartEntity() : new CartEntity(cartDto.getId());
    }

    public CartDto mapToCartDto(CartEntity cart) {
        return new CartDto(cart.getId(), cart.getUser().getUserName(), mapToProductDtoList(cart.getProducts()));
    }

    private List<CartProductDto> mapToProductDtoList(List<CartProduct> orderProductList){
        List<CartProductDto> productsInCart = new ArrayList<>();
        for (CartProduct orderProduct : orderProductList) {
            productsInCart.add(new CartProductDto(orderProduct.getId(), orderProduct.getProductInCart().getName(), orderProduct.getProductInCart().getDescription(), orderProduct.getProductInCart().getPrice(), orderProduct.getQuantity()));
        }
        return productsInCart;
    }
}
