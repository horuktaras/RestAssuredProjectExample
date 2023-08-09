package cases.products.dto;

import commons.rest.FailedResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductsSuccessResponseItem extends FailedResponse {
    private String image;
    private String unit;
    private String provider;
    private Double price;
    private String title;
    private String promoDetails;
    private String brand;
    private Boolean isPromo;
    private String url;
}