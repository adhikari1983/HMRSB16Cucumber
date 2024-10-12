package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static List<Map<String, String>> read(String sheetName, String path) {
        FileInputStream fileInputStream = null;
        // we are storing multiple sets of map as a list extracted from Excel sheet
        List<Map<String,String>> excelData = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(path);
/*         that special call which knows how to read the data from excel files
XSSFWorkbook is a class provided by the Apache POI library to handle Excel files in the .xlsx format (Excel 2007+).
HSSFWorkbook is used for older .xls (Excel 97-2003) files.
*/     XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = xssfWorkbook.getSheet(sheetName);
/* headerRow the 1st row, these are the header keys that stays same -> that's why it is outside the loop
               Rest of the rows with values that changes                        -> that's why it is inside the loop
               @@this rows is just for header keys, & we keep it on 0th row, coz it is not changing at all
*/     Row headerRow = sheet.getRow(0);
            //for rows, it starts from 1 index because 0 is already used for headers
            for (int rows = 1; rows < sheet.getPhysicalNumberOfRows(); rows++) {
                //@@this row is just for values
                Row row = sheet.getRow(rows);
                //L27 + L31 key & value concept demands -> Map<String, String> rowMap = new LinkedHashMap<>();
                Map<String, String> rowMap = new LinkedHashMap<>();
                //here, we  are taking all the columns starting from 0th index, unlike the headerRow
                for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {
                    String key = headerRow.getCell(col).toString();
                    String value = row.getCell(col).toString();
    //rowMap => is used to temporarily store the values for each row before adding that map to the excelData list.
                    rowMap.put(key, value);
                }
                excelData.add(rowMap);  /** adds the map in the list one by one using for loop*/
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return excelData;          /** and returns all the maps as in a single Excel sheet as list of maps*/
    }
}
