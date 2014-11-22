package at.app.dalight.dalight;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class EditDalight extends Activity implements View.OnClickListener {

    private Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dalight);

        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_dalight, menu);
        setContentView(R.layout.activity_edit_dalight);
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
    public void onClick(View v) {

        int ce = v.getId(); //Click Element

        String message = "Sollte geschlossen werden! ";
        Toast.makeText(EditDalight.this, message,Toast.LENGTH_SHORT).show();

        if(ce == R.id.btnback)
        {
            finish();   //Schließen der aktuellen aktivity
        }

    }
}
