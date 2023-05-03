package DataJuridica.report;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.File;

import DataJuridica.utils.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentHtml {

    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;

    public static boolean isInitialized = false;
    public static ExtentTest test;
    public static ExtentTest extentSuiteName;

    /*
    Constructor de clase
    Si no está creado el reporte genera uno nuevo. Si está creado, lo mantiene
     */
    public ExtentHtml (String suiteName, String caseName) throws Exception{
        if (!isInitialized){
            createReport(suiteName,caseName);
            isInitialized = true;
            extentSuiteName = extent.createTest(suiteName);
        }

        test = extentSuiteName.createNode(caseName);
    }

    /*
    Getter para ExtentTest test
     */
    public ExtentTest getTest(){
        return test;
    }

    /*
    Se crea el reporte inicial
     */
    public void createReport(String suiteName, String caseName) throws Exception{
        //Creamos carpeta logs
        File file = Utils.createLogsFolder();
        //Creamos reporte
        extent = new ExtentReports();
        htmlReporter = new ExtentSparkReporter(file + "\\Results_DataJuridica.html");
        customizeHtml(htmlReporter);
        //Asignamos modalidad de Suite
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);

        extent.flush();
    }
    /*
    Cierre del reporte
     */
    public void closeReport(){
        extent.flush();
    }

    /*
    Configura el reporte con los datos de la máquina
     */
    private static void customizeHtml(ExtentSparkReporter htmlReporter) throws Exception{
        String username = System.getProperty("user.name");
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String javaVersion = System.getProperty("java.specification.version");

        extent.setSystemInfo("Tester name", username);
        extent.setSystemInfo("Operative System", os);
        extent.setSystemInfo("Java Version", javaVersion);
        extent.attachReporter(htmlReporter);
    }

}


