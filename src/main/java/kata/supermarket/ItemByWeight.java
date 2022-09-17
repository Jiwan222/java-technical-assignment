package kata.supermarket;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static kata.supermarket.DiscountTag.NO_DISCOUNT;

@Builder
@RequiredArgsConstructor
public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;
    private final DiscountTag discountTag;


    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discountTag = NO_DISCOUNT;
    }

    @Override
    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }

    @Override
    public DiscountTag getDiscountTag() {
        return discountTag;
    }


}
