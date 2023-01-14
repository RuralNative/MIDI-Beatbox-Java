package BeatBox.Serialization;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        ObjectToSave berlinData = new ObjectToSave("John Berlin", "Moises Padilla", "Male");
        serializeObjectToSave(berlinData);
    }

    public static void serializeObjectToSave(Object objectReference) {
        try {
            FileOutputStream fileStream = new FileOutputStream("John Berlin's Data.ser");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            try {
                objectStream.writeObject(objectReference);
                objectStream.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
