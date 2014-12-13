package at.app.dalight.dalight;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stephan on 09.11.2014.
 * This class represents one Device at the DALI Bus
 */
public class Device implements Parcelable{

    private String name;
    private int iconId;
    private int adress;
    private String type;


    public Device(String name, int iconId, int adress, String type) {
        this.name = name;
        this.iconId = iconId;
        this.adress = adress;
        this.type = type;
    }

    public Device(Parcel parcel) {
        this.name = parcel.readString();
        this.iconId = parcel.readInt();
        this.adress = parcel.readInt();
        this.type = parcel.readString();
    }


    public Device(String name, int iconId, int adress) {
        this.name = name;
        this.iconId = iconId;
        this.adress = adress;
        this.type = "invalid Type";
    }

    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }

    public int getAdress() {
        return adress;
    }

    public String getType() {
        return type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(iconId);
        dest.writeInt(adress);
        dest.writeString(type);
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel source) {
            return new Device(source);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];

        }
    };
}


