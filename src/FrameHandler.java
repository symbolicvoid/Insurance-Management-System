import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameHandler{
    MainFrame mainFrame;

    public static final Color BACKGROUND_COLOR = new Color(55, 55, 55);
    public static final Color BROWSE_PANEL_COLOR = new Color(75,75,75);
    public static final Color HEADER_COLOR = new Color(51, 139, 168);
    public static final Color TEXT_COLOR = new Color(138, 199, 219);
    public static final Color LABEL_COLOR = new Color(87,87,92);

    private static final int ITEMS_PER_BROWSE_LIMIT = 11;

    private JPanel browsePanel, detailPanel, userActionPanel;
    private LabelPanel labelPanel;
    private CardLayout browseCard;
    private int pageCount, currentPage;
    private NButton next, prev;
    ArrayList<JTextField> registerTextFields, loginTextFields, newInsuranceFields;

    public FrameHandler(){
        mainFrame = new MainFrame();

        registerTextFields = new ArrayList<>();
        loginTextFields = new ArrayList<>();
        newInsuranceFields = new ArrayList<>();

        loginPanelInit();
        registerPanelInit();
        addInsurancePanelInit();
        browsePanelInit();
        detailPanelInit();
        userActionPanelInit();
    }

    private JPanel createRegisterTextField(String text, ArrayList<JTextField> al){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(600, 35));
        panel.setBackground(BACKGROUND_COLOR);

        NLabel label = new NLabel(text+":");
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
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        loginPanel.setBackground(BACKGROUND_COLOR);
        loginPanel.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 50));

        NLabel head = new NLabel("Login", 50);
        head.setPreferredSize(new Dimension(600, 70));
        head.setVerticalAlignment(JLabel.TOP);
        head.setHorizontalAlignment(JLabel.CENTER);

        NButton login = new NButton("Login!");
        NButton register = new NButton("Register");

        loginPanel.add(head);
        loginPanel.add(createRegisterTextField("User ID", loginTextFields));
        loginPanel.add(createRegisterTextField("Password", loginTextFields));
        loginPanel.add(login);
        loginPanel.add(register);
        mainFrame.middlePanel.add(loginPanel, "login");
    }

    private void registerPanelInit(){
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        registerPanel.setBackground(BACKGROUND_COLOR);
        registerPanel.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 20));

        NLabel head = new NLabel("Register", 50);
        head.setPreferredSize(new Dimension(600, 70));
        head.setVerticalAlignment(JLabel.TOP);
        head.setHorizontalAlignment(JLabel.CENTER);

        NButton register = new NButton("Register!");
        NButton login = new NButton("Login");

        registerPanel.add(head);
        registerPanel.add(createRegisterTextField("Full name", registerTextFields));
        registerPanel.add(createRegisterTextField("Password", registerTextFields));
        registerPanel.add(createRegisterTextField("Age", registerTextFields));
        registerPanel.add(createRegisterTextField("Gender", registerTextFields));
        registerPanel.add(createRegisterTextField("Contact Number", registerTextFields));
        registerPanel.add(register);
        registerPanel.add(login);
        mainFrame.middlePanel.add(registerPanel, "register");
    }

    private void addInsurancePanelInit(){
        JPanel newInsurancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        newInsurancePanel.setBackground(BACKGROUND_COLOR);
        newInsurancePanel.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 20));

        NLabel head = new NLabel("New Insurance", 50);
        head.setPreferredSize(new Dimension(600, 70));
        head.setVerticalAlignment(JLabel.TOP);
        head.setHorizontalAlignment(JLabel.CENTER);

        NButton register = new NButton("Add");
        NButton login = new NButton("Cancel");

        newInsurancePanel.add(head);
        newInsurancePanel.add(createRegisterTextField("Name", newInsuranceFields));
        newInsurancePanel.add(createRegisterTextField("Company", newInsuranceFields));
        newInsurancePanel.add(createRegisterTextField("Premium", newInsuranceFields));
        newInsurancePanel.add(createRegisterTextField("Amount", newInsuranceFields));
        newInsurancePanel.add(createRegisterTextField("Duration", newInsuranceFields));
        newInsurancePanel.add(register);
        newInsurancePanel.add(login);
        mainFrame.middlePanel.add(newInsurancePanel, "newInsurance");
    }

    private void browsePanelInit(){
        JPanel browseMaster = new JPanel(new BorderLayout());
        browseMaster.setPreferredSize(new Dimension(600, 500));
        labelPanel = new LabelPanel();
        browseMaster.add(labelPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setPreferredSize(new Dimension(600, 50));
        buttonPanel.add(prev = new NButton("Prev"));
        buttonPanel.add(next = new NButton("Next"));
        prev.setEnabled(false);
        next.setEnabled(false);
        browseMaster.add(buttonPanel, BorderLayout.SOUTH);

        browseCard = new CardLayout();
        browsePanel = new JPanel(browseCard);
        browsePanel.setBackground(BROWSE_PANEL_COLOR);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(BROWSE_PANEL_COLOR);
        emptyPanel.setPreferredSize(new Dimension(600, 400));
        browsePanel.add(emptyPanel, "none");

        browseMaster.add(browsePanel, BorderLayout.CENTER);
        mainFrame.middlePanel.add(browseMaster, "browse");
    }

    private void detailPanelInit(){
        detailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        detailPanel.setPreferredSize(new Dimension(250, 800));
        detailPanel.setBackground(BACKGROUND_COLOR);
        mainFrame.rightPanel.add(detailPanel, "details");
        mainFrame.rightPanel.add(new AdminCommandPanel(), "admin");
        mainFrame.getRightCard().show(mainFrame.rightPanel, "none");
    }

    private void userActionPanelInit(){
        userActionPanel = new JPanel();
        userActionPanel.setBorder(BorderFactory.createLineBorder(BACKGROUND_COLOR, 20));
        userActionPanel.setPreferredSize(new Dimension(900, 100));
        userActionPanel.setBackground(BACKGROUND_COLOR);

        mainFrame.topPanel.add(userActionPanel, "user");
        mainFrame.getTopCard().show(mainFrame.topPanel, "none");
    }

    private void putInsurancesToPanel(ArrayList<Insurance> insurances, JPanel panel){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.setBackground(BROWSE_PANEL_COLOR);

        if(insurances.size() == 0)
            return;

        pageCount++;
        browsePanel.add(panel, ""+pageCount);

        int id = 1, counter = 1;
        for(Insurance i: insurances){
            panel.add(new InsurancePanel((pageCount-1) * ITEMS_PER_BROWSE_LIMIT + id, i));
            counter++;
            id++;
            if(counter > ITEMS_PER_BROWSE_LIMIT){
                putInsurancesToPanel(new ArrayList<>(insurances.subList(id - 1, insurances.size())),
                        new JPanel());
                return;
            }
        }
    }

    private void putUsersToPanel(ArrayList<User> users, JPanel panel){
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.setBackground(BROWSE_PANEL_COLOR);

        if(users.size() == 0)
            return;

        pageCount++;
        browsePanel.add(panel, ""+pageCount);

        int id = 1, counter = 1;
        for(User user: users){
            panel.add(new UserPanel(user));
            counter++;
            id++;
            if(counter > ITEMS_PER_BROWSE_LIMIT){
                putUsersToPanel(new ArrayList<>(users.subList(id - 1, users.size())),
                        new JPanel());
                return;
            }
        }
    }

    private void clearTextFields(ArrayList<JTextField> textFields){
        for(JTextField tf: textFields){
            tf.setText("");
        }
    }

    public void displayLogin(){
        clearTextFields(loginTextFields);
        mainFrame.getRightCard().show(mainFrame.rightPanel, "none");
        mainFrame.getTopCard().show(mainFrame.topPanel, "none");
        EventListener.activeTextFields = loginTextFields;
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "login");
    }

    public void displayRegister(){
        clearTextFields(registerTextFields);
        mainFrame.getRightCard().show(mainFrame.rightPanel, "none");
        mainFrame.getTopCard().show(mainFrame.topPanel, "none");
        EventListener.activeTextFields = registerTextFields;
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "register");
    }

    public void displayNewInsurance(){
        clearTextFields(newInsuranceFields);
        EventListener.activeTextFields = newInsuranceFields;
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "newInsurance");
    }

    public void deselectDetails(){
        mainFrame.getRightCard().show(mainFrame.rightPanel, "none");
    }

    public void displayInsuranceBrowse(ArrayList<Insurance> insurances){
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "browse");
        labelPanel.setInsuranceLabels();
        if(insurances.size() == 0) {
            MainFrame.logError("No insurances found!");
            browseCard.show(browsePanel, "none");
            return;
        }
        prev.setEnabled(false);
        next.setEnabled(false);
        pageCount = 0;
        putInsurancesToPanel(insurances, new JPanel());
        currentPage = 1;
        browseCard.show(browsePanel, "1");
        if(pageCount > 1)
            next.setEnabled(true);
        browsePanel.validate();
    }

    public void displayUserBrowse(ArrayList<User> users){
        mainFrame.getMiddleCard().show(mainFrame.middlePanel, "browse");
        labelPanel.setUserLabels();
        prev.setEnabled(false);
        next.setEnabled(false);
        pageCount = 0;
        putUsersToPanel(users, new JPanel());
        currentPage = 1;
        browseCard.show(browsePanel, "1");
        if(pageCount > 1)
            next.setEnabled(true);
        browsePanel.validate();
    }

    public void nextBrowsePage(){
        browseCard.next(browsePanel);
        currentPage++;
        if(currentPage == pageCount)
            next.setEnabled(false);
        prev.setEnabled(true);
    }

    public void prevBrowsePage(){
        browseCard.previous(browsePanel);
        currentPage--;
        if(currentPage == 1)
            prev.setEnabled(false);
        next.setEnabled(true);
    }

    public void displayUserFunctions(User currentUser){
        userActionPanel.removeAll();
        userActionPanel.add(new UserFunctionPanel(currentUser));
        mainFrame.getTopCard().show(mainFrame.topPanel, "user");
        userActionPanel.validate();
    }

    public void displayInsuranceDetails(Insurance insurance, boolean isPurchased){
        InsuranceDetailsPanel idp = new InsuranceDetailsPanel(insurance, isPurchased);
        detailPanel.removeAll();
        detailPanel.add(idp);
        mainFrame.getRightCard().show(mainFrame.rightPanel, "details");
        detailPanel.validate();
    }

    public void displayUserDetails(User user){
        UserDetailsPanel udp = new UserDetailsPanel(user);
        detailPanel.removeAll();
        detailPanel.add(udp);
        mainFrame.getRightCard().show(mainFrame.rightPanel, "details");
        detailPanel.validate();
    }

    public void displayAdminFunctions(){
        mainFrame.getRightCard().show(mainFrame.rightPanel, "admin");
    }
}
