package BeatBox.Serialization;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        ObjectToSave berlinData = new ObjectToSave("John Berlin", "Moises Padilla", "Male");
        serializeObjectToSave(berlinData);
        deserializeObject();
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

    public static void deserializeObject() {
        Object johnBerlin;
        ObjectToSave johnBerlinData = new ObjectToSave(null, null, null);

        try {
            FileInputStream fileToDeserialize = new FileInputStream("John Berlin's Data.ser");
            ObjectInputStream readDeserializedFile = new ObjectInputStream(fileToDeserialize);
            try {
                johnBerlin = readDeserializedFile.readObject();
                johnBerlinData = (ObjectToSave) johnBerlin;
                readDeserializedFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(johnBerlinData.name);
    }
}
