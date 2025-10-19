package dataproviders;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.example.api.annotations.CSVFile;
import com.example.api.utils.CSVUtils;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Generic DataProvider that reads data from CSV files
 * specified via @CSVFile annotation.
 */
public class TestDataProvider {

    @DataProvider(name = "csvDataProvider")
    public static Object[][] getCSVData(Method testMethod) throws CsvValidationException {
        // Retrieve @CSVFile annotation from test method
        CSVFile annotation = testMethod.getAnnotation(CSVFile.class);

        if (annotation == null) {
            throw new IllegalArgumentException(
                "Missing @CSVFile annotation on test method: " + testMethod.getName()
            );
        }

        String path = annotation.path();
        List<String[]> records = CSVUtils.readCSV(path);

        Object[][] data = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            data[i] = records.get(i);
        }

        return data;
    }
}
