package dataDrivenTesting;

import java.util.List;

public class ReadData {

	// Data Driven Testing Methods

	public void readCSVData() {
		String pathToFile = "";
		List<String[]> data = dataDrivenTesting.CsvData.get(pathToFile);
		for (String[] record : data) {
			for (String field : record) {
				System.out.println(field);
			}
		}
	}

	public void readXLSData() {
		String pathToFile = "";
		String[][] data = dataDrivenTesting.XcelData.get(pathToFile);
		for (String[] record : data) {
			for (String field : record) {
				System.out.println(field);
			}
		}
	}
}
