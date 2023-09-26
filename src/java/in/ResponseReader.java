package in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ResponseReader {
    public static String read(HttpURLConnection connection) {
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = responseReader.readLine()) != null)
                response.append(line);
        } catch (IOException ex) {
            System.out.println("Error occurred while read response.");
            System.exit(1);
        }
        return response.toString();
    }
}
