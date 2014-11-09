package at.app.dalight.dalight;

/**
 * Created by Stephan on 09.11.2014.
 */
public class Device {

    private String name;
    private int id;
    private int adress;

    public String getType() {
        return type;
    }

    private String type;


    public Device(String name, int id, int adress, String type) {
        this.name = name;
        this.id = id;
        this.adress = adress;
        this.type = type;
    }

    public Device(String name, int id, int adress) {
        this.name = name;
        this.id = id;
        this.adress = adress;
        this.type = "invalid Type";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAdress() {
        return adress;
    }

}
