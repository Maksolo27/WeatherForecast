import JsonObjs.JsonWeather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.istack.internal.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

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
public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args)  throws IOException  {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }catch(TelegramApiException e) {
            e.printStackTrace();
        }
        String apiKey = getApiKey("C:\\Users\\maxim\\IdeaProjects\\api.txt");
        System.out.println("Введите страну");
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        System.out.println("Введите город");
        scanner = new Scanner(System.in);
        String cityName = scanner.nextLine();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ cityName +"," + country + "&appid="+apiKey ;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = proccesRequest(url);
        JsonWeather jsonWeather = gson.fromJson(json, JsonWeather.class);
        System.out.println(jsonWeather);

    }

    @NotNull
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

    @NotNull
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

    public String getBotUsername() {
        return "MaksymWeatherBot";
    }

    public String getBotToken() {
        StringBuilder builder = new StringBuilder();
        try {
            FileReader fr = new FileReader("C:\\Users\\maxim\\IdeaProjects\\WeatherBotToken");
            int i;
            while((i=fr.read())!=-1)
                builder.append((char)i);
            fr.close();
        }catch (IOException e ){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void onUpdateReceived(Update update) {

    }
}
