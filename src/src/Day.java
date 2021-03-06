/**
 * Record which contains day data. 
 * @author Daniel Welicki 
 */
package src;

public record Day(int dayNumber, int cases, int deaths, int recoveries, int sus, int infect) {
	private static int totalCases;
	private static int totalDeaths;
	private static int totalRecoveries;
	private static int totalActive;
	
	public static void setTotalActive(int cases) {
		totalActive = cases;
	}
	
	public static int getTotalActive() {
		return totalActive;
	}

	public static void addTotalCases(int cases) {
		totalCases += cases;
	}
	public static void addTotalDeaths(int deaths) {
		totalDeaths += deaths;
	}

	public static void addTotalRecoveries(int recoveries) {
		totalRecoveries += recoveries;
	}
	public static int totalDeaths() {
		return totalDeaths;
	}
	public static int totalRecoveries() {
		return totalRecoveries;
	}

	public static int totalCases() {
		return totalCases;
	}
		
}
