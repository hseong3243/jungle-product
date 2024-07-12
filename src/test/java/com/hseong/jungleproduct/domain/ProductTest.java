package com.hseong.jungleproduct.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductTest {

    @Nested
    @DisplayName("initializeInventory 호출 시")
    class InitializeInventory {
        @ParameterizedTest
        @CsvSource({
                "-1,5",
                "5,-1",
                "-1,-1"
        })
        void 재고는_음수로_초기화_할_수_없다(int displayAmount, int storageAmount) {
            //given
            Product product = new Product(2134L, "미니얼룩말", 24000);

            //when
            Exception exception = catchException(() -> product.initializeInventory(displayAmount, storageAmount));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }
    }
}