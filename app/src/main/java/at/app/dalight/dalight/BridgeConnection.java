package at.app.dalight.dalight;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Stephan on 09.12.2014.
 */
public class BridgeConnection extends AsyncTask<String,Void,String>{


    @Override
    protected String doInBackground(String... params) {
        //Init
        BufferedReader reader = null;
        String data = new String("");
        try {
            //Http-Client erzeugen "Browser"
            HttpClient client = new DefaultHttpClient();

            //Adresse umwandeln zu einer URI
            URI uri = new URI(params[0]);

            //Request anfordern
            HttpGet get = new HttpGet(uri);
            HttpResponse request = client.execute(get);
            Log.println(Log.DEBUG,"HTTPClient", "Request: -->" + get.getRequestLine());

            //InputStream anlegen und umwandeln zu einem "BufferedReader"
            InputStream in = request.getEntity().getContent();
            reader = new BufferedReader(new InputStreamReader(in));

            //dieser reader muss solange ausgelesen werden bis "null" steht.


            StringBuilder buffer = new StringBuilder();
            String line = "";
            // Wir wollen nach jeder Sequenz (Line) einen Zeilenumbruch in unserem String haben)
            // String newLine = System.getProperty(line.)

            while ((line = reader.readLine()) != null ){
                buffer.append(line);
            }
            reader.close();

            data = buffer.toString();
            Log.println(Log.DEBUG,"HTTPClient", "Answere: <-- " + data);
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (Exception e){
                    //TODO Exception abfangen
                }
            }
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
