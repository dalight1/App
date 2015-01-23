package at.app.dalight.dalight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class DeviceActivity extends Activity implements View.OnClickListener{

    // Definitions
    public Button btnback1;
    public Button btnInit;
    public TextView TextTypeName;
    public TextView TextAdressName;
    public TextView MainText;

    //ListView
    private ListView deviceListView_Actions;
    //Extended ListView
    private ListView deviceListView_Controll;
    public List<Device> myDevices = new ArrayList<Device>();
    private FloatingActionButton TESTsendButton;

    private BridgeConnectionBLE bridgeConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        bridgeConnection = (BridgeConnectionBLE)getApplicationContext();
        //Tab Host Create **************************************************
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost_Device);

        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab_generall");
        tabSpec.setContent(R.id.tab_generall);
        tabSpec.setIndicator("general");
        tabHost.addTab(tabSpec);

        tabHost.newTabSpec("tab_group");
        tabSpec.setContent(R.id.tab_group);
        tabSpec.setIndicator("group");
        tabHost.addTab(tabSpec);

        tabHost.newTabSpec("tab_scene");
        tabSpec.setContent(R.id.tab_scene);
        tabSpec.setIndicator("scene");
        tabHost.addTab(tabSpec);

        //Create
        TextTypeName = (TextView) findViewById(R.id.textType);
        TextAdressName = (TextView) findViewById(R.id.textAdress);
        MainText = (TextView) findViewById(R.id.MainText);


        deviceListView_Actions = (ListView) findViewById(R.id.listView_DeviceActions);
        //Setzen der Daten und Aufrufen der Listwiew
        populateDeviceList();
        populateListViewDevice();


        btnback1 = (Button) findViewById(R.id.btnback);
        btnback1.setOnClickListener(this);


        //setzen der Texte, die mitübergeben wurden
        setTexts();

        TESTsendButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_lamp_1))
                .withButtonColor(getResources().getColor(R.color.White))
                .withGravity(Gravity.TOP | Gravity.RIGHT)
                .withMargins(0, 16, 16, 0)
                .create();

        TESTsendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText editText = (EditText) findViewById(R.id.sendText);
                ((BridgeConnectionBLE) getApplicationContext()).sendCMD(DaliCommands.OFF,1);
            }
        });
    }


    public void setTexts()
    {
        //Übergebene Daten ausgeben *********************************
        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            MainText.setText(extras.getString("ClickedDevice"));
            TextTypeName.setText("Device type: " + extras.getString("Type"));
            TextAdressName.setText("Device adress: " + extras.getInt("Adress"));
        }

    }
    /*  Wenn dieses Scheißdrecks-Ding aktiv ist, dann bleibt man da hängen---> geht nix
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device, menu);
        setContentView(R.layout.activity_device);
        return true;
    }*/

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

        //Click Listener **********************************************
    @Override
    public void onClick(View v) {

        /* just for debugg
        String message = "Sollte geschlossen werden! ";
        Toast.makeText(DeviceActivity.this, message,Toast.LENGTH_SHORT).show();
        */

        //welcher Button wurde gedrückt??
        switch (v.getId()){

            case    R.id.btnback:{
                finish();   //Schließen der aktuellen aktivity
                break;
            }

        }

    }



      //Wird nicht mehr benötigt, nur zum Nachschauen aktiv
    //ListView Class---------------------------------------------------------------------------------
    private class DevicesListAdapter extends ArrayAdapter<Device> {
        public DevicesListAdapter() {
            super(DeviceActivity.this, R.layout.item_device, myDevices);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure we have a view to work with
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_device, parent, false);
            }
            //Find the Device to Work with
            Device currentDevice = myDevices.get(position);

            //Fill the View
            ImageView imageView = (ImageView) itemView.findViewById(R.id.itemImage);
            imageView.setImageResource(currentDevice.getIconId());

            //Name
            TextView nametext = (TextView) itemView.findViewById(R.id.txtName);
            nametext.setText(currentDevice.getName());


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
    private void populateDeviceList() {
        myDevices.add(new Device("Initialize", R.drawable.ic_lamp_2,1,"LED"));
        myDevices.add(new Device("Set Adress", R.drawable.ic_lamp_2,2,"LED"));
        myDevices.add(new Device("Read Adress", R.drawable.ic_lamp_2,3,"LED"));
        myDevices.add(new Device("Config.", R.drawable.ic_lamp_2,4,"LED"));
        myDevices.add(new Device("Light on", R.drawable.ic_lamp_2,4,"LED"));


    }

    private void populateListViewDevice() {
        ArrayAdapter<Device> adapter = new DevicesListAdapter();
        deviceListView_Actions.setAdapter(adapter);
    }
}
