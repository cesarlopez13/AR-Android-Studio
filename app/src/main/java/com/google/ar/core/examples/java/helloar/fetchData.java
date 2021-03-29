package com.google.ar.core.examples.java.helloar;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    private static JSONArray JA = new JSONArray();
    String dataJson="";
    //String latitude= "";
    //String longitude="";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.npoint.io/bedfc91b42a158a57943");
            //URL url = new URL("https://valknut.dcom.upv.es:8443/orion/entities");
            //URL url = new URL("http://echo.jsontest.com/key/value/one/two");
            //URL url = new URL ("https://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";

            while(line != null){
                line=bufferedReader.readLine();
                dataJson= dataJson + line;
            }

            JA = new JSONArray(dataJson);
            /*for (int i = 0; i < JA.length(); i++) {

                    JSONObject JO = (JSONObject) JA.get(i);
                    JSONObject JOlatitude = (JSONObject) JO.get("latitude");
                    latitude = (String) JOlatitude.get("value");
                    JSONObject JOlongitude = (JSONObject) JO.get("longitude");
                    longitude = (String) JOlongitude.get("value");
            }*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
        e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        //HelloArActivity.data.setText("Hay " + JA.length() + " POIS");
        //HelloArActivity.data.setText(this.longitude);
        HelloArActivity.POIs= this.JA;
    }
}
