package BeatBox.Serialization;

import java.io.Serializable;

public class ObjectToSave implements Serializable {
    String name = null;
    String address = null;
    String gender = null;

    ObjectToSave(String name, String address, String gender) {
        this.name = name;
        this.address = address;
        this.gender = gender;
    }
}
