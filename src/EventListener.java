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
        for(var i: activeTextFields){
            if(i.getText().equals(""))
                return false;
        }
        return true;
    }

    @Override
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
            default:
                MainFrame.logError("Feature not implemented yet!");
                break;
        }
    }

    public void infoLabelClicked(String attribute){
        eventHandler.displaySortedInsurances(attribute);
    }
}
