package at.app.dalight.dalight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    //Layout Elements
    private FloatingActionButton addButton;
    private FloatingActionButton connectButton;
    private Boolean mode;

    //List view: {views: items.xml}
    private ListView deviceListView;

    //Extended ListView
    private List<Device> myDevices = new ArrayList<Device>();

    //Connection
    private BridgeConnection test;

    Context context;

    //Standard Methods -----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        deviceListView = (ListView) findViewById(R.id.listViewMain);

        addButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_add))
                .withButtonColor(getResources().getColor(R.color.Orange))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClicked(v);
            }
        });

        connectButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_play))
                .withButtonColor(getResources().getColor(R.color.Orange))
                .withGravity(Gravity.BOTTOM | Gravity.LEFT)
                .withMargins(16, 0, 0, 16)
                .create();
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectButtonClicked(v);
            }
        });



        populateDeviceList(); //ArrayList fill up
        populateListViewDevice(); //Fill up ListView with the ArrayList
        deviceListClick(); // onclick Listener für die ListView

        //ToDo  nur zum testen muss durch einen eigenen Service ausgetauscht werden
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //END of Standard Methods ----------------------------------------------------------------------

    //Test -----------------------------------------------------------------------------------------
    private void readStream(InputStream in) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                //wird nur als Log Eintrag ausgegeben

                //System.out.println(line);
                Log.e("READ Stream",line);
                //Todo muss als String zurückgegeben werden und weiterverarbeitet
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void connectButtonClicked(View v) {
        test = new BridgeConnection();
        test.execute("http://192.168.0.200/hallihallo" + getResources());
    }
    //END Test -------------------------------------------------------------------------------------

    private void populateListViewDevice() {
        ArrayAdapter<Device> adapter = new DevicesListAdapter();
        deviceListView.setAdapter(adapter);
    }
    private void populateDeviceList() {
        myDevices.add(new Device("DALI 1", R.drawable.ic_lamp_2,15,"LED"));
        myDevices.add(new Device("DALI 2", R.drawable.ic_lamp_2,16));
        myDevices.add(new Device("DALI 3", R.drawable.ic_lamp_2,1,"Typ1"));
        myDevices.add(new Device("DALI 4", R.drawable.ic_lamp_2,10,"Typ1"));
        myDevices.add(new Device("DALI 5", R.drawable.ic_lamp_2,2));
        myDevices.add(new Device("DALI 6", R.drawable.ic_lamp_2,4));
        myDevices.add(new Device("DALI 7", R.drawable.ic_lamp_2,11));
        myDevices.add(new Device("DALI 8", R.drawable.ic_lamp_2,0));

    }

    //Floating Button ********************************************
    private void addButtonClicked(View v){

        myDevices.add(new Device("Check this out", R.drawable.ic_lamp_2,15,"Hero!"));
        populateListViewDevice();
        /*
        if (!mode){
            addButton.setFloatingActionButtonDrawable(getResources().getDrawable(R.drawable.ic_play));
            Toast.makeText(getApplicationContext(),"Der Play Modus ist aktiv!", Toast.LENGTH_SHORT).show();
            mode = true;
        }
        else{
            addButton.setFloatingActionButtonDrawable(getResources().getDrawable(R.drawable.ic_add));
            Toast.makeText(getApplicationContext(),"Der Add Modus ist aktiv!", Toast.LENGTH_SHORT).show();
            mode = false;
        }*/
    }
    //Click on Device *************************************************
    private void deviceListClick() {
        deviceListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteDeviceFromList(position);
                return true; // return true, otherwise the second click Listener will be started
            }
        });

        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Device clickedDevive = myDevices.get(position);
                //String message = "You Clicked " + position + " which ist string " + clickedDevive.getName();
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();


                //Wechsel in die Device Activity
                Intent intent = new Intent(MainActivity.this, DeviceActivit.class);

                //Daten anhängen
                intent.putExtra("ClickedDevice", clickedDevive.getName());
                intent.putExtra("Type", clickedDevive.getType());
                intent.putExtra("Adress", clickedDevive.getAdress());
                intent.putExtra("extra", clickedDevive.getIconId());
                startActivity(intent);
            }
        });
    }
    private void deleteDeviceFromList(final int position){
        //Show Message Box to be sure that the Device will be deleted
        Device clickedDevice = myDevices.get(position);
        new AlertDialog.Builder(this)
                .setMessage("Are you sure youz want deleting this Device: " + clickedDevice.getName() + " ?" )
                .setTitle("Delete Device")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        myDevices.remove(position);
                        populateListViewDevice();
                    }
                })
                .setNegativeButton("No", null)//Do nothing on no
                .show();
    }

    //Helper Class----------------------------------------------------------------------------------
    private class DevicesListAdapter extends ArrayAdapter<Device> {
        public DevicesListAdapter(){
            super(MainActivity.this ,R.layout.item_device,myDevices );
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure we have a view to work with
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_device, parent,false);
            }
            //Find the Device to Work with
            Device currentDevice = myDevices.get(position);

            //Fill the View
            ImageView imageView = (ImageView) itemView.findViewById(R.id.itemImage);
            imageView.setImageResource(currentDevice.getIconId());

            //Name
            TextView nameText = (TextView) itemView.findViewById(R.id.txtName);
            nameText.setText(currentDevice.getName());

            //Adress
            TextView adressText = (TextView) itemView.findViewById(R.id.txtAdress);
            adressText.setText("Adress: " + currentDevice.getAdress()); //returnValue is a integer

            //Type
            TextView typeText = (TextView) itemView.findViewById(R.id.txtType);
            typeText.setText("Type: " + currentDevice.getType()); //returnValue is a integer

            return itemView;
            //return super.getView(position, convertView, parent);
        }
    }

}
