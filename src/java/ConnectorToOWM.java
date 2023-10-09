import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ConnectorToOWM implements CityWeather {

    private String API_URL;
    private String API_KEY;

    private static final String OWM_PROPERTIES_FILE = "owm.properties";
    private static final String OWM_PROPERTIES_URL = "owm.url";
    private static final String OWM_PROPERTIES_KEY = "owm.key";

    private final HttpClient client;

    public ConnectorToOWM() {
        setProperties();
        client = HttpClient.newHttpClient();
    }

    private void setProperties() {
        InputStream input = this.getClass().getResourceAsStream(OWM_PROPERTIES_FILE);
        Properties props = new Properties();
        try {
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error occurred while reading " + OWM_PROPERTIES_FILE);
        }
        API_URL = props.getProperty(OWM_PROPERTIES_URL);
        API_KEY = props.getProperty(OWM_PROPERTIES_KEY);
    }

    @Override
    public String getWeatherUnformatted(String city) {
        HttpResponse<String> response = getResponse(city);
        return response.body();
    }

    @Override
    public String getWeatherFormatted(String city) {
        throw new RuntimeException("Formatted output is not implemented.");
    }

    private HttpResponse<String> getResponse(String city) {
        HttpResponse<String> response;
        try {
            response = client.send(getRequest(city), HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Syntax error in URI");
        } catch (InterruptedException e) {
            throw new RuntimeException("Request has been interrupted");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while sending or receiving");
        }
        return response;
    }

    private HttpRequest getRequest(String city)
            throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?q=" + city + "&appid=" + API_KEY))
                .GET()
                .build();
    }
}
