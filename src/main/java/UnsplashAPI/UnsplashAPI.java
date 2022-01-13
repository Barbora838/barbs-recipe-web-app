package UnsplashAPI;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * UnsplashAPI class.
 */
public class UnsplashAPI {

    /**
     * This method sends request to Unsplash API.
     *
     * @param recipe
     * @return
     */
    public static String sendUnsplashRequest(String recipe) {

        String url = "https://api.unsplash.com/search/photos?query=" + recipe + "&client_id=t9yY_fDhsyz8_QUx-2Fh3fMQ8wvJT5PK-YIL04NeIyE";
        URL myurl = null;
        try {
            myurl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpsURLConnection con = null;
        try {
            con = (HttpsURLConnection) myurl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    /**
     * Method that returns url.
     *
     * @param res
     * @return
     */
    public static String getURL(String res) {

        String url = "";
        String[] split = res.split(",");

        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("\"download")) {
//                String[] split1 = split[i].split(":");
//                String url1 = split1[1] + ":" + split1[2];
//                String[] split2 = url1.split("=");
                url = split[i].substring(12, split[i].length() - 1);
                break;
            }
        }
        return url;

    }

    /**
     * helper method that encode Query
     *
     * @param recipe
     * @return
     */
    public static String encodeQuery(String recipe) {
        return recipe.replace(" ", "&");
    }

    /**
     * This is main class for trying out unsplash API.
     *
     * @param args
     */
    public static void main(String[] args) {

        String recipe = "Crispy Asian Chicken And Watercress Salad";
        String res = sendUnsplashRequest(encodeQuery(recipe));
        System.out.println(res);
        System.out.println(getURL(res));
        String recipe2 = "Chicken Pot Tot Hotdish";
        String res2 = sendUnsplashRequest(encodeQuery(recipe2));

        System.out.println(getURL(res2));
        String recipe3 = "Crispy Chicken Thighs With Bacon And Wilted Escarole";
        String res3 = sendUnsplashRequest(encodeQuery(recipe3));

        System.out.println(getURL(res3));
        String recipe4 = "California Chicken Salad";
        String res4 = sendUnsplashRequest(encodeQuery(recipe4));

        System.out.println(getURL(res4));

        String recipe5 = "Quick Chicken Fajitas";
        String res5 = sendUnsplashRequest(encodeQuery(recipe5));

        System.out.println(getURL(res5));
        String recipe6 = "Warm Shredded Chicken Salad With Mango Chile Salsa";
        String res6 = sendUnsplashRequest(encodeQuery(recipe6));

        System.out.println(getURL(res6));
    }
}
