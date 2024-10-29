package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Map;

public class Example {
    public void exampleMethod() {
        Gson gson = new Gson();
        String jsonResponse = "{...}";  // sua resposta JSON aqui

        // Exemplo de conversão para Map
        Map<String, Object> data = gson.fromJson(jsonResponse, new TypeToken<Map<String, Object>>(){}.getType());

        // Exemplo de conversão para uma lista de objetos específicos
        List<YourObject> yourObjectList = gson.fromJson(jsonResponse, new TypeToken<List<YourObject>>(){}.getType());
    }
}
