package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static kata.supermarket.BasketTest.kiloOfHalfPriceItem;
import static org.junit.jupiter.api.Assertions.*;

class DiscountProcessorTest {

    @Test
    void canProcessHalfPriceItemDiscount() {
        // Given
        List<Item> items = Collections.singletonList(kiloOfHalfPriceItem(tenPoundPerKiloVeg()));

        DiscountProcessor discountProcessor = DiscountProcessor.builder()
                .items(items)
                .discounts(BigDecimal.ZERO)
                .build();

        // When // Then
        assertEquals(new BigDecimal("5.00"), discountProcessor.getTotalDiscounts());
    }

    //using some shared methods here for object creation, ideally with more time I'd put them all in one shared utility class
    static WeighedProduct tenPoundPerKiloVeg() {
        return new WeighedProduct(new BigDecimal("10"));
    }

}