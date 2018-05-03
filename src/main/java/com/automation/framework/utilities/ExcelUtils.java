package com.automation.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static String readExcel(String filePath, int row, int col) throws Exception {

		File src = new File(filePath);
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workBook.getSheetAt(0);

		String value = sheet.getRow(row).getCell(col).getStringCellValue();
		return value;
	}

	public static void writeExcel(String filePath, String fileName, String sheetName, Object[] dataToWrite)
			throws IOException {

		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);
		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);
		}

		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();
	}
}