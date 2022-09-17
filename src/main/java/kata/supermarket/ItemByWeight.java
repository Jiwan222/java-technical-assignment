package kata.supermarket;

import lombok.AllArgsConstructor;
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
    private final Enum<DiscountTag> discountTags;


    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
        this.discountTags = NO_DISCOUNT;
    }

    public static ItemByWeight ItemWithDiscount(final WeighedProduct product, final DiscountTag discountTag) {
        return ItemByWeight.builder()
                .product(product)
                .discountTags(discountTag)
                .build();
    }

    @Override
    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
    }




}
