import java.util.ArrayList;
import java.util.Scanner;

public class EventHandler {

    public static User currentUser;
    private final UserListManager users;
    private final InsuranceListManger ins;
    Scanner sc;

    public EventHandler(){
        currentUser = null;
        sc = new Scanner(System.in);
        users = new UserListManager();
        ins = new InsuranceListManger();
        try{
            users.readList();
            ins.readList();
        }
        catch(Exception e){
            MainFrame.logError(e.getMessage());
        }
    }

    public void login(String userID, String pwd){

        if(isNotNumber(userID)) {
            MainFrame.logError("The user ID must be a number!");
            return;
        }

        int id = Integer.parseInt(userID);

        if(users.findUser(id) == null){
            MainFrame.logError("User does not exist!");
        }
        else if(users.findUser(id).getAttribute("password").equals(pwd)){
            currentUser = users.findUser(id);
            MainFrame.logMessage("Successfully logged in as "+ currentUser.getAttribute("name")+"!");
            Main.frameHandler.displayInsuranceBrowse(ins.getList());
        }
        else{
            MainFrame.logError("Invalid password!");
        }

    }

    public void logout(){
        currentUser = null;
    }

    public void register(String name, String pwd, String age, String gender, String contact){

        if(isNotNumber(age)) {
            MainFrame.logError("Age must be a number!");
            return;
        }
        if(isNotNumber(contact)) {
            MainFrame.logError("Contact number must be a number!");
            return;
        }

        User newUser = new User(users.getNextId(), name, pwd, age, gender, contact, (users.getNextId() == 0));
        MainFrame.logMessage("New account created! Your account ID is " + newUser.getKey());
        users.addUser(newUser);
        currentUser = newUser;
        saveData();
        Main.frameHandler.displayInsuranceBrowse(ins.getList());
    }

    public void updateUserAttrib(){
        System.out.println("-------------------------------------------");
        System.out.println("Please enter the detail you wish to change!");
        System.out.println("1 - Name");
        System.out.println("2 - Password");
        System.out.println("3 - Age");
        System.out.println("4 - Gender");
        System.out.println("5 - Contact");
        System.out.println("Other - go back");
        String attrib;
        int change = sc.nextInt(); sc.nextLine();
        switch (change) {
            case 1: attrib = "name"; break;
            case 2: attrib = "password"; break;
            case 3: attrib = "age"; break;
            case 4: attrib = "gender"; break;
            case 5: attrib = "contact"; break;
            default: return;
        }
        System.out.println("Please enter new value for " + attrib);
        String newValue = sc.nextLine();
        currentUser.updateAttribute(attrib, newValue);
    }

    public void displaySortedInsurances(String attribute){
        ArrayList<Insurance> insurances = new ArrayList<>(ins.getList());

        if(attribute.equals("ID")){
            Main.frameHandler.displayInsuranceBrowse(insurances);
            MainFrame.logMessage("Sorting set to none!");
            return;
        }

        MainFrame.logMessage("Sorting by " + attribute + "!");
        attribute = attribute.toLowerCase();

        for(int i=0; i<insurances.size(); i++){
            for(int j=0; j<insurances.size()-1; j++){
                if(insurances.get(j).getAttribute(attribute).compareTo(insurances.get(j+1).getAttribute(attribute)) > 0){
                    Insurance temp = insurances.get(j);
                    insurances.set(j, insurances.get(j+1));
                    insurances.set(j+1, temp);
                }
            }
        }

        Main.frameHandler.displayInsuranceBrowse(insurances);
    }

    public void buyInsurance(){
        System.out.println("-------------------------------------");
        System.out.println("Enter insurance ID to purchase!");
        int id = sc.nextInt(); sc.nextLine();
        if(ins.findInsurance(id) == null)
            System.out.println("Insurance does not exist!");
        else{
            currentUser.addInsurance(id);
            System.out.println("Insurance purchased successfully!");
        }
    }

    public void cancelInsurance(){
        System.out.println("-------------------------------------------");
        System.out.println("Enter insurance ID to remove from account");
        int id = sc.nextInt(); sc.nextLine();
        currentUser.removeInsurance(id);
        System.out.println("Insurance removed successfully!");
    }

    public void printUserInsurances(){
        ArrayList<Integer> IDs = currentUser.getInsurance();
        System.out.println("---------Purchased Insurances---------");
        if(IDs.size() == 0){
            System.out.println("You do not have any Insurances purchased!");
            return;
        }
        for (int i: IDs){
            ins.findInsurance(i).printDetails();
        }
    }

    public void exit(){
        System.out.println("-------------------------------------------");
        System.out.println("Thank you for using the system! =)");
    }

    public void saveData(){
        try{
            users.saveList();
            ins.saveList();
        }
        catch (Exception e){
            MainFrame.logError(e.getMessage());
        }
    }

    private boolean isNotNumber(String s){
        try{
            Long.parseLong(s);
        }
        catch (NumberFormatException e){
            return true;
        }
        return false;
    }
}


