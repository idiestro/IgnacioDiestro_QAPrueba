package DataJuridica.report;

import DataJuridica.utils.Utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;

import java.util.Properties;
public class Report {

    public static ExtentTest test;
    public static Logger logger;

    private Properties prop;
    private static String LOG_LEVEL;


    /*
    Constructor de clase
     */
    public Report(ExtentTest testReport, Logger loggerReport) throws Exception{
        //Almacenamos variables externas
        test = testReport;
        logger = loggerReport;

        //Leemos config.prop
        prop = Utils.getConfigProperties();
        LOG_LEVEL = prop.getProperty("LOG_LEVEL");
    }
    /*
    Escritura de logs en reporte HTML
     */
    public static void reportLog(String msg, Status status){
        test.log(status,msg);
    }
    /*
    Escritura de logs en reporte .log
     */
    public static void reportConsoleLog(String msg){
        //Asignamos el log level en función del archivo de configuración
        switch(LOG_LEVEL){
            case "INFO":
                logger.info(msg);
                break;
            case "DEBUG":
                logger.debug(msg);
                break;
            case "ERROR":
                logger.error(msg);
        }
    }
}

