package commons.testdata;

public class AbstractDataProvider {

    public static Object[][] getObjects(Object[] obj) {
        Object[][] data = new Object[obj.length][1];
        for (int i = 0; i < obj.length; i++) {
            data[i][0] = obj[i];
        }
        return data;
    }
}
