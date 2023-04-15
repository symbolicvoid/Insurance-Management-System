import java.util.ArrayList;
import java.util.Scanner;

public class EventHandler {

    private User currentUser;
    private UserListManager users = null;
    private InsuranceListManger ins = null;
    boolean userInsurancesSelected = false, shouldSaveFile = true;
    Scanner sc;

    public EventHandler(){
        currentUser = null;
        sc = new Scanner(System.in);
        try{
            users = new UserListManager();
            ins = new InsuranceListManger();
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
            displayAllInsurances();
            Main.frameHandler.displayUserFunctions(currentUser);
        }
        else{
            MainFrame.logError("Invalid password!");
        }
    }

    public void logout(){
        currentUser = null;
        Main.frameHandler.displayLogin();
        MainFrame.logMessage("Successfully logged out!");
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
        displayAllInsurances();
        Main.frameHandler.displayUserFunctions(currentUser);
    }

    public void updateUserData(int id, String name, String age, String gender, String contact){

        if(isNotNumber(age)) {
            MainFrame.logError("Age must be a number!");
            return;
        }
        if(isNotNumber(contact)) {
            MainFrame.logError("Contact number must be a number!");
            return;
        }

        String updatedAttributes = "";

        User targetUser = users.findUser(id);

        if(!name.equals(targetUser.getAttribute("name"))){
            targetUser.updateAttribute("name", name);
            updatedAttributes += " Name";
        }
        if(!age.equals(targetUser.getAttribute("age"))){
            targetUser.updateAttribute("age", age);
            updatedAttributes += " Age";
        }
        if(!gender.equals(targetUser.getAttribute("gender"))){
            targetUser.updateAttribute("gender", gender);
            updatedAttributes += " Gender";
        }
        if(!contact.equals(targetUser.getAttribute("contact"))){
            targetUser.updateAttribute("contact", contact);
            updatedAttributes += " Contact";
        }

        if(!updatedAttributes.equals("")) {
            MainFrame.logMessage("Updated data:" + updatedAttributes);
            Main.frameHandler.displayUserDetails(targetUser);
            Main.frameHandler.displayUserFunctions(currentUser);
            saveData();
        }
    }

    public void displayAllInsurances(){
        userInsurancesSelected = false;
        Main.frameHandler.displayInsuranceBrowse(ins.getList());
    }

    public void displayUserInsurances(){
        userInsurancesSelected = true;
        Main.frameHandler.displayInsuranceBrowse(getUserInsurances(currentUser.getKey()));
    }

    private ArrayList<Insurance> getUserInsurances(int id){
        ArrayList<Integer> userInsurances = users.findUser(id).getInsurance();
        ArrayList<Insurance> insurances = new ArrayList<>();
        for(int i: userInsurances){
            insurances.add(ins.findInsurance(i));
        }
        return insurances;
    }

    public void displaySortedInsurances(String attribute){
        ArrayList<Insurance> insurances;
        if(userInsurancesSelected)
            insurances = new ArrayList<>(getUserInsurances(currentUser.getKey()));
        else
            insurances = new ArrayList<>(ins.getList());

        if(attribute.equals("ID")){
            Main.frameHandler.displayInsuranceBrowse(insurances);
            MainFrame.logMessage("Sorting set to none!");
            return;
        }

        MainFrame.logMessage("Sorting by " + attribute + "!");
        attribute = attribute.toLowerCase();

        if(attribute.equals("premium") || attribute.equals("amount") || attribute.equals("duration")){
            for(int i=0; i<insurances.size(); i++){
                for(int j=0; j<insurances.size()-1; j++){
                    if(Integer.parseInt(insurances.get(j).getAttribute(attribute)) >
                            Integer.parseInt((insurances.get(j+1).getAttribute(attribute)))
                    ){
                        Insurance temp = insurances.get(j);
                        insurances.set(j, insurances.get(j+1));
                        insurances.set(j+1, temp);
                    }
                }
            }
        }
        else{
            for (int i = 0; i < insurances.size(); i++) {
                for (int j = 0; j < insurances.size() - 1; j++) {
                    if (insurances.get(j).getAttribute(attribute).compareTo(
                            insurances.get(j + 1).getAttribute(attribute)) > 0) {
                        Insurance temp = insurances.get(j);
                        insurances.set(j, insurances.get(j + 1));
                        insurances.set(j + 1, temp);
                    }
                }
            }
        }

        Main.frameHandler.displayInsuranceBrowse(insurances);
    }

    public void displayInsuranceDetails(int id){
        Main.frameHandler.displayInsuranceDetails(ins.findInsurance(id), currentUser.getInsurance().contains(id));
    }

    public void buyInsurance(int id){
        currentUser.addInsurance(id);
        MainFrame.logMessage("Insurance purchased successfully!");
        saveData();
    }

    public void cancelInsurance(int id){
        currentUser.removeInsurance(id);
        MainFrame.logMessage("Insurance removed successfully!");
        saveData();
    }

    public void addInsurance(String name, String company, String premium, String amount, String duration){
        if(isNotNumber(premium)) {
            MainFrame.logError("Premium must be a number!");
            return;
        }
        if(isNotNumber(amount)) {
            MainFrame.logError("Amount must be a number!");
            return;
        }
        if(isNotNumber(duration)) {
            MainFrame.logError("Duration must be a number!");
            return;
        }
        Insurance newInsurance = new Insurance(ins.getNextId(), name, company, premium, amount, duration);
        MainFrame.logMessage("New insurance added!");
        ins.addInsurance(newInsurance);
        saveData();
        displayAllInsurances();
    }

    public void displayTemporaryInsurances(){
        shouldSaveFile = false;
        String name = "Insurance", company = "Company", premium = "100", amount = "1000", duration = "5";
        for(int i=1; i<=10; i++){
            addInsurance(name + i, company + i,
                    (Integer.parseInt(premium) * i) + "", (Integer.parseInt(amount) * i) + "",
                    (Integer.parseInt(duration) * i) + "");
        }
        MainFrame.logMessage("Temporary insurances added for testing purposes. Data will NOT be saved!");
        Main.frameHandler.displayInsuranceBrowse(ins.getList());
    }

    public void displayUserDetails(){
        Main.frameHandler.displayUserDetails(currentUser);
    }

    private void saveData(){
        if(!shouldSaveFile)
            return;
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
