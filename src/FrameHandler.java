import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameHandler{
    MainFrame mainFrame;
    MiniFrame miniFrame;

    public static final Color BORDER_COLOR = new Color(44, 44, 47);
    public static final Color BACKGROUND_COLOR = new Color(55, 55, 55);

    public static final Color BROWSE_PANEL_COLOR = new Color(75,75,75);

    private JPanel welcomePanel, browsePanel;
    ArrayList<JTextField> registerTextFields, loginTextFields;

    public FrameHandler(){
        mainFrame = new MainFrame();
        miniFrame = new MiniFrame();

        registerTextFields = new ArrayList<>();
        loginTextFields = new ArrayList<>();

        welcomePanelInit();
        loginPanelInit();
        registerPanelInit();
        browsePanelInit();
    }

    private void welcomePanelInit() {
        welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER_COLOR, 4),
                BorderFactory.createLineBorder(BACKGROUND_COLOR, 16)));

        JPanel textPanel = new JPanel();
        NLabel welcomeText = new NLabel("Welcome to the insurance management system!");
        NLabel loginText = new NLabel("Please Login or Register to continue!");
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        loginText.setHorizontalAlignment(JLabel.CENTER);
        textPanel.add(welcomeText);
        textPanel.add(loginText);
        textPanel.setBackground(BACKGROUND_COLOR);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        NButton loginButton = new NButton("Login");
        NButton registerButton = new NButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        welcomePanel.add(textPanel);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createRegisterTextField(String text, ArrayList<JTextField> al){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(600, 35));
        panel.setBackground(BACKGROUND_COLOR);

        NLabel label = new NLabel(text);
        label.setPreferredSize(new Dimension(100, 20));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 20));
        textField.addActionListener(Main.eventListener);
        al.add(textField);

        panel.add(label);
        panel.add(textField);
        return panel;
    }
    private void loginPanelInit(){
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        loginPanel.setBackground(BACKGROUND_COLOR);
        loginPanel.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 50));

        NLabel head = new NLabel("Login", 50);
        head.setPreferredSize(new Dimension(600, 70));
        head.setVerticalAlignment(JLabel.TOP);
        head.setHorizontalAlignment(JLabel.CENTER);

        NButton login = new NButton("Login!");

        loginPanel.add(head);
        loginPanel.add(createRegisterTextField("User ID", loginTextFields));
        loginPanel.add(createRegisterTextField("Password", loginTextFields));
        loginPanel.add(login);
        mainFrame.middlePanel.add(loginPanel, "login");
    }

    private void registerPanelInit(){
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        registerPanel.setBackground(BACKGROUND_COLOR);
        registerPanel.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 20));

        NLabel head = new NLabel("Register", 50);
        head.setPreferredSize(new Dimension(600, 70));
        head.setVerticalAlignment(JLabel.TOP);
        head.setHorizontalAlignment(JLabel.CENTER);

        NButton register = new NButton("Register!");

        registerPanel.add(head);
        registerPanel.add(createRegisterTextField("Full name", registerTextFields));
        registerPanel.add(createRegisterTextField("Password", registerTextFields));
        registerPanel.add(createRegisterTextField("Age", registerTextFields));
        registerPanel.add(createRegisterTextField("Gender", registerTextFields));
        registerPanel.add(createRegisterTextField("Contact Number", registerTextFields));
        registerPanel.add(register);
        mainFrame.middlePanel.add(registerPanel, "register");
    }

    private void browsePanelInit(){
        JPanel browseMaster = new JPanel(new BorderLayout());
        browseMaster.setPreferredSize(new Dimension(600, 500));
        browseMaster.add(new InfoPanel("Name", "Company", "Premium", "Amount", "Duration"), BorderLayout.NORTH);
        browsePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
        browsePanel.setBackground(BROWSE_PANEL_COLOR);
        browseMaster.add(browsePanel, BorderLayout.CENTER);
        mainFrame.middlePanel.add(browseMaster, "browse");
    }

    private void putInsurancesToPanel(ArrayList<Insurance> insurances){
        browsePanel.removeAll();

        if(insurances.size() == 0){
            MainFrame.logError("No insurances found in the database!");
            return;
        }

        int id = 1;
        for(Insurance i: insurances){
            browsePanel.add(new InsurancePanel(id, i));
            id++;
        }
    }

    public void displayWelcome(){
        miniFrame.add(welcomePanel);
        miniFrame.setVisible(true);
    }

    public void displayLogin(){
        miniFrame.setVisible(false);
        EventListener.activeTextFields = loginTextFields;
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "login");
        mainFrame.setVisible(true);
    }

    public void displayRegister(){
        miniFrame.setVisible(false);
        EventListener.activeTextFields = registerTextFields;
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "register");
        mainFrame.setVisible(true);
    }

    public void displayInsuranceBrowse(ArrayList<Insurance> insurances){
        putInsurancesToPanel(insurances);
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "browse");
        mainFrame.setVisible(true);
    }
}
