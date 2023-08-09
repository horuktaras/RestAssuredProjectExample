package cases.products.td;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AvailableProduct {
    ORANGE("orange"),
    APPLE("apple"),
    PASTA("pasta"),
    COLA("cola");

    private final String name;
}
