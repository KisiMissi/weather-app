import in.ResponseReader;

import java.net.HttpURLConnection;

public class Connector {
    public String getResponse(String city, String apiKey) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        ConnectionBuilder connectionBuilder = new ConnectionBuilder();

        HttpURLConnection connection = connectionBuilder.getConnection(apiUrl);
        return ResponseReader.read(connection);
    }
}
