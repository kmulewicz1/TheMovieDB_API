package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

public class A {
    private static final String api = "554d5e56831881e0cbbc3aea1cf89f86";
    private static Scanner sc = new Scanner(System.in);

    public static JSONObject init(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

            String output = br.readLine();

            return  new JSONObject(output);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static JSONArray bestmovies() {
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key="+api+"&language=en-US&page=1");


            return  (JSONArray) init(url).get("results");

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void choosemovie(JSONArray tmp) throws Exception
    {
        JSONObject jsonObject1;
        System.out.println("Number of results " + tmp.length() + ": ");
        if (tmp.length() != 0) {
            for (int i = 0; i < tmp.length(); i++) {
                jsonObject1 = tmp.getJSONObject(i);
                System.out.println(i + 1 + ": " + jsonObject1.get("title")+" data: "+jsonObject1.get("release_date"));
            }

        }//liczba wynikow >0
        else {
            System.out.println("Sorry, this movie does not exist in our database.");
        }//brak wynikow

    }

    public static void properties(JSONArray jsonArray, int index) throws Exception
    {
        JSONObject jsonObject1 = jsonArray.getJSONObject(index - 1);
        System.out.println("Title: " + jsonObject1.get("title"));
        System.out.println("Release date: " + jsonObject1.get("release_date"));
        System.out.println("Vote averange: " + jsonObject1.get("vote_average") + " based on " + jsonObject1.get("vote_count") + " votes");
        System.out.println("Overview: " + jsonObject1.get("overview"));
        System.out.println();
    }

    public static JSONArray find(String tosearch){

        tosearch=tosearch.replaceAll("\\s","%20");

        try {
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + api +
                    "&language=en-US&query=" + tosearch + "&page=1&include_adult=false");
             return  (JSONArray) init(url).get("results");

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
}