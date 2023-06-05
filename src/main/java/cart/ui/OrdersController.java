package cart.ui;

import cart.application.OrdersService;
import cart.domain.Member;
import cart.dto.CouponResponse;
import cart.dto.OrdersRequest;
import cart.dto.OrdersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<Void> takeOrders(Member member, @RequestBody @Valid OrdersRequest ordersRequest) {
        final long ordersId = ordersService.takeOrders(member, ordersRequest);
        return ResponseEntity.created(URI.create("/orders/" + ordersId)).build();
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> showOrdersByMember(Member member) {
        return ResponseEntity.ok().body(ordersService.findMembersAllOrders(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersResponse> showOrdersDetail(Member member, @PathVariable long id) {
        return ResponseEntity.ok().body(ordersService.findOrdersById(member, id));
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<CouponResponse> confirmOrders(Member member, @PathVariable long id) throws IllegalAccessException {
        return ResponseEntity.ok().body(ordersService.confirmOrders(member, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrders(@PathVariable long id) {
        ordersService.deleteOrders(id);
        return ResponseEntity.noContent().build();
    }
}
