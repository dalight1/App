package at.app.dalight.dalight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    //Layout Elements
    private FloatingActionButton addButton;
    private Boolean mode;

    //List view: {views: items.xml}
    private ListView list;

    //Extended ListView
    private List<Device> myDevices = new ArrayList<Device>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listViewMain);

        addButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_add))
                .withButtonColor(getResources().getColor(R.color.Orange))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoIt(v);
            }
        });
        mode = false;

        populateListView();
        registerClickCallback();

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

    public void DoIt(View v){
        if (!mode){
            addButton.setFloatingActionButtonDrawable(getResources().getDrawable(R.drawable.ic_play));
            Toast.makeText(getApplicationContext(),"Der Play Modus ist aktiv!", Toast.LENGTH_SHORT).show();
            mode = true;
        }
        else{
            addButton.setFloatingActionButtonDrawable(getResources().getDrawable(R.drawable.ic_add));
            Toast.makeText(getApplicationContext(),"Der Add Modus ist aktiv!", Toast.LENGTH_SHORT).show();
            mode = false;
        }
    }



    private void populateListView(){
        //Create a List of Items
        String[] myitems = {"eins", "zwei", "drei"};

        //Build Adapter

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,           //Context for the activity
                R.layout.items, //Layout to use (create)
                myitems);       //Items to be displayed

        //Configure the ListView
        //ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);

    }
    private void registerClickCallback() {
        //ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String message = "You Clicked " +
                        position +
                        " which ist string " +
                        textView.getText().toString();
                Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
