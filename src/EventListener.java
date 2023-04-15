import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EventListener implements ActionListener {

    private final EventHandler eventHandler = new EventHandler();
    public static ArrayList<JTextField> activeTextFields;

    public EventListener(){
        activeTextFields = new ArrayList<>();
    }

    private boolean allFieldsFilled(){
        for(JTextField i: activeTextFields){
            if(i.getText().equals(""))
                return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {

        MainFrame.clearLog();

        switch (e.getActionCommand()) {
            case "Login":
                Main.frameHandler.displayLogin();
                break;

            case "Register":
                Main.frameHandler.displayRegister();
                break;

            case "Login!":
                if (allFieldsFilled())
                    eventHandler.login(activeTextFields.get(0).getText(), activeTextFields.get(1).getText());

                else MainFrame.logError("Please fill all fields!");
                break;

            case "Register!":
                if (allFieldsFilled()) {
                    eventHandler.register(activeTextFields.get(0).getText(), activeTextFields.get(1).getText(),
                            activeTextFields.get(2).getText(), activeTextFields.get(3).getText(),
                            activeTextFields.get(4).getText());
                } else MainFrame.logError("Please fill all fields!");
                break;

            case "My Insurances":
                eventHandler.displayUserInsurances();
                NButton button = (NButton) e.getSource();
                button.setText("All Insurances");
                break;

            case "All Insurances":
                eventHandler.displayAllInsurances();
                button = (NButton) e.getSource();
                button.setText("My Insurances");
                break;

            case "My Profile":
                eventHandler.displayUserDetails();
                break;

            case "Logout":
                eventHandler.logout();
                break;

            case "Admin Functions":
                Main.frameHandler.displayAdminFunctions();
                break;

            case "New Insurance":
                Main.frameHandler.displayNewInsurance();
                break;

            case "Temp Insurances":
                eventHandler.displayTemporaryInsurances();
                break;

            case "Next":
                Main.frameHandler.nextBrowsePage();
                break;

            case "Prev":
                Main.frameHandler.prevBrowsePage();
                break;

            case "Add":
                if (allFieldsFilled()) {
                    eventHandler.addInsurance(activeTextFields.get(0).getText(), activeTextFields.get(1).getText(),
                            activeTextFields.get(2).getText(), activeTextFields.get(3).getText(),
                            activeTextFields.get(4).getText());
                } else MainFrame.logError("Please fill all fields!");
                break;

            case "Go Back":
                deselectDetails();
                break;

            case "Cancel":
                eventHandler.displayAllInsurances();
                break;

            default:
                MainFrame.logError("Feature not implemented yet!");
                break;
        }
    }

    public void infoLabelClicked(String attribute){
        MainFrame.clearLog();
        eventHandler.displaySortedInsurances(attribute);
    }

    public void insuranceSelected(int id){
        MainFrame.clearLog();
        eventHandler.displayInsuranceDetails(id);
    }

    public void buyInsurance(int id){
        MainFrame.clearLog();
        eventHandler.buyInsurance(id);
    }

    public void cancelInsurance(int id){
        MainFrame.clearLog();
        eventHandler.cancelInsurance(id);
    }

    public void deselectDetails(){
        MainFrame.clearLog();
        Main.frameHandler.deselectDetails();
    }

    public void updateUserData(int id){

        MainFrame.clearLog();

        if(allFieldsFilled())
            eventHandler.updateUserData(id, activeTextFields.get(0).getText(), activeTextFields.get(1).getText(),
                    activeTextFields.get(2).getText(), activeTextFields.get(3).getText());
        else
            MainFrame.logError("Please fill all fields!");
    }
}
