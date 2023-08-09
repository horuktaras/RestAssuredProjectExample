package cases.products.dto;

import commons.rest.FailedResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductsSuccessResponse extends FailedResponse {
    private List<ProductsSuccessResponseItem> productsSuccessResponse;
}