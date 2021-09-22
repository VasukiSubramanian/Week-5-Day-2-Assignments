package week5.day2.assignments;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readData(String wbName) throws IOException {

		XSSFWorkbook wb = new XSSFWorkbook("./data/" + wbName + ".xlsx");
		XSSFSheet ws = wb.getSheet("Sheet1");
		int rowCount = ws.getLastRowNum();
		int cellCount = ws.getRow(0).getLastCellNum();

		String[][] data = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				String text = ws.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j] = text;
			}
		}

		wb.close();
		
		return data;
	}

}
