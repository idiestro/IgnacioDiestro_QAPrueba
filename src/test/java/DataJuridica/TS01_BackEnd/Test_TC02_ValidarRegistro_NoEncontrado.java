package DataJuridica.TS01_BackEnd;


import DataJuridica.actions.ValidarResponse;
import DataJuridica.request.Request_DataJuridica;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import DataJuridica.report.*;

import java.io.File;
import java.util.Properties;

public class Test_TC02_ValidarRegistro_NoEncontrado {

    private static Request_DataJuridica requestDataJuridica;
    private static ValidarResponse validarResponse;
    private static ExtentHtml extentHtml;
    private static ConsoleLogs consoleLogs;

    public static String suiteName = "TS01_BackEnd";
    public static String caseName = Test_TC02_ValidarRegistro_NoEncontrado.class.getSimpleName();

    public static Properties prop;
    public static String level;
    public static String finalResult = "OK";
    public static File folderTestCase;
    public static File folderDownloads;

    public static String requestBodyFile = "resources\\request\\Request_NOK.json";
    public static Response response;


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
    @DisplayName("TC02_ValidarRegistro_NoEncontrado")
    public void test() throws Exception{
        try{

            Report.reportLog("Inicio de la ejecución",Status.PASS);

            response = requestDataJuridica.POST();
            Report.reportLog("Request DataJuridica POST enviada satisfactoriamente a <strong>" + requestDataJuridica.url + "</strong>\n"
                            +"<textarea readonly>" + requestDataJuridica.body + "</textarea>",
                    Status.PASS);

            Report.reportLog("La Response obtenida es: " +"<textarea readonly>" + response.getBody().asString() + "</textarea>",
                    Status.PASS);

            validarResponse.response_NOK(response);
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

        if(finalResult == "OK"){
            Report.reportLog("Resultado de Test_TC02_ValidarRegistro_NoEncontrado: " + finalResult, Status.PASS);
        } else {
            Report.reportLog("Resultado de Test_TC02_ValidarRegistro_NoEncontrado: " + finalResult,Status.FAIL);
        }
        extentHtml.closeReport();
    }



    public static void setUpEnvironment() throws Exception{

        extentHtml = new ExtentHtml(suiteName,caseName);
        consoleLogs = new ConsoleLogs(caseName);

        new Report(extentHtml.getTest(), consoleLogs.logger);

        requestDataJuridica = new Request_DataJuridica(requestBodyFile);
        validarResponse = new ValidarResponse();
    }

}