package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static kata.supermarket.DiscountTag.HALF_PRICE_KILO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    @DisplayName("basket provides its total value with discounts when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithDiscounts(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValueWithDiscounts() {
        return Stream.of(
                oneDiscountedItem(),
                multipleItemsWithAndWithoutDiscounts(),
                multipleDiscountedItems()
        );
    }

    private static Arguments oneDiscountedItem() {
        return Arguments.of("a half price kilo of veg", "1.50", Collections.singleton(kiloOfHalfPriceItem(threePoundPerKiloItem())));
    }

    private static Arguments multipleItemsWithAndWithoutDiscounts() {
        return Arguments.of("multiple weighed items with and without discounts", "2.10",
                Arrays.asList(kiloOfHalfPriceItem(threePoundPerKiloItem()), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleDiscountedItems() {
        return Arguments.of("multiple weighed items with discounts", "11.50",
                Arrays.asList(kiloOfHalfPriceItem(threePoundPerKiloItem()), kiloOfHalfPriceItem(twentyPoundPerKiloItem()))
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct threePoundPerKiloItem() {
        return new WeighedProduct(new BigDecimal("3"));
    }

    private static WeighedProduct twentyPoundPerKiloItem() {
        return new WeighedProduct(new BigDecimal("20"));
    }

    static Item kiloOfHalfPriceItem(WeighedProduct product) {
        return ItemByWeight.builder()
                .product(product)
                .weightInKilos(new BigDecimal("1"))
                .discountTag(HALF_PRICE_KILO)
                .build();
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}