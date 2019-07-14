package api;


import org.json.JSONArray;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws  Exception {
        Scanner sc = new Scanner(System.in);
        String tosearch;
        System.out.println("What you want to do? search movie / chceck top rated movies");
        String action = sc.nextLine();
        switch (action) {
            case "search movie":

            System.out.println("Enter the title of the movie.");
            tosearch = sc.nextLine();
            JSONArray jsonArray = A.find(tosearch);
            A.choosemovie(jsonArray);
            System.out.println("Choose the number of the movie you meant.");
            A.properties(jsonArray, sc.nextInt());
            break;

            case "chceck top rated movies":
                JSONArray jsonArray1=A.bestmovies();
                System.out.println(jsonArray1);
                A.choosemovie(jsonArray1);
                System.out.println("Choose the number of the movie you meant.");
                A.properties(jsonArray1,sc.nextInt());
                break;



        }
    }
}
