import java.util.List;

public class PrintInfoAboutYearlyReport {

    public static void printInformation(List<YearlyReport> report, String filename) {
        System.out.println(Constants.YEAR_NAME + findYearName(filename));
        printIncome(report);
        System.out.println(Constants.AVERAGE_INCOME + findAverageIncome(report));
        System.out.println(Constants.AVERAGE_RATE + findAverageRate(report));

    }

    public static String findYearName(String fileName) {
        StringBuilder sb = new StringBuilder(fileName);
        return sb.substring(2, 6);
    }

    public static void printIncome(List<YearlyReport> report) {
        int rate = 0;
        int income = 0;
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i).getMonth() == report.get(i + 1).getMonth()) {
                if (report.get(i).isExpense()) {
                    rate = report.get(i).getAmount();
                    income = report.get(i + 1).getAmount();
                } else {
                    rate = report.get(i + 1).getAmount();
                    income = report.get(i).getAmount();
                }
                System.out.println(Constants.INCOME + printMonthName(report.get(i).getMonth()) + ": "
                        + (income - rate));
                income = 0;
                rate = 0;
            }
        }
    }

    public static int findAverageIncome(List<YearlyReport> report) {
        int summaryIncome = 0;
        int amount = 0;
        for (YearlyReport rep : report) {
            if (!rep.isExpense()) {
                summaryIncome += rep.getAmount();
                amount++;
            }
        }
        return summaryIncome / amount;
    }

    public static int findAverageRate(List<YearlyReport> report) {
        int summaryRate = 0;
        int amount = 0;
        for (YearlyReport rep : report) {
            if (rep.isExpense()) {
                summaryRate += rep.getAmount();
                amount++;
            }
        }
        return summaryRate / amount;
    }

    public static String printMonthName(int month) {
        String months = "";
        switch (month) {
            case 1:
                months = "январь";
                break;
            case 2:
                months = "февраль";
                break;
            case 3:
                months = "март";
                break;
        }
        return months;
    }
}
