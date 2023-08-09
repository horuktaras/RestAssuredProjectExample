package cases.products;

import cases.BaseTest;
import cases.products.steps.ProductsSteps;
import cases.products.td.AvailableProduct;
import cases.products.td.ProductsDataProvider;
import cases.products.td.UnavailableProduct;
import constants.files.Files;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    private ProductsSteps apiSteps;

    @BeforeClass
    private void setupSteps() {
        apiSteps = new ProductsSteps();
    }

    @Test(testName = "Check available products",
            dataProvider = "Available products",
            dataProviderClass = ProductsDataProvider.class)
    public void testAvailableProducts(AvailableProduct product) {
        apiSteps.callEndpoint(product.getName())
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseSchema(Files.JsonSchemas.PRODUCTS)
                .verifyPricesAreOk()
                .verifyResponseBodyContains(product.getName());
    }

    @Test(testName = "Check unavailable products",
            dataProvider = "Unavailable products",
            dataProviderClass = ProductsDataProvider.class)
    public void testUnavailableProducts(UnavailableProduct product) {
        apiSteps.callEndpoint(product.getName())
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyResponseSchema(Files.JsonSchemas.ERROR);
    }
}