package DataJuridica.TS01_BackEnd;


import DataJuridica.actions.ValidarResponse;
import DataJuridica.request.Request_DataJuridica;
import DataJuridica.utils.Utils;
import com.aventstack.extentreports.Status;
import com.mongodb.internal.connection.tlschannel.util.Util;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import DataJuridica.report.*;

import java.util.Properties;


public class Test_TC01_ValidarRegistro_Encontrado {

    private static Request_DataJuridica requestDataJuridica;
    private static ValidarResponse validarResponse;
    private static ExtentHtml extentHtml;
    private static ConsoleLogs consoleLogs;

    public static String suiteName = "TS01_BackEnd";
    public static String caseName = Test_TC01_ValidarRegistro_Encontrado.class.getSimpleName();

    public static Properties prop;
    public static String finalResult = "OK";


    public static String requestBodyFile = "resources\\request\\Request_OK.json";
    public static Response response;

    /*
    Configuración inicial del test
     */
    @BeforeEach
    public void beforeEach() throws Exception{
        try{
            setUpEnvironment();

        } catch (Exception e) {
            Report.reportConsoleLog(e.getMessage());
            finalResult = "BQ";
            Report.reportLog(e.getMessage(),Status.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("TC01_ValidarRegistro_Encontrado")
    public void test() throws Exception{
        try{
            //Reporte html
            Report.reportLog("Inicio de la ejecución",Status.PASS);

            //Enviamos request
            response = requestDataJuridica.POST();
            //Reporte html
            Report.reportLog("Request DataJuridica POST enviada satisfactoriamente a <strong>" + requestDataJuridica.url + "</strong>\n"
                    +"<textarea readonly>" + requestDataJuridica.body + "</textarea>",
                    Status.PASS);
            //Reporte html
            Report.reportLog("La Response obtenida es: " +"<textarea readonly>" + response.getBody().asString() + "</textarea>",
                    Status.PASS);

            //Validamos reponse
            validarResponse.response_OK(response);
            Report.reportLog("Request DataJuridica POST validada satisfactoriamente",
                    Status.PASS);


        }catch (AssertionError | Exception e){
            Report.reportConsoleLog(e.getMessage());
            if(finalResult != "BQ"){
                finalResult = "KO";
            }
            Report.reportLog(e.getMessage(),Status.FAIL);
            throw new Exception(e);
        }
    }

    @AfterEach
    public void afterEach(){
        //Reporte html en función dle resultado
        if(finalResult == "OK"){
            Report.reportLog("Resultado de Test_TC01_ValidarRegistro_Encontrado: " + finalResult, Status.PASS);
        } else {
            Report.reportLog("Resultado de Test_TC01_ValidarRegistro_Encontrado: " + finalResult,Status.FAIL);
        }
        extentHtml.closeReport();
    }


    /*
    Módulo de configuraciones iniciales
     */
    public static void setUpEnvironment() throws Exception{
        extentHtml = new ExtentHtml(suiteName,caseName);
        consoleLogs = new ConsoleLogs(caseName);
        new Report(extentHtml.getTest(), consoleLogs.logger);
        requestDataJuridica = new Request_DataJuridica(requestBodyFile);
        validarResponse = new ValidarResponse();
    }

}