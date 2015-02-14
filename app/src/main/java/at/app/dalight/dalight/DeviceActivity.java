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


import java.util.ArrayList;
import java.util.List;


public class DeviceActivity extends Activity implements View.OnClickListener{

    // Definitions

    public TextView TextTypeName;
    public TextView TextAdressName;
    public TextView MainText;

    public static Device selectedDevice = null;
    private boolean onOff;
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

        onOff = false;

        //setzen der Texte, die mitübergeben wurden
        setTexts();

        TESTsendButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_on_off))
                .withButtonColor(getResources().getColor(R.color.Orange))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        TESTsendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOff) {
                    ((BridgeConnectionBLE) getApplicationContext()).sendCmdAdr(DaliCommands.OFF, selectedDevice.getAdress(), DaliCommands.DAPC_OFF);
                    onOff = false;
                }else {
                    ((BridgeConnectionBLE) getApplicationContext()).sendCmdAdr("fe", selectedDevice.getAdress(), DaliCommands.DAPC_ON);
                    onOff = true;
                }
            }
        });
    }


    public void setTexts()
    {
        MainText.setText(selectedDevice.getName());
        TextTypeName.setText("Device type: " + selectedDevice.getType());
        TextAdressName.setText("Device adress: " + selectedDevice.getAdress());
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

        //Click Listener **********************************************
    @Override
    public void onClick(View v) {


        //welcher Button wurde gedrückt??
        switch (v.getId()){


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
