package at.app.dalight.dalight;

/**
 * Created by Stephan on 09.11.2014.
 */
public class Device {

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

}
