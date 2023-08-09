package commons.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ResponseHandler {

    protected ObjectMapper mapper = new ObjectMapper();

    public <T> List<T> getResponseAsList(Response response, Class<T> klass) {
        try {
            return mapper.readValue(response.getBody().asString(), mapper.getTypeFactory().constructParametricType(List.class, klass));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T extends FailedResponse> T getResponse(Response response, Class<T> klass) {
        try {
            return handleResponse(response, klass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends FailedResponse> T handleResponse(Response response, Class<T> klass) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (response.getStatusCode() >= HttpStatus.SC_BAD_REQUEST) {
            return handleErrorResponse(response, klass);
        } else {
            return handleSuccessResponse(response, klass);
        }
    }

    private <T extends FailedResponse> T handleSuccessResponse(Response response, Class<T> klass) throws IOException {
        return mapper.readValue(response.getBody().asString(), klass);
    }

    private <T extends FailedResponse> T handleErrorResponse(Response response, Class<T> klass) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Error[] errors = mapper.readValue(response.getBody().asString(), Error[].class);
        T instance = klass.getDeclaredConstructor().newInstance();
        instance.errors = errors;
        return instance;
    }
}