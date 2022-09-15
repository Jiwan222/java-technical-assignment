package kata.supermarket.discounts;

import kata.supermarket.Basket;
import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public class DiscountProcessor {

    private final Basket basket;

    public DiscountProcessor(Basket basket) {
        this.basket = basket;
    }

    public static BigDecimal applyDiscounts(List<Item> items) {
        return BigDecimal.ONE;
    }


}
