import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Report {
    Scanner scanner = new Scanner(System.in);
    FileReader fileReader = new FileReader();
    ArrayList<String[]> yearlyReportArray = new ArrayList<>();
    ArrayList<YearlyReport> yearlyReportObjects = new ArrayList<>();
    HashMap<Integer, List<MonthlyReport>> monthlyReportObjects = new HashMap<>();
    public static String yearlyFileName = "";
    List<String> fileNames = new ArrayList<>();

    public void runApp() {
        while (true) {
            printMenu();
            String point = scanner.nextLine();
            if (!isNumeric(point)) {
                System.out.println(Constants.PRINT_NUMBER);
                continue;
            }
            int command = Integer.parseInt(point);
            switch (command) {
                case 1:
                    readMonthlyReports();
                    break;
                case 2:
                    readYearlyReport();
                    break;
                case 3:
                    if (yearlyReportObjects.isEmpty()) {
                        System.out.println(Constants.REPORT_NOT_LOADED);
                        break;
                    }
                    if (monthlyReportObjects.isEmpty()) {
                        System.out.println(Constants.REPORTS_NOT_LOADED);
                        break;
                    }
                    CheckReports.CheckYearlyAndMonthlyReports(yearlyReportObjects, monthlyReportObjects);
                    break;
                case 4:
                    if (monthlyReportObjects.isEmpty()) {
                        System.out.println(Constants.REPORTS_NOT_LOADED);
                        break;
                    }
                    PrintInfoAboutMonthlyReport.printInformation(monthlyReportObjects);
                    break;
                case 5:
                    if (yearlyReportObjects.isEmpty()) {
                        System.out.println(Constants.REPORT_NOT_LOADED);
                        break;
                    }
                    PrintInfoAboutYearlyReport.printInformation(yearlyReportObjects, yearlyFileName);
                    break;
                case 6:
                    return;
                default:
                    System.out.println(Constants.NOT_COMMAND);
                    break;
            }
        }
    }

    public void readMonthlyReports() {
        while (true) {
            printMenuForMonthlyReports();
            String command = scanner.nextLine();
            if (!isNumeric(command)) {
                System.out.println(Constants.PRINT_NUMBER);
                break;
            }
            int pointOfMenu = Integer.parseInt(command);
            switch (pointOfMenu) {
                case 1:
                    System.out.println(Constants.PRINT_MONTH);
                    String fileMonth = scanner.nextLine();
                    if (!isNumeric(fileMonth)) {
                        System.out.println(Constants.PRINT_NUMBER);
                        break;
                    }
                    int month = Integer.parseInt(fileMonth);
                    if ((month <= 0) || (month >= 13)) {
                        System.out.println(Constants.WRONG_MONTH);
                        break;
                    }
                    if (monthlyReportObjects.containsKey(month)) {
                        System.out.println(Constants.ALREADY_EXISTS);
                        break;
                    }
                    System.out.println(Constants.PRINT_MONTHLY_REPORT_NAME);
                    String fileName = scanner.nextLine();
                    if (fileNames.contains(fileName)) {
                        System.out.println(Constants.FILE_ALREADY_LOADED);
                        break;
                    }
                    createMonthlyObjects(fileReader.readFileContents(fileName), month);
                    fileNames.add(fileName);
                    break;
                case 2:
                    System.out.println(Constants.REPORTS_LOADED);
                    return;
                default:
                    System.out.println(Constants.NOT_COMMAND);
                    break;
            }
        }
    }

    public void readYearlyReport() {
        System.out.println(Constants.PRINT_YEARLY_REPORT_NAME);
        String fileName = scanner.nextLine();
        yearlyFileName = fileName;
        createYearlyObjects(fileReader.readFileContents(fileName));
        System.out.println(Constants.YEARLY_REPORT_LOADED);
    }

    public void createYearlyObjects(List<String> yearReport) {
        for (String line : yearReport) {
            String[] lineContents = line.split(",");
            yearlyReportArray.add(lineContents);
        }
        yearlyReportArray.remove(0);
        for (String[] line : yearlyReportArray) {
            StringBuilder sb = new StringBuilder(line[0]);
            int month = Integer.parseInt(sb.substring(1));
            int amount = Integer.parseInt(line[1]);
            boolean isExpense = Boolean.parseBoolean(line[2]);
            yearlyReportObjects.add(new YearlyReport(month, amount, isExpense));
        }
    }

//    public void printYearReport() {
//        for (YearlyReport report : yearlyReportObjects) {
//            System.out.print(report.getMonth() + " ");
//            System.out.print(report.getAmount() + " ");
//            System.out.println(report.isExpense());
//        }
//    }

    public void createMonthlyObjects(List<String> monthReport, int month) {
        List<String[]> monthlyArrays = new ArrayList<>();
        for (String line : monthReport) {
            String[] lineContents = line.split(",");
            monthlyArrays.add(lineContents);
        }
        monthlyArrays.remove(0);
        List<MonthlyReport> monthlyReportLines = new ArrayList<>();
        for (String[] line : monthlyArrays) {
            boolean isExpense = Boolean.parseBoolean(line[1]);
            int quantity = Integer.parseInt(line[2]);
            int unitPrice = Integer.parseInt(line[3]);
            monthlyReportLines.add(new MonthlyReport(line[0], isExpense, quantity, unitPrice));
        }
        monthlyReportObjects.put(month, monthlyReportLines);
    }

//    public void printMonthlyReport() {
//        for (Integer key : monthlyReportObjects.keySet()) {
//            System.out.println("МЕСЯЦ: " + key);
//            for (MonthlyReport report : monthlyReportObjects.get(key)) {
//                System.out.print(report.getItemName() + " ");
//                System.out.print(report.isExpense() + " ");
//                System.out.print(report.getQuantity() + " ");
//                System.out.println(report.getUnitPrice());
//            }
//        }
//    }

    public void printMenu() {
        System.out.println(Constants.CHOOSE_COMMAND);
        System.out.println(Constants.READ_MONTHLY_REPORTS);
        System.out.println(Constants.READ_YEARLY_REPORT);
        System.out.println(Constants.CHECK_REPORTS);
        System.out.println(Constants.PRINT_INFO_ABOUT_MONTHLY_REPORTS);
        System.out.println(Constants.PRINT_INFO_ABOUT_YEARLY_REPORT);
        System.out.println(Constants.EXIT);
    }

    public void printMenuForMonthlyReports() {
        System.out.println(Constants.CONTINUE_LOADING);
        System.out.println(Constants.STOP_LOADING);
    }

    public static boolean isNumeric(String command) {
        if (command == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(command);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
