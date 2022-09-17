package kata.supermarket.discounts;

import kata.supermarket.DiscountTag;
import kata.supermarket.Item;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@UtilityClass
public class DiscountProcessor {

    /**
     * I'd ordinarily not just use static methods like this and would instead have this Discount Processor
     *  own a basket, using springs dependency injection so we can make these more loosely coupled.
     *  But due to time constraints and an explicit request to not add a dependency injection framework, I've just done
     *  it this way using a utility class, which I would usually make use of for testing classes only.
     *  The BasketTest would be a good example of this, I would move all those static object creations into a
     *  utility class so they could be shared with any integration tests etc I would want to add.
     */

    private static final BigDecimal TWO = new BigDecimal("2");
    private static BigDecimal DISCOUNTS = BigDecimal.ZERO;

    //TODO get rid of these statics later if utility class works
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
