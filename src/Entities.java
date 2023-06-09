import dbmanager.StoredObject;
import java.util.ArrayList;

class User extends StoredObject {

    private final ArrayList<Integer> insuranceIDs;
    public boolean isAdmin;

    public User(int id, String name, String password, String age, String gender, String contact, boolean _isAdmin){
        super(id);
        attributes.put("name", name);
        attributes.put("password", password);
        attributes.put("age", age);
        attributes.put("gender", gender);
        attributes.put("contact", contact);
        isAdmin = _isAdmin;
        insuranceIDs = new ArrayList<>();
    }

    public void addInsurance(int insuranceID){
        if(!insuranceIDs.contains(insuranceID))
            insuranceIDs.add(insuranceID);
    }

    public void removeInsurance(int insuranceID){
        int index = insuranceIDs.indexOf(insuranceID);
        if(index != -1)
            insuranceIDs.remove(index);
    }

    @Override
    public void printDetails() {
        System.out.println("---------------User Details------------");
        System.out.println("|Name: "+getAttribute("name"));
        System.out.println("|Age: "+getAttribute("age"));
        System.out.println("|Gender: "+getAttribute("gender"));
        System.out.println("|Contact: "+getAttribute("contact"));
    }

    public ArrayList<Integer> getInsurance(){
        return insuranceIDs;
    }

}

class Insurance extends StoredObject {

    public Insurance(int id, String name, String company, String premium, String amount, String duration) {
        super(id);
        attributes.put("name", name);
        attributes.put("company", company);
        attributes.put("premium", premium);
        attributes.put("amount", amount);
        attributes.put("duration", duration);
    }

    @Override
    public void printDetails() {
        System.out.println("---------------Insurance Details------------");
        System.out.println("|Insurance ID: "+getKey());
        System.out.println("|Name: "+getAttribute("name"));
        System.out.println("|Company: "+getAttribute("company"));
        System.out.println("|Premium: "+getAttribute("premium"));
        System.out.println("|Amount: "+getAttribute("amount"));
        System.out.println("|Duration: "+getAttribute("duration"));
    }
}
