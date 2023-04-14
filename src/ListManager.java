import java.util.ArrayList;
import dbmanager.DatabaseHandler;

class UserListManager{

    ArrayList<User> list;
    DatabaseHandler dh;

    public UserListManager() throws Exception{
        list = new ArrayList<>();
        dh = new DatabaseHandler("database/users.ser");
    }

    public ArrayList<User> getList() {
        return list;
    }

    public void saveList() throws Exception{
        dh.writeListToFile(list);
    }

    public void readList() throws Exception {
        ArrayList<User> temp = (ArrayList<User>)dh.readListFromFile();
        if(temp != null){
            list = temp;
        }
    }

    public int getNextId() {
        return list.size();
    }

    public User findUser(int key) {
        for(User obj: list){
            if (obj.compareKey(key))
                return obj;
        }
        return null;
    }

    public void addUser(User newUser){
        list.add(newUser);
    }
}

class InsuranceListManger{

    ArrayList<Insurance> list;
    DatabaseHandler dh;

    public InsuranceListManger() throws Exception{
        list = new ArrayList<>();
        dh = new DatabaseHandler("database/insurance.ser");
    }

    public ArrayList<Insurance> getList() {
        return list;
    }

    public void saveList() throws Exception {
        dh.writeListToFile(list);
    }

    public void readList() throws Exception {
        ArrayList<Insurance> temp = (ArrayList<Insurance>)dh.readListFromFile();
        if(temp != null){
            list = temp;
        }
    }

    public int getNextId() {
        return list.size();
    }

    public void addInsurance(Insurance ins){
        list.add(ins);
    }

    public Insurance findInsurance(int key) {
        for(Insurance obj: list){
            if (obj.compareKey(key))
                return obj;
        }
        return null;
    }
}


