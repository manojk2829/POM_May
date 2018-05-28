package Excel_reader_Util;

import java.util.Hashtable;

public class ExcelReader_data {
	
	public static Object[][] getExcelData(Xls_Reader xls,String sheetN,String TN){
		String sheetName = sheetN;
		int testStart = 1;
		while(!xls.getCellData(sheetName, 0, testStart).equals(TN)) {
			testStart++;
		}System.out.println(testStart);
		
		int row=0;
		int rowStart=testStart+2;
		int colStart = testStart+1;
		while(!xls.getCellData(sheetName, 0, rowStart+row).equals("")) {
			row++;
		}System.out.println(row);
		
		int col=0;
		while(!xls.getCellData(sheetName, col, colStart).equals("")) {
			col++;
		}System.out.println(col);

		Object[][] data=new Object[row][1];
		int RD=0;
		Hashtable<String,String> tb=null;
		for(int r=rowStart;r<rowStart+row;r++) {
			tb=new Hashtable<String,String>();
			for(int c=0;c<col;c++) {
				String key=xls.getCellData(sheetName, c, colStart);
				String value = xls.getCellData(sheetName, c, r);
				tb.put(key, value);
			}			
			data[RD][0]=tb;
			RD++;
		}
		return data;
	}
}
