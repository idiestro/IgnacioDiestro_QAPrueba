package DataJuridica.utils;

import java.io.FileReader;

import org.json.simple.*;
import org.json.simple.parser.*;


public class JsonParser {

    private JSONObject jsonObject;

    /*
    Constructor de clase
     */
    public JsonParser(String fileName){
        try{
            //Parseamos el archivo json
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject) object;

        }catch (Exception e){
            System.out.println(e);
        }
    }

    /*
    Lectura del archivo Json y creación del body para las request
     */
    public String readFile() throws Exception{
        String RequestVerificationToken = jsonObject.get("__RequestVerificationToken").toString();
        String optradio = jsonObject.get("optradio").toString();
        String iNombre = jsonObject.get("iNombre").toString();
        String iApellidos = jsonObject.get("iApellido").toString();
        String Nombre = jsonObject.get("Nombre").toString();
        String TipoDocumento = jsonObject.get("TipoDocumento").toString();

        String body = "__RequestVerificationToken:=" + RequestVerificationToken + "&" + "optradio:=" + optradio + "&"
                +"iNombre:=" + iNombre + "&" + "iApellido:=" + iApellidos + "&" + "Nombre=" + Nombre + "&"
                +"TipoDocumento=" + TipoDocumento;

        return body;
    }
}
