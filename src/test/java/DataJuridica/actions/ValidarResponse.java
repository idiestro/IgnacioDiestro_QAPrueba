package DataJuridica.actions;

import com.aventstack.extentreports.Status;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import DataJuridica.report.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidarResponse {

    //Variables de los xpath en la response
    private String xpath_Doc_OK = "**.find { it.@class == 'table' }.tbody.tr.td[2]";
    private String xpath_Doc_NOK = "**.find{ it.@class == 'no-result' }.div.h3";

    //Variables de los resultados esperados
    private String ExpectedResult_OK = "*****040";
    private String ExpectedResult_NOK = "NO SE ENCUENTRA INFORMACION PARA NACHO DIESTRO GIL";
    private int ExpectedResponse_OK = 200;

    /*
    Valida la respuesta de la página para una búsqueda correcta
     */
    public void response_OK(Response response) throws Exception{

        //Assertion
        assertTrue(response.statusCode() == ExpectedResponse_OK,
                "La respuesta a la petición es '" + response.statusCode() + "' y la esperada: '"
                + ExpectedResponse_OK + "'");
        //Reporte html
        Report.reportLog("La respuesta a la petición es <strong>'" + response.statusCode() + "'</strong> y la esperada: '"
                + ExpectedResponse_OK + "'", Status.PASS);

        //Parseamos response en html
        XmlPath htmlpath = new XmlPath(XmlPath.CompatibilityMode.HTML,response.getBody().asString());

        //Assertion
        assertTrue(htmlpath.getString(xpath_Doc_OK).contains(ExpectedResult_OK),
                "El valor del campo DOC es: '" + htmlpath.getString(xpath_Doc_OK) + "' y el esperado: '"
                + ExpectedResult_OK + "'");
        //Reporte html
        Report.reportLog("El valor del campo DOC es: '<strong>" + htmlpath.getString(xpath_Doc_OK) + "</strong>' y el esperado: <strong>'"
                + ExpectedResult_OK + "'</strong>", Status.PASS);
    }
    /*
    Valida la respuesta de la página para una búsqueda incorrecta
    */
    public void response_NOK(Response response) throws Exception{

        //Asserion
        assertTrue(response.statusCode() == ExpectedResponse_OK,
                "La respuesta a la petición es '" + response.statusCode() + "' y la esperada: '"
                        + ExpectedResponse_OK + "'");
        //Reporte html
        Report.reportLog("La respuesta a la petición es <strong>'" + response.statusCode() + "'</strong> y la esperada: <strong>'"
                + ExpectedResponse_OK + "'</strong>", Status.PASS);

        //Parseamos response en html
        XmlPath htmlpath = new XmlPath(XmlPath.CompatibilityMode.HTML,response.getBody().asString());

        //Assertion
        assertTrue(htmlpath.getString(xpath_Doc_NOK).contains(ExpectedResult_NOK),
                "El valor del campo DOC es: '" + htmlpath.getString(xpath_Doc_NOK) + "' y el esperado: '"
                        + ExpectedResult_NOK + "'");
        //Reporte html
        Report.reportLog("El valor del campo DOC es: <strong>'" + htmlpath.getString(xpath_Doc_NOK) + "'</strong> y el esperado: <strong>'"
                + ExpectedResult_NOK + "'</strong>", Status.PASS);
    }

}
