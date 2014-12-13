package at.app.dalight.dalight;

import java.util.ArrayList;

/**
 * Created by Stephan on 09.11.2014.
 * This class represents one Device at the DALI Bus
 */
public class Device {

    private String name;
    private int iconId;
    private int adress;
    private boolean[] group;
    private boolean[] scene;
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

    public ArrayList<Integer> getGroups(){
        ArrayList<Integer> usedGroups = new ArrayList();
        for (int groupIdx = 0;groupIdx < this.group.length;groupIdx++){
            if (this.group[groupIdx]) usedGroups.add(groupIdx);
        }
        return usedGroups;
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
