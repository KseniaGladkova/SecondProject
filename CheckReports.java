import java.util.List;
import java.util.HashMap;

public class CheckReports {

    public static void CheckYearlyAndMonthlyReports(List<YearlyReport> yearlyReport,
                                                    HashMap<Integer, List<MonthlyReport>> monthlyReports) {
        if (checkRates(yearlyReport, monthlyReports) && checkIncomes(yearlyReport, monthlyReports)) {
            System.out.println(Constants.IS_OK);
        }
    }

    public static boolean checkRates(List<YearlyReport> yearlyReport,
                                     HashMap<Integer, List<MonthlyReport>> monthlyReports) {
        boolean isOK = false;
        for (Integer key : monthlyReports.keySet()) {
            int summaryRate = 0;
            for (MonthlyReport report : monthlyReports.get(key)) {
                if (report.isExpense()) {
                    summaryRate += (report.getQuantity() * report.getUnitPrice());
                }
            }
            for (YearlyReport yearly : yearlyReport) {
                if (yearly.getMonth() == key) {
                    if (yearly.isExpense()) {
                        if (yearly.getAmount() == summaryRate) {
                            isOK = true;
                        } else {
                            System.out.println(Constants.ERROR + PrintInfoAboutYearlyReport.printMonthName(key));
                            isOK = false;
                        }
                    }
                }
            }
        }
        return isOK;
    }

    public static boolean checkIncomes(List<YearlyReport> yearlyReport,
                                       HashMap<Integer, List<MonthlyReport>> monthlyReports) {
        boolean isOK = false;
        for (Integer key : monthlyReports.keySet()) {
            int summaryIncome = 0;
            for (MonthlyReport report : monthlyReports.get(key)) {
                if (!report.isExpense()) {
                    summaryIncome += (report.getQuantity() * report.getUnitPrice());
                }
            }
            for (YearlyReport yearly : yearlyReport) {
                if (yearly.getMonth() == key) {
                    if (!yearly.isExpense()) {
                        if (yearly.getAmount() == summaryIncome) {
                            isOK = true;
                        } else {
                            System.out.println(Constants.ERROR + PrintInfoAboutYearlyReport.printMonthName(key));
                            isOK = false;
                        }
                    }
                }
            }
        }
        return isOK;
    }
}
