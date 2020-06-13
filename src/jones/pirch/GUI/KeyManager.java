package jones.pirch.GUI;

import jones.pirch.ProblemDomain.Key;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class KeyManager {

    public String keyPath = "res/keys.ser";
    protected ArrayList<Key> keyList = new ArrayList<>();

    public KeyManager() {
        this.populateKeyList(this.keyList);
    }
    public KeyManager(String keyPath) {
        this.keyPath = keyPath;
        this.populateKeyList(this.keyList);
    }

    private void populateKeyList(ArrayList<Key> keyList) {
        try {
            FileInputStream fileIn = new FileInputStream(this.keyPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) { // FIXME fix this later, cheap fix. Will have to change later anyways
                keyList.add((Key) in.readObject());
            }
        } catch (EOFException e) { // no good solution to read whole file without getting EOF exception. Catch error for now
            System.out.println("File Populated");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(Arrays.toString(keyList.toArray()));
    }

    public void serializeKeyList() {
//        Key kay = new Key("facebook", "matt", "pass");
//        Key kk = new Key("insta", "jones", "words");
//        keyList.add(kay);
//        keyList.add(kk);
        try {
            FileOutputStream fileOut = new FileOutputStream(keyPath);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            for (Key key : keyList) {
                objOut.writeObject(key);
            }
            objOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addNewKey(Key key) {
        boolean add = true;
        for (Key iterator : keyList) {
            if (iterator.getUrl().equals(key.getUrl())) {
                if (1 == JOptionPane.showConfirmDialog(null, "There's an existing key for this URL already. Continue?", "Add Duplicate ?", JOptionPane.YES_NO_OPTION)) {
                    add = false;
                }
            }
        }
        if (add)
            keyList.add(key);
        serializeKeyList();
    }

    protected void removeKey(Key key, int index) {
        if (0 == JOptionPane.showConfirmDialog(null, "Are you sure you want to remove key for " + key.getUrl() + "?", "Remove Key Confirmation", JOptionPane.YES_NO_OPTION)) {
            keyList.remove(index);
            serializeKeyList();
        }
    }
}




