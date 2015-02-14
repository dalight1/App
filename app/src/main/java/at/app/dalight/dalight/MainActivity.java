package at.app.dalight.dalight;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {

    //Layout Elements
    private FloatingActionButton mainButton;
    private FloatingActionButton DisconnectButton;

    //List view: {views: items.xml}
    private ListView deviceListView;

    //Spinner für die Auswahl der Selection
    private Spinner filter_devices;

    //Extended ListView
    private List<Device> myDevices = new ArrayList<Device>();

    //Connection

    //BLE Components
    private static final int REQUEST_SELECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    public static final String TAG = "DALight-App";
    private static final int UART_PROFILE_CONNECTED = 20;
    private static final int UART_PROFILE_DISCONNECTED = 21;

    private int mState = UART_PROFILE_DISCONNECTED;
    private BluetoothDevice mDevice = null;
    private BluetoothAdapter mBtAdapter = null;
    private BridgeConnectionBLE bridgeConnection;
    private boolean onOff = false;


    Context context;

    //Standard Methods -----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        initializeLayoutElements();

        //check if Bluetooth is available
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        populateDeviceList(); //ArrayList fill up
        populateListViewDevice(); //Fill up ListView with the ArrayList
        deviceListClick(); // onclick Listener für die ListView
        service_init();
        //addItemToFilterSpinner();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_SELECT_DEVICE:
                //When the DeviceListActivity return, with the selected device address
                if (resultCode == Activity.RESULT_OK && data != null) {
                    String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                    mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);

                    Log.d(TAG, "... onActivityResultdevice.address==" + mDevice + "mserviceValue" + bridgeConnection.mService);
                    ((TextView) findViewById(R.id.listHeader)).setText(mDevice.getName()+ " - connecting");
                    bridgeConnection.mService.connect(deviceAddress);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();

                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                Log.e(TAG, "wrong request code");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (!mBtAdapter.isEnabled()) {
            Log.i(TAG, "onResume - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");

        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(UARTStatusChangeReceiver);
        } catch (Exception ignore) {
            Log.e(TAG, ignore.toString());
        }
        unbindService(mServiceConnection);
        bridgeConnection.mService.stopSelf();
        bridgeConnection.mService= null;

    }

    @Override
    public void onBackPressed() {
        if (mState == UART_PROFILE_CONNECTED) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
        else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.popup_title)
                    .setMessage(R.string.popup_message)
                    .setPositiveButton(R.string.popup_yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.popup_no, null)
                    .show();
        }
    }

    //Init Methods -----------------------------------------------------------------------------
    private void addItemToFilterSpinner() {
        ArrayAdapter<CharSequence> filterSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.selection_group,
                android.R.layout.simple_spinner_dropdown_item);
        filterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filter_devices.setAdapter(filterSpinnerAdapter);
        addListenerToFilterSpinner();
    }

    public void addListenerToFilterSpinner(){
        filter_devices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initializeLayoutElements() {
        // in dieser Funktion werden alle Elemente der GUI initialisiert und referenziert

        deviceListView = (ListView) findViewById(R.id.listViewMain);
        filter_devices = (Spinner) findViewById(R.id.sp_filter_devices);

        //Main Button erzeugen
        mainButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_ble_new))
                .withButtonColor(getResources().getColor(R.color.Orange))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainButtonClicked(v);
            }
        });

        //Disconnect Button erzeugen
        DisconnectButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_ble_disconnect))
                .withButtonColor(getResources().getColor(R.color.Orange))
                .withGravity(Gravity.TOP | Gravity.LEFT)
                .withMargins(16, 16, 0, 0)
                .withButtonSize(50)
                .create();
        DisconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { disconnectButtonClicked(v);
            }
        });
        DisconnectButton.setVisibility(View.INVISIBLE);
    }


    private void service_init() {
        bridgeConnection = (BridgeConnectionBLE)getApplicationContext();
        Intent bindIntent = new Intent(this, UartService.class);
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(this).registerReceiver(UARTStatusChangeReceiver, makeGattUpdateIntentFilter());
    }
    //END of Standard Methods ----------------------------------------------------------------------


    private void populateListViewDevice() {
        ArrayAdapter<Device> adapter = new DevicesListAdapter();
        deviceListView.setAdapter(adapter);
    }
    private void populateDeviceList() {
        for(int i=0; i<64; i++){
            myDevices.add(new Device("Device "+ i, R.drawable.ic_lamp_2,i,"LED-Test"));
        }

    }
    private void setHeader(String newText){
        ((TextView) findViewById(R.id.listHeader)).setText(newText);
    }

    //Floating Button Clicked Methodes**************************************************************
    private void mainButtonClicked(View v){
        if (!mBtAdapter.isEnabled()) {
            Log.i(TAG, "onClick - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
        else {
            if (!bridgeConnection.mService.isConnected()){
                //Connect button pressed, open DeviceListActivity class, with popup windows that scan for devices

                Intent newIntent = new Intent(MainActivity.this, DeviceListActivityBLE.class);
                startActivityForResult(newIntent, REQUEST_SELECT_DEVICE);

            } else {
                bridgeConnection.scan();
                if (onOff) {
                    bridgeConnection.sendBroadcast("fe",DaliCommands.DAPC_ON);
                    onOff = false;
                }
                else{
                    bridgeConnection.sendBroadcast("00",DaliCommands.DAPC_OFF);
                    onOff = true;
                }
            }
        }

    }

    private void disconnectButtonClicked(View v){
        //Disconnect button pressed
        if (mDevice!=null)
        {
            bridgeConnection.mService.disconnect();
        }
    }

    //Click on Device ******************************************************************************
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

                //Wechsel in die Device Activity
                Intent intent = new Intent(MainActivity.this, DeviceActivity.class);
                DeviceActivity.selectedDevice = clickedDevive;
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

    //Connection ***********************************************************************************

    //UART service connected/disconnected
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder rawBinder) {
            bridgeConnection.mService = ((UartService.LocalBinder) rawBinder).getService();
            Log.d(TAG, "onServiceConnected mService= " + bridgeConnection.mService);
            if (!bridgeConnection.mService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
        }

        public void onServiceDisconnected(ComponentName classname) {
            ////     mService.disconnect(mDevice);
            bridgeConnection.mService = null;
        }
    };

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UartService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(UartService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(UartService.DEVICE_DOES_NOT_SUPPORT_UART);
        return intentFilter;
    }

    private final BroadcastReceiver UARTStatusChangeReceiver = new BroadcastReceiver() {

        public void onReceive(final Context context, Intent intent) {
            String action = intent.getAction();

            final Intent mIntent = intent;
            //*********************//
            if (action.equals(UartService.ACTION_GATT_CONNECTED)) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        Log.d(TAG, "UART_CONNECT_MSG");
                        mainButton.setFloatingActionButtonDrawable(getResources()
                                .getDrawable(R.drawable.ic_on_off));
                        DisconnectButton.setVisibility(View.VISIBLE);
                        setHeader("Connected to " + mDevice.getName());
                        //populateDeviceList(); //ArrayList fill up
                        mState = UART_PROFILE_CONNECTED;
                    }
                });
            }

            //*********************//
            if (action.equals(UartService.ACTION_GATT_DISCONNECTED)) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        Log.d(TAG, "UART_DISCONNECT_MSG");
                        mainButton.setFloatingActionButtonDrawable(getResources().getDrawable(R.drawable.ic_ble_new));
                        DisconnectButton.setVisibility(View.INVISIBLE);
                        setHeader("Not Connected");
                        Toast.makeText(context,"Disconnected: "+ mDevice.getName(), Toast.LENGTH_LONG).show();
                        mState = UART_PROFILE_DISCONNECTED;
                        bridgeConnection.mService.close();
                    }
                });
            }


            //*********************//
            if (action.equals(UartService.ACTION_GATT_SERVICES_DISCOVERED)) {
                bridgeConnection.mService.enableTXNotification();
            }
            //*********************//
            if (action.equals(UartService.ACTION_DATA_AVAILABLE)) {

                final byte[] txValue = intent.getByteArrayExtra(UartService.EXTRA_DATA);
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            String text = new String(txValue, "UTF-8");
                            String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                            Toast.makeText(context,"["+currentDateTimeString+"] RX: "+ text, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                });
            }
            //*********************//
            if (action.equals(UartService.DEVICE_DOES_NOT_SUPPORT_UART)){
                Toast.makeText(context,"Device doesn't support UART. Disconnecting", Toast.LENGTH_LONG).show();
                bridgeConnection.mService.disconnect();
            }
        }
    };


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
