package cases.products.steps;

import cases.products.dto.ProductsSuccessResponseItem;
import commons.rest.ResponseHandler;
import commons.steps.IApiValidationSteps;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.hamcrest.Matchers;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ProductApiValidationSteps implements IApiValidationSteps {

    private ValidatableResponse response;

    public ProductApiValidationSteps(ValidatableResponse response) {
        this.response = response;
    }

    @Override
    public ProductApiValidationSteps verifyStatusCode(int statusCode) {
        response.statusCode(Matchers.equalTo(statusCode));
        return this;
    }

    @Override
    public ProductApiValidationSteps verifyResponseSchema(File schemaFile) {
        response.body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
        return this;
    }

    public ProductApiValidationSteps verifyResponseBodyContains(String expectedValue) {
        response.body(Matchers.containsStringIgnoringCase(expectedValue));
        return this;
    }

    public ProductApiValidationSteps verifyPricesAreOk() {
        List<ProductsSuccessResponseItem> products = getProducts();
        List<Double> prices = products.stream().map(ProductsSuccessResponseItem::getPrice).collect(Collectors.toList());
        SoftAssert sa = new SoftAssert();
        prices.forEach(p -> sa.assertTrue(p > NumberUtils.DOUBLE_ZERO));
        sa.assertAll();
        return this;
    }

    private List<ProductsSuccessResponseItem> getProducts() {
        return new ResponseHandler()
                .getResponseAsList(response.extract().response(), ProductsSuccessResponseItem.class);
    }
}
