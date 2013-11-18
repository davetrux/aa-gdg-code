package com.devfest.starter;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebHelper {

    public static boolean isOnline(Context ctx){
        ConnectivityManager manager =  (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * Performs a GET request on a URL
     * @param url The URL
     * @return THe result as a string
     * @throws IOException
     */
    public String getHttp(String url) throws IOException {
        return getHttp(url, "GET", "");
    }

    /**
     * A more verbose version of the function to handle both POST and GET
     * @param url The resource URL
     * @param method GET or POST
     * @param input HTTP body for posting
     * @return the result as a string
     * @throws IOException
     */
    public String getHttp(String url, String method, String input) throws IOException {

        OutputStream os = null;
        BufferedReader in = null;

        try {
            final URL networkUrl = new URL(url);
            final HttpURLConnection conn = (HttpURLConnection) networkUrl.openConnection();
            conn.setRequestMethod(method);

            if (!TextUtils.isEmpty(input)) {
                //Create HTTP Headers for the content length and type
                conn.setFixedLengthStreamingMode(input.getBytes().length);
                conn.setRequestProperty("Content-Type", "application/json");
                //Place the input data into the connection
                conn.setDoOutput(true);
                os = new BufferedOutputStream(conn.getOutputStream());
                os.write(input.getBytes());
                //clean up
                os.flush();
            }

            final InputStream inputFromServer = conn.getInputStream();

            in = new BufferedReader(new InputStreamReader(inputFromServer));
            String inputLine;
            StringBuffer json = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }

            return json.toString();

        } catch (Exception ex) {
            //Really should log something here
            return "";
        } finally {
            //clean up
            if (in != null) {
                in.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }
}
