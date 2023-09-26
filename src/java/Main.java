import in.KeyboardReader;

public class Main {

    public static final String API_KEY = "5692607afa77925828f95c1376b62cc8";

    public static void main(String[] args) {
        System.out.println("Enter city name: ");
        String city = new KeyboardReader().getCityName();

        Connector connector = new Connector();
        String cityInfo = connector.getResponse(city, API_KEY);
        System.out.println("Information about city: " + cityInfo);
    }
}
