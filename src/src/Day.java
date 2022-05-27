package src.src;

public record Day(int dayNumber, int cases, int deaths, int recoveries, int sus) {
	private static int totalCases;
	private static int totalDeaths;
	private static int totalRecoveries;
	

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
