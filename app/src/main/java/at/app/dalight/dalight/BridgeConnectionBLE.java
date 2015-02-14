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
                Log.d(TAG, "[" + currentDateTimeString + "] TX[byte]: " + value.toString());
                Log.d(TAG, "[" + currentDateTimeString + "] TX[byteLength]: " + value.length);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this,R.string.ble_not_connected, Toast.LENGTH_SHORT).show();
        }
    }

    private void send(byte[] telegram){

        //send data to service
        mService.writeRXCharacteristic(telegram);
        //Update the log with time stamp
        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
        Log.d(TAG, "[" + currentDateTimeString + "] TX[byte]: " + telegram.toString());

    }

    public void sendCmdAdr(String cmd, int adress, int DAPC){
        //TODO: umbauen auf sauber nur für die Tests
        String sadress;
        Log.d(TAG, "Adresse: " + adress + "CMD: " + cmd);
        if (adress <= 15) sadress = "0" + Integer.toHexString((adress <<1) + DAPC);
        else sadress = Integer.toHexString(adress << + DAPC);

        String message = sadress + cmd;
        sendText(message);
    }

    public void sendCmdGrp(String cmd, int group, int DAPC){
        String message = Integer.toHexString((DaliCommands.BROADCAST_ADRESS <<1 ) + DAPC) + cmd;
        sendText(message);
    }

    public void sendBroadcast(String cmd, int DAPC){
        String message = Integer.toHexString((DaliCommands.BROADCAST_ADRESS + DAPC)) + cmd;
        sendText(message);
    }

    public ArrayList<Device> scan(){
        return null;
    }




}
