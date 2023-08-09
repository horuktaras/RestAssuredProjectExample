package cases.products.td;

import commons.testdata.AbstractDataProvider;
import org.testng.annotations.DataProvider;

public class ProductsDataProvider extends AbstractDataProvider {

    @DataProvider(name = "Available products")
    public static Object[][] availableProducts() {
        AvailableProduct[] products = AvailableProduct.values();
        return getObjects(products);
    }

    @DataProvider(name = "Unavailable products")
    public static Object[][] unavailableProducts() {
        UnavailableProduct[] products = UnavailableProduct.values();
        return getObjects(products);
    }
}