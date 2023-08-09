package cases.products.td;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UnavailableProduct {
    MANGO("mango"),
    CAR("car");

    private final String name;
}
