package core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class ExcelDataProviderCustomerInfo {

    public static List<Map<String, String>> getTestDataFromCustomerInfoFile() throws IOException {
        List<Map<String,String>> testDataAllRows=null;
        Map<String,String> testData=null;
        FileInputStream fileInputStream=new FileInputStream("./src/main/resources/testdata/DataCustomerInfo_File.xlsx");
        Workbook workbook=new XSSFWorkbook(fileInputStream);
        Sheet sheet=workbook.getSheet("Demo");
        int lastRowNumber=sheet.getLastRowNum();
        int lastColNumber=sheet.getRow(0).getLastCellNum();
        List list=new ArrayList();
        //go to all columns
        for(int i=0; i<lastColNumber;i++){
            Row row=sheet.getRow(0); //reading all column value of row0
            Cell cell=row.getCell(i);
            String rowHeader = cell.getStringCellValue().trim();
            list.add(rowHeader); //Headers stored in the List
        }
        testDataAllRows=new ArrayList<Map<String,String>>();
        //STARTING ALL ROWS FROM #1 to end. ROW #0 is header
        for (int j=1;j<=lastRowNumber;j++){
            Row row=sheet.getRow(j);
            testData=new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            //each column for every row(j)
            for(int k=0;k<lastColNumber;k++){
                Cell cell=row.getCell(k);
                String colValue = getCellDataAsString((XSSFCell) cell.getRow().getCell(k));
                //String colValue=cell.toString().trim();   //.getStringCellValue().trim();
                //KEY           //VALUE
                testData.put((String) list.get(k),colValue);
            }
            testDataAllRows.add(testData);
        }
        return testDataAllRows;  //TESTDATA FOR ALL ROWS
    }


    private static String getCellDataAsString(XSSFCell cell) {
        String value = "";
        if (cell != null) {
            CellType cellType = cell.getCellType();
            switch (cellType) {
                case BLANK:
                    value = "";
                    break;
                case BOOLEAN:
                    value = (cell.getBooleanCellValue()) ? "true" : "false";
                    break;
                case ERROR:
                    value = cell.getErrorCellString();
                    break;
                case FORMULA:
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    value = Double.toString(cell.getNumericCellValue());
                    break;
                case STRING:
                default:
                    value = cell.getStringCellValue();
            }
        }
        return value.trim();
    }


}
