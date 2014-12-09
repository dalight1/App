package at.app.dalight.dalight;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DeviceActivit extends Activity implements View.OnClickListener{

    // Definitions
    public Button btnback1;
    public TextView TextTypeName;
    public TextView TextAdressName;
    public TextView MainText;

    //ListView
    private ListView deviceListView_Actions;
    //Extended ListView
    public List<Device> myDevices = new ArrayList<Device>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        //Create
        TextTypeName = (TextView) findViewById(R.id.textType);
        TextAdressName = (TextView) findViewById(R.id.textAdress);
        MainText = (TextView) findViewById(R.id.MainText);
        deviceListView_Actions = (ListView) findViewById(R.id.listView_DeviceActions);


        btnback1 = (Button) findViewById(R.id.btnback);
        btnback1.setOnClickListener(this);

        settheText();   //setzen der Texte, die mit übergeben wurden


    }


    public void settheText()
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


    @Override
    public void onClick(View v) {

        /* just for debugg
        String message = "Sollte geschlossen werden! ";
        Toast.makeText(DeviceActivit.this, message,Toast.LENGTH_SHORT).show();
        */

        //welcher Button wurde gedrückt??
        switch (v.getId()){

            case    R.id.btnback:{
                finish();   //Schließen der aktuellen aktivity
            }

        }

    }


    //ListView Class---------------------------------------------------------------------------------
    private class DevicesListAdapter extends ArrayAdapter<Device> {
        public DevicesListAdapter() {
            super(DeviceActivit.this, R.layout.item_device, myDevices);
        }


    }
}
