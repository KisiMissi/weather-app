import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionBuilder {
    public HttpURLConnection getConnection(String apiUrl) {
        HttpURLConnection connection = setHttpConnection(apiUrl);
        if (connection == null) {
            System.out.println("Connection failed.");
            System.exit(1);
        }
        return connection;
    }

    private HttpURLConnection setHttpConnection(String apiUrl) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            if (! (connection.getResponseCode() == HttpURLConnection.HTTP_OK))
                throw new IOException();
        } catch (IOException ex) {
            System.out.println("Error occurred connecting to the server.");
            System.exit(1);
        }
        return connection;
    }
}
