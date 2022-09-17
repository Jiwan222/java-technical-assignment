package kata.supermarket;

import java.math.BigDecimal;

import static kata.supermarket.DiscountTag.HALF_PRICE_KILO;
import static kata.supermarket.ItemByUnit.ItemByUnitWithDiscount;

public class Product {

    private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public Item oneOfWithHalfPriceKilo() {
        return ItemByUnitWithDiscount(this, HALF_PRICE_KILO);
    }

}
