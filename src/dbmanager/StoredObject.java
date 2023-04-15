package dbmanager;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;

public abstract class StoredObject implements Serializable {

    int primaryKey;
    protected Dictionary<String, String> attributes = new Hashtable<>();

    public StoredObject(int key){
        primaryKey = key;
    }

    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public void updateAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public boolean compareKey(int key){
        return key == primaryKey;
    }

    public int getKey(){ return primaryKey;}

    abstract public void printDetails();

}
