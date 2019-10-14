package ecommerce.controller;

import ecommerce.controller.exceptions.OrderNotFoundException;
import ecommerce.controller.exceptions.ProductNotFoundException;
import ecommerce.controller.exceptions.UserNotFoundException;
import ecommerce.domain.OrderEntity;
import ecommerce.domain.ProductEntity;
import ecommerce.domain.UserEntity;
import ecommerce.domain.dto.OrderDto;
import ecommerce.maper.OrderMapper;
import ecommerce.service.OrderDbService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/ecommerce/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDbService orderDbService;

    @Autowired
    private RabbitTemplate queueSender;

//    @RabbitListener(queues = "${queue.name}")
//    public void rabbitListener (OrderDto order){
//         System.out.println(order.toString());
//    }

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderDbService.getAllOrders());
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(@RequestParam("orderId") Long orderId) {
        return orderMapper.mapToOrderDto(orderDbService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @Transactional
    @PostMapping(value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        UserEntity user = orderDbService.findUser(orderDto.getUserName()).orElseThrow(UserNotFoundException::new);
        checkIfProductsExist(orderDto);
        List<ProductEntity> productEntityList = createProductList(orderDto);
        OrderEntity order = orderMapper.mapToOrder(orderDto, user, productEntityList);
        orderDbService.addOrder(order);
        queueSender.convertAndSend(orderMapper.mapToOrderDto(order));
    }

    @PutMapping(value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        UserEntity user = orderDbService.findUser(orderDto.getUserName()).orElseThrow(UserNotFoundException::new);
        checkIfProductsExist(orderDto);
        OrderEntity order = createOrder(orderDto.getId(), user, orderDto);
        orderDbService.saveOrderProduct(order.getProducts().get(0));
        return orderMapper.mapToOrderDto(order);
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam("orderId") Long orderId) {
        if (!orderDbService.getOrder(orderId).isPresent()) {
            throw new OrderNotFoundException();
        }
        orderDbService.deleteOrder(orderId);
    }

    private List<ProductEntity> createProductList(OrderDto orderDto) {
        List<ProductEntity> productEntityList = new ArrayList<>();
        orderDto.getProducts().stream()
                .forEach(product -> productEntityList.add(orderDbService.findProduct(product.getName()).get()));
        return productEntityList;
    }

    private void checkIfProductsExist (OrderDto orderDto) {
        orderDto.getProducts().stream()
                .forEach(product -> orderDbService.findProduct(product.getName()).orElseThrow(ProductNotFoundException::new));
    }

    private OrderEntity createOrder(Long id, UserEntity user, OrderDto orderDto){
        List<ProductEntity> productEntityList = createProductList(orderDto);
        OrderEntity order = orderDbService.findOrder(id).orElseThrow(OrderNotFoundException::new);
        order.getProducts().clear();
        order.setUser(user);
        user.getOrders().add(order);
        return orderMapper.mapToProducts(orderDto, order, productEntityList);
    }
}
