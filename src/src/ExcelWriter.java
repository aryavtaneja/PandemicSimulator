/**
 * File writer for excel data. 
 * @author Daniel Welicki 
 * @author Aryav Taneja
 */

package src;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	
	public static void writeCsv(String virusName, String path) throws FileNotFoundException {
		ArrayList<Day> dayData = Simulator.getDayData();
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

		} 
	}	
	
