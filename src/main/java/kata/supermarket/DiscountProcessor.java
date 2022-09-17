package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DiscountProcessor {

    private static final BigDecimal TWO = new BigDecimal("2");
    private static BigDecimal DISCOUNTS = BigDecimal.ZERO;

    public static BigDecimal getTotalBasketDiscounts(List<Item> items) {
        items.forEach(DiscountProcessor::calculateDiscount);
        return DISCOUNTS;
    }

    private static void calculateDiscount(Item item) {
        if (item.getDiscountTag() == DiscountTag.HALF_PRICE_KILO) {
            DISCOUNTS = DISCOUNTS.add(item.price().divide(TWO, RoundingMode.DOWN));
        }
    }

}
