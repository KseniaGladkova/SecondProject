import java.util.HashMap;
import java.util.List;

public class PrintInfoAboutMonthlyReport {

    public static void printInformation(HashMap<Integer, List<MonthlyReport>> report) {
        for (Integer key : report.keySet()) {
            System.out.println(Constants.MONTH + PrintInfoAboutYearlyReport.printMonthName(key));
            printInfo(report.get(key));
        }
    }

    public static void printInfo(List<MonthlyReport> report) {
        profitableProduct(report);
        maxRate(report);
    }

    public static void profitableProduct(List<MonthlyReport> monthlyReport) {
        MonthlyReport profitable = null;
        int maxPrice = 0;
        for (MonthlyReport report : monthlyReport) {
            if (!report.isExpense()) {
                if ((report.getQuantity() * report.getUnitPrice()) > maxPrice) {
                    maxPrice = report.getQuantity() * report.getUnitPrice();
                    profitable = report;
                }
            }
        }
        if (profitable != null) {
            System.out.println(Constants.PROFITABLE_PRODUCT + profitable.getItemName() + Constants.PROFITABLE_SUM +
                    maxPrice);
        }
    }

    public static void maxRate(List<MonthlyReport> monthlyReport) {
        MonthlyReport maxRate = null;
        int maxPrice = 0;
        for (MonthlyReport report : monthlyReport) {
            if (report.isExpense()) {
                if ((report.getQuantity() * report.getUnitPrice()) > maxPrice) {
                    maxPrice = report.getQuantity() * report.getUnitPrice();
                    maxRate = report;
                }
            }
        }
        if (maxRate != null) {
            System.out.println(Constants.MAX_RATE + maxRate.getItemName() + Constants.MAX_PRICE + maxPrice);
        }
    }
}
