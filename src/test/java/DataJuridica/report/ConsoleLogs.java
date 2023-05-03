package DataJuridica.report;

import org.apache.log4j.*;
import java.io.File;

public class ConsoleLogs {

    public Logger logger;

    /*
    Constructor de clase
     */
    public ConsoleLogs(String caseName){
        try{
            //Eliminamos log anterior
            File file = new File(System.getProperty("user.dir") + "\\logs\\ConsoleLogs.log");
            file.delete();
            //Iniciamos el logger
            initConsoleLogs(caseName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
    Módulo de inicialización del logger
     */
    private void initConsoleLogs(String caseName) throws Exception{
        logger = Logger.getLogger(caseName);
        //Leemos y cargamos archivo de configuración
        String log4jConfigFile = System.getProperty("user.dir") + "\\src\\test\\java\\DataJuridica\\resources\\" + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
    }

}