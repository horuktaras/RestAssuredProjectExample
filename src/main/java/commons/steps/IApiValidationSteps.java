package commons.steps;

import java.io.File;

public interface IApiValidationSteps {
    IApiValidationSteps verifyStatusCode(int statusCode);
    IApiValidationSteps verifyResponseSchema(File schemaFile);
}