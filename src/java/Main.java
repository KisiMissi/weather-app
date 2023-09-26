import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        // Ваш API-ключ OpenWeatherMap
        String apiKey = "5692607afa77925828f95c1376b62cc8";

        // Чтение города с клавиатуры
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите название города: ");
            String city = reader.readLine();

            // Создание URL для запроса к API
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            // Отправка запроса и получение ответа
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Чтение ответа
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = responseReader.readLine()) != null) {
                    response.append(line);
                }
                responseReader.close();

                // Вывод информации о погоде
                System.out.println("Информация о погоде для города " + city + ":");
                System.out.println(response.toString());
            } else {
                System.out.println("Ошибка при отправке запроса. Код ответа: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
