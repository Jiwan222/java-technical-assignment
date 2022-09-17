package kata.supermarket;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Builder
public class DiscountProcessor {

    private final List<Item> items;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PRIVATE)
    private BigDecimal discounts;

    private static final BigDecimal TWO = new BigDecimal("2");

    public BigDecimal getTotalDiscounts() {
        items.forEach(this::calculateDiscounts);
        return discounts;
    }

    private void calculateDiscounts(Item item) {
        if (item.getDiscountTag() == DiscountTag.HALF_PRICE_KILO) {
            this.setDiscounts(this.getDiscounts().add(item.price().divide(TWO, RoundingMode.DOWN)));
        }
    }


}
