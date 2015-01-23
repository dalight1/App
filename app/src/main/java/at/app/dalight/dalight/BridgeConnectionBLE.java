package at.app.dalight.dalight;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Stephan on 10.01.2015.
 */
public class BridgeConnectionBLE  extends Application {


    public UartService mService;
    public static final String TAG = "Bridge Connection BLE";

    public BridgeConnectionBLE() {

        super.onCreate();
    }

    public void sendText(String text){

        if (mService.isConnected()) {
            byte[] value;
            try {
                //send data to service
                value = text.getBytes("UTF-8");
                mService.writeRXCharacteristic(value);
                //Update the log with time stamp
                String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                Log.d(TAG, "[" + currentDateTimeString + "] TX: " + text);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this,R.string.ble_not_connected, Toast.LENGTH_SHORT).show();
        }
    }

    public void sendCMD(String cmd, int adress){
        String message = Integer.toHexString(adress) + cmd;
        sendText(message);
    }

    public ArrayList<Device> scan(){
        return null;
    }




}
