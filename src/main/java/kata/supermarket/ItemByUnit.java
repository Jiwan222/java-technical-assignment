package kata.supermarket;

import java.math.BigDecimal;
import static kata.supermarket.DiscountTag.NODISCOUNT;

public class ItemByUnit implements Item {

    private final Product product;
    private final Enum<DiscountTag> discountTags;


    public ItemByUnit(Product product) {
        this.product = product;
        this.discountTags = NODISCOUNT;
    }

    public ItemByUnit(Product product, DiscountTag discountTag) {
        this.product = product;
        this.discountTags = discountTag;
    }

    public static ItemByUnit ItemByUnitWithDiscount(final Product product, final DiscountTag discountTag) {
        return new ItemByUnit(product, discountTag);
    }

    @Override
    public BigDecimal price() {
        return product.pricePerUnit();
    }
}
