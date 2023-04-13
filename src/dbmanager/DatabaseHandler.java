package dbmanager;

import java.io.*;
import java.util.List;

public class DatabaseHandler {

    String filename;

    public DatabaseHandler(String _filename){
        filename = _filename;
    }

    public List readListFromFile() throws Exception {
        try {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename));
            return (List) objIn.readObject();
        }
        catch (EOFException e){
            return null;
        }
        catch(FileNotFoundException e){
            File newFile = new File(filename);
            if(newFile.createNewFile())
                return null;
            else throw new FileCannotBeCreatedException("Database files are missing and cannot be created!");
        }
    }

    public void writeListToFile(List list) throws Exception{
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename));
        objOut.writeObject(list);
    }

}

class FileCannotBeCreatedException extends Exception{
    FileCannotBeCreatedException(String msg){
        super(msg);
    }
}
