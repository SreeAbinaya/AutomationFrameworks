package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> readDataFromJson() throws IOException {
		File file = new File(System.getProperty("user.dir") + "//src//test//java//testData//Login.json");
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public Object[][] readDataFromExcel() throws InvalidFormatException, IOException {
		HashMap<String, String> hs = new HashMap<String, String>();
		File file = new File(System.getProperty("user.dir") + "//src//test//java//testData//Login.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		XSSFRow headerRow = sheet.getRow(0);
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.iterator();
			int k = 0;
			while (cells.hasNext()) {
				Cell cell = cells.next();
				String cellValue = cell.getStringCellValue();
				if (cellValue.equalsIgnoreCase("pmview")) {
					while ((headerRow.getCell(k) != null)) {
						String key = String.valueOf(headerRow.getCell(k));
						XSSFRow va = sheet.getRow(cell.getRowIndex());
						String value = String.valueOf(va.getCell(k));
						hs.put(key, value);
						k++;
					}
				}
			}

		}
		return new Object[][] { { hs } };

	}
}
