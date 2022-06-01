package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelWriter {
	
//	public void convertArray() {
//		ArrayList<String[]> convertedData = new ArrayList<String[]>();
//		
//		
//		convertedData.add();
//
//		 {
//			convertedData.add(new String[]
//					{, ,Integer.toString(d.recoveries()), 
//					 Integer.toString(d.sus()), Integer.toString(d.infect())});
//		}
//		
//	}
	
	public static void writeCsv(String virusName, String path) {
		ArrayList<Day> dayData = Simulator.getDayData();
		try {
			PrintWriter writeExcel = new PrintWriter(path + "/" + virusName + ".csv");
			StringBuilder buildExcel = new StringBuilder();
			String[] categories = {"Day Number", "Deaths", "Recoveries", "Susceptible People", "Infected People"};
			for(String s : categories) {
				buildExcel.append(s);
				buildExcel.append(",");
			}
			buildExcel.append("\n");
			for(Day d : dayData) {
				buildExcel.append(Integer.toString(d.dayNumber()));
				buildExcel.append(",");
				buildExcel.append(Integer.toString(d.deaths()));
				buildExcel.append(",");
				buildExcel.append(Integer.toString(d.recoveries()));
				buildExcel.append(",");
				buildExcel.append(Integer.toString(d.sus()));
				buildExcel.append(",");
				buildExcel.append(Integer.toString(d.infect()));
				buildExcel.append(",");
				buildExcel.append("\n");
			}
			writeExcel.write(buildExcel.toString());
			writeExcel.close();

		} catch (FileNotFoundException e) {
			System.out.println("File unable to be written.");

		    
		}
	}	
	

}
