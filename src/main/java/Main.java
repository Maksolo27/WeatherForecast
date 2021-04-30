import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by maxim on 30.04.2021.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String apiKey = getApiKey("C:\\Users\\maxim\\IdeaProjects\\api.txt");
        System.out.println("Введите город");
        Scanner scanner = new Scanner(System.in);
        String cityName = scanner.nextLine();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ cityName +",GBR&appid="+apiKey ;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = proccesRequest(url);
        System.out.println(json);

    }

    private static String proccesRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder stringBuilder = new StringBuilder();

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    private static String getApiKey(String way) {
        StringBuilder builder = new StringBuilder();
        try {
            FileReader fr=new FileReader(way);
            int i;
            while((i=fr.read())!=-1)
                builder.append((char)i);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
