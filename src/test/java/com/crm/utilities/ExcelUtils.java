package com.crm.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crm.base.TestBase;


public class ExcelUtils extends TestBase{
	
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	static Sheet sheet;
	static HashMap<String, Integer> excelColumns = new HashMap<String, Integer>();
	
	public static String TEST_DATA_SHEET_PATH=System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\TestData.xlsx";
	public static void setExcelFile(String xlfile,String xlsheet) throws Exception {
		 
			try {

   			FileInputStream ExcelFile = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(ExcelFile);
			ws = wb.getSheet(xlsheet);
			} catch (Exception e){
				throw (e);
			}
	}

	
	//get the row count from excel
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		//wb.close();
		fi.close();
		return rowcount;		
	}
	
	//get the cell count from excel
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		//wb.close();
		fi.close();
		return cellcount;
	}
	
	
	//*********************************Excel Utils********************************
	//To read data from excel
	
	public static String getCellData(String sheetname,String colname,int rownum) throws IOException
	{
		FileInputStream file= new FileInputStream(TEST_DATA_SHEET_PATH);
		wb=new XSSFWorkbook(file); 
		
		int colum_Num = 0;
		
		//int colum_Num = -1;
		ws = wb.getSheet(sheetname);
		row = ws.getRow(0);

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colname.trim()))
				colum_Num = i;
		}
		row = ws.getRow(rownum);
		cell = row.getCell(colum_Num);
		
		String data;
		try {	
		DataFormatter formatter = new DataFormatter();
        String cellData = formatter.formatCellValue(cell);
        return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		
		fi.close();
		return data;
	}
	
	
	//write to excel with column header
	public static void setCellData(String sheetName,String colName,int rowNum,String data) throws Exception
	{
		File f = new File(TEST_DATA_SHEET_PATH);

		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(f);

		//creating workbook instance that refers to .xls file
		 wb=new XSSFWorkbook(inputStream); 

		 sheet = wb.getSheet(sheetName);
		//XSSFSheet sh = wb.getSheetAt(0);    //0 - index of 1st sheet

		//adding all the column header names to the map 'columns'
		sheet.getRow(0).forEach(cell ->{
			excelColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
		});
		
		writeToExcel(sheetName, rowNum, excelColumns.get(colName), data);
		 
	}
	
	
	//To write data to excel
	public static void writeToExcel(String sheet, int rownum, int colnum, String data)
			throws EncryptedDocumentException, IOException {

		try {
			Thread.sleep(1000);

			File f = new File(TEST_DATA_SHEET_PATH);

			// Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(f);

			// creating workbook instance that refers to .xls file
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);

			// creating a Sheet object using the sheet Name
			XSSFSheet s = wb.getSheet(sheet);

			// Create a row object to retrieve row at index m
			 XSSFRow row=s.getRow(rownum);
		
			 //row = s.createRow(rownum);
            
			XSSFCell cell = row.createCell(colnum);
			cell.setCellValue(data);

			// Write the data back in the Excel file
			FileOutputStream outputStream = new FileOutputStream(TEST_DATA_SHEET_PATH);
			wb.write(outputStream);
			Thread.sleep(1000);

			// wb.close();
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			System.out.println("write exception :" + e);
		}
	
	}
	
	
	//get Row count from excel
	public static int getRowCount(String sheetName) throws IOException{
		
		 FileInputStream file= new FileInputStream(TEST_DATA_SHEET_PATH);
		  
		wb=new XSSFWorkbook(file); 
		int index = wb.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = wb.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
	
	
	
	}
	
	
}
