package cases.products.steps;

import commons.steps.IApiCommonSteps;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductsSteps implements IApiCommonSteps {

    private ValidatableResponse response;

    @Override
    public ProductApiValidationSteps callEndpoint(String endpoint) {
        response = RestAssured.given()
                .get(endpoint)
                .then();
        return new ProductApiValidationSteps(response);
    }
}