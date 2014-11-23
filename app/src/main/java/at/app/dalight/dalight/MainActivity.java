package at.app.dalight.dalight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    //Layout Elements
    private FloatingActionButton addButton;
    private Boolean mode;

    //List view: {views: items.xml}
    private ListView deviceListView;

    //Extended ListView
    private List<Device> myDevices = new ArrayList<Device>();

    //Standard Methods -----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        populateDeviceList(); //ArrayList fill up
        populateListViewDevice(); //Fill up ListView with the ArrayList
        deviceListClick(); // onclick Listener für die ListView

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

    private void populateListViewDevice() {
        ArrayAdapter<Device> adapter = new DevicesListAdapter();
        deviceListView.setAdapter(adapter);
    }
    private void populateDeviceList() {
        myDevices.add(new Device("Name1", R.drawable.ic_launcher,15,"Typ1"));
        myDevices.add(new Device("Name2", R.drawable.ic_launcher,16));
        myDevices.add(new Device("Name3", R.drawable.ic_launcher,1,"Typ1"));
        myDevices.add(new Device("Name4", R.drawable.ic_launcher,10,"Typ1"));
        myDevices.add(new Device("Name5", R.drawable.ic_launcher,2));
        myDevices.add(new Device("Name6", R.drawable.ic_launcher,4));
        myDevices.add(new Device("Name7", R.drawable.ic_launcher,11));
        myDevices.add(new Device("Name8", R.drawable.ic_launcher,0));
    }
    private void addButtonClicked(View v){

        myDevices.add(new Device("Check this out", R.drawable.ic_launcher,15,"Hero!"));
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
                /*
                Device clickedDevive = myDevices.get(position);
                String message = "You Clicked " + position + " which ist string " + clickedDevive.getName();
                Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();*/

                //Wechsel in die Edit Activity
                Intent intent = new Intent(MainActivity.this, DeviceActivit.class);
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
            //Make shure we have a view to work with
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
