import in.KeyboardReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter city name: ");
        String city = new KeyboardReader().getCityName();

        ConnectorToOWM connector = new ConnectorToOWM();
        System.out.println("Unformatted output:");
        System.out.println(connector.getWeatherUnformatted(city));
        System.out.println("Formatted output:");
        System.out.println(connector.getWeatherFormatted(city));
    }
}
