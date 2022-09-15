package kata.supermarket;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static kata.supermarket.DiscountTag.NODISCOUNT;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;
    private final Enum<DiscountTag> discountTags;


    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discountTags = NODISCOUNT;
    }

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos, final DiscountTag discountTag) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discountTags = discountTag;
    }

    public static ItemByWeight ItemByWeightWithDiscount(final WeighedProduct product,
                                                        BigDecimal weightInKilos,
                                                        final DiscountTag discountTag) {
        return new ItemByWeight(product, weightInKilos, discountTag);
    }

    @Override
    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }




}
