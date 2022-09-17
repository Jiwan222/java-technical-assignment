package kata.supermarket;

import java.math.BigDecimal;

public interface Item {

    BigDecimal price();

    //can make this return an array of discount tags if we want to apply multiple
    DiscountTag getDiscountTag();
}
