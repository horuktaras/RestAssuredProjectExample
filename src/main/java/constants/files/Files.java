package constants.files;

import helpers.FileHelper;

import java.io.File;

import static constants.files.JsonFileNames.ERROR_SCHEMA;
import static constants.files.JsonFileNames.PRODUCTS_SCHEMA;

public class Files {
    public static class JsonSchemas {
        public static final File PRODUCTS = FileHelper.getFromSchemas(PRODUCTS_SCHEMA);
        public static final File ERROR = FileHelper.getFromSchemas(ERROR_SCHEMA);
    }
}