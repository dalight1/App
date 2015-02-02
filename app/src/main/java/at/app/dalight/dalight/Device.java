package at.app.dalight.dalight;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stephan on 09.11.2014.
 * This class represents one Device at the DALI Bus
 */
public class Device implements Serializable //Added implements Serializable
{

    private String name;
    private int iconId;


    private int adress;
    private boolean[] group;
    private Scene[] scene;
    private String type;

    //TODO IllegalArgumentException bei den Settern

    public Device(String name, int iconId, int adress, String type) {
        this.name = name;
        this.iconId = iconId;
        this.setAdress(adress);
        this.type = type;

        group = new boolean[16];
        scene = new Scene[16];
    }

       public Device(String name, int iconId, int adress) {
        this.name = name;
        this.iconId = iconId;
        this.adress = adress;
        this.type = "invalid Type";

        group = new boolean[16];
        scene = new Scene[16];
    }

    //Methode zum überprüfen ob ein Device in dieser Gruppe ist
    public boolean isInGroup(int groupNr){
        return group[groupNr];
    }

    //Gibt die Idx der Gruppen in der sich das Device befindet zurück
    public ArrayList<Integer> getGroups(){
        ArrayList<Integer> usedGroups = new ArrayList();
        for (int groupIdx = 0;groupIdx < this.group.length;groupIdx++){
            if (this.group[groupIdx]) usedGroups.add(groupIdx);
        }
        return usedGroups;
    }

    //Scene Value für eine bestiummte Szene ausgeben
    public int getSceneValue(int sceneNr){return this.scene[sceneNr].getValue();}

    //Getter and Setter-Methods --------------------------------------------------------------------
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

    public void setAdress(int adress) {
        if(adress <= 64)this.adress = adress;
        else throw new IllegalArgumentException("Adress must be <= 64");
    }


    public boolean[] getGroup() {
        return group;
    }

    public void setGroup(boolean[] group) {
        this.group = group;
    }
    public void setGroup(int groupNr){
        this.group[groupNr] = true;
    }
    public void setGroup(ArrayList<Integer> groups){
        //in diese Methode werden die Gruppen als Hex Zahl im Format String übergeben
        for (int Idx = 0;Idx < groups.size();Idx++) {
            this.group[groups.get(Idx)] = true;
        }
    }

    public Scene[] getScene() {
        return scene;
    }

    public void setScene(Scene[] scene) {
        this.scene = scene;
    }
    public void setScene(int sceneNr, int value) {
        this.scene[sceneNr].activ = true;
        this.scene[sceneNr].setValue(value);
    }

}

class Scene {
    boolean activ = false;
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 255 & value >= 0){
            this.value = value;
        }
    }


}

