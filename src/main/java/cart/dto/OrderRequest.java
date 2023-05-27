package cart.dto;

import java.util.List;

public class OrderRequest {
    private List<Long> cartProductIds;
    private int originalPrice;
    private int discountPrice;
    private Long couponId;

    private OrderRequest(){

    }
    public OrderRequest(List<Long> cartProductIds, int originalPrice, int discountPrice, Long couponId) {
        this.cartProductIds = cartProductIds;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.couponId = couponId;
    }

    public List<Long> getCartProductIds() {
        return cartProductIds;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public Long getCouponId() {
        return couponId;
    }
}
