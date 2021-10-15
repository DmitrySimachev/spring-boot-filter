import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;


public class Test {

    public static String urlReceive = "http://localhost:5000/receive";
    public static String urlSave = "http://localhost:5000/save";
    private static String str = "c";
    private static String tag = "sfksdkmfskldf";

    public static void main(String[] args) {

        System.err.println("Пользователь сохранил тэг: " + tag);
        JSONObject json = getSocksJSON(tag);
        System.err.println(json.toString());
        testSavedTag(urlSave, json);
        System.err.println("Пользователь ввел: " + str);
        testSocksIncome();
    }

    private static void testSocksIncome() {
        sendHTTPRequest(urlReceive, str);
    }

    private static void testSavedTag(String url, JSONObject json) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);

            httpClient.execute(request);
            System.err.println("Пользователь получил сообщение о сохранении в бд: ");
            //todo ответ с сервера о деталях сохранении
            httpClient.close();
        } catch (Exception ignored) { }
    }

    private static void sendHTTPRequest(String url, String str) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpGet request = new HttpGet(url + "?str=" + str);

            httpClient.execute(request);

            System.err.println("Пользователь получил: ");
            //todo получить данные с сервера
            httpClient.close();
        } catch (Exception ignored) { }
    }

    public static JSONObject getSocksJSON(String tag) {
        JSONObject json = new JSONObject();
        try {
            json.put("tag", tag);
        } catch (JSONException ignored) { }

        return json;
    }
}
