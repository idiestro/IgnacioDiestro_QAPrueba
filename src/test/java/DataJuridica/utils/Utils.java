package DataJuridica.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Utils {

    public static Properties prop;

    /*
    Lectura del archivo de config.properties
     */
    public static Properties getConfigProperties() throws Exception{
        try {
            prop = new Properties();
            prop.load(new FileInputStream("config.properties"));

            return prop;
        }catch (Exception e){
            throw new Exception("No se encuentra el archivo de configuración: config.properties");

        }
    }
    /*
    Obtención de los test cases para el archivo main (Launcher)
     */
    public static ArrayList<String> getTestCases() throws Exception{
        ArrayList<String> testCases = new ArrayList<String>();

        testCases.add("DataJuridica.TS01_BackEnd.Test_TC01_ValidarRegistro_Encontrado");
        testCases.add("DataJuridica.TS01_BackEnd.Test_TC02_ValidarRegistro_NoEncontrado");

        return testCases;
    }

    /*
    Creación de carpetas donde se almacenarán los logs
    en formato: Log_fecha_hora
     */
    public static File createLogsFolder() throws Exception{
        DateFormat dfNameF = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
        String dat = dfNameF.format(new Date());
        String root = System.getProperty("user.dir") + FileSystems.getDefault().getSeparator() + "logs";
        File logsFolder = new File(root  + FileSystems.getDefault().getSeparator() + "Log_" + dat);
        return logsFolder;
    }

}
