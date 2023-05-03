package DataJuridica.request;

import DataJuridica.report.Report;
import DataJuridica.utils.JsonParser;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import DataJuridica.utils.*;

import java.util.Properties;

public class Request_DataJuridica {

    private static Properties prop;
    private static String contenType = "application/x-www-form-urlencoded";
    public  String url = null;
    public  String body = null;

    /*
    Constructor de clase
     */
    public Request_DataJuridica(String requestBodyFile){
        try {
            //Leemos config.properties
            prop = Utils.getConfigProperties();
            url = prop.getProperty("BACKEND_ENDPOINT");
            //Instanciamos parseador Json para el body de las request
            JsonParser jsonParser = new JsonParser(requestBodyFile);
            body = jsonParser.readFile();

        }catch (Exception e){
            Report.reportConsoleLog(e.getMessage());
        }
    }

    /*
    Request POST para búsqueda correcta
     */
    public Response POST() throws Exception {
        try {
            Response response = given()
                    .relaxedHTTPSValidation()
                    .when()
                    .body(body)
                    .contentType(contenType)
                    .post(url);
            return response;
        } catch (Exception e) {
            String message = e.getMessage() != null ? e.getMessage() : "Encontrado error de solicitud para la URL: " + url;
            Report.reportConsoleLog(e.getMessage());
            Report.reportLog(message, Status.FAIL);
            throw new Exception();
        }
    }

}
