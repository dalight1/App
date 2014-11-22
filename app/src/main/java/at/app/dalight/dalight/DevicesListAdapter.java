package at.app.dalight.dalight;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Stephan on 22.11.2014.
 */
public class DevicesListAdapter extends ArrayAdapter<Device>{

    private List<Device> Devices;

    public DevicesListAdapter(Activity homeActivity,List<Device> myDevices){
        super(homeActivity ,R.layout.item_device, myDevices );
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Make shure we have a view to work with
        View itemView = convertView;
        if (itemView == null){
            itemView = getLayoutInflater().inflate(R.layout.item_device, parent, false);
        }
        //Find the Device to Work with
        Device currentDevice = Devices.get(position);

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
