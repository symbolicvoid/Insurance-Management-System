import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class MainFrame extends JFrame {

    public JPanel masterPanel, topPanel, middlePanel, middleMasterPanel, rightPanel, msgPanel;
    private static final JLabel msgLabel = new JLabel();

    MainFrame(){
        super();
        this.setBounds(300, 100, 900, 600);
        this.setTitle("Insurance Management System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel emptyRight = new JPanel(), emptyTop = new JPanel();
        emptyRight.setPreferredSize(new Dimension(250, 800));
        emptyRight.setBackground(FrameHandler.BACKGROUND_COLOR);
        emptyTop.setPreferredSize(new Dimension(900, 100));
        emptyTop.setBackground(FrameHandler.BACKGROUND_COLOR);

        msgLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        msgLabel.setVerticalAlignment(JLabel.CENTER);
        msgLabel.setHorizontalAlignment(JLabel.CENTER);

        masterPanel = new JPanel(new BorderLayout());
        middleMasterPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new CardLayout());
        rightPanel = new JPanel(new CardLayout());
        middlePanel = new JPanel(new CardLayout());
        msgPanel = new JPanel(new GridLayout(1, 1));

        topPanel.setPreferredSize(new Dimension(900, 100));
        rightPanel.setPreferredSize(new Dimension(250, 800));
        middlePanel.setPreferredSize(new Dimension(650,465));
        msgPanel.setPreferredSize(new Dimension(600, 35));

        topPanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        topPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        rightPanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        rightPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        middlePanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        msgPanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        msgPanel.setBorder(BorderFactory.createLineBorder(FrameHandler.BACKGROUND_COLOR, 3));
        middleMasterPanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        middleMasterPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        masterPanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        masterPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        msgPanel.add(msgLabel);
        middleMasterPanel.add(msgPanel, BorderLayout.SOUTH);
        middleMasterPanel.add(middlePanel, BorderLayout.CENTER);
        masterPanel.add(topPanel, BorderLayout.NORTH);
        masterPanel.add(rightPanel, BorderLayout.EAST);
        masterPanel.add(middleMasterPanel, BorderLayout.CENTER);

        rightPanel.add(emptyRight,"none");
        topPanel.add(emptyTop,"none");

        this.add(masterPanel);

        this.setVisible(true);
    }

    public CardLayout getMiddleCard(){
        return (CardLayout) this.middlePanel.getLayout();
    }

    public CardLayout getTopCard(){
        return (CardLayout) this.topPanel.getLayout();
    }

    public CardLayout getRightCard(){
        return (CardLayout) this.rightPanel.getLayout();
    }

    public static void logError(String message){
        msgLabel.setText(message);
        msgLabel.setForeground(new Color(208, 0, 0));
    }

    public static void logMessage(String message){
        msgLabel.setText(message);
        msgLabel.setForeground(new Color(0, 134, 55));
    }

    public static void clearLog(){
        msgLabel.setText("");
    }
}

class NButton extends JButton{

    public static final Color BUTTON_COLOR = new Color(51, 61, 55);
    public static final Color TEXT_COLOR = new Color(74, 183, 131);

    public NButton(String text){
        super(text);
        this.setBackground(BUTTON_COLOR);
        this.setForeground(TEXT_COLOR);
        this.addActionListener(Main.eventListener);
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(100, 30));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public NButton(String text, ActionListener listener){
        super(text);
        this.setBackground(BUTTON_COLOR);
        this.setForeground(TEXT_COLOR);
        this.addActionListener(listener);
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(100, 30));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}

class NLabel extends JLabel{

    public NLabel(String text){
        super(text);
        this.setForeground(FrameHandler.TEXT_COLOR);
    }
    public NLabel(String text, int fontSize){
        super(text);
        this.setForeground(FrameHandler.HEADER_COLOR);
        this.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
    }
}

class InsurancePanel extends JPanel implements MouseListener{

    int insuranceID;
    JLabel id, name, company, premium, amount, duration;

    InsurancePanel(int id_, Insurance ins){
        super(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(625, 28));

        insuranceID = ins.getKey();

        name = new JLabel(ins.getAttribute("name"), SwingConstants.CENTER);
        company = new JLabel(ins.getAttribute("company"), SwingConstants.CENTER);
        premium = new JLabel("Rs. "+processAmount(ins.getAttribute("premium")), SwingConstants.CENTER);
        amount = new JLabel("Rs. "+processAmount(ins.getAttribute("amount")), SwingConstants.CENTER);
        duration = new JLabel(ins.getAttribute("duration") + " years", SwingConstants.CENTER);
        id = new JLabel(id_+"", SwingConstants.CENTER);

        id.setPreferredSize(new Dimension(25, 25));
        name.setPreferredSize(new Dimension(225, 25));
        company.setPreferredSize(new Dimension(75,25));
        premium.setPreferredSize(new Dimension(100,25));
        amount.setPreferredSize(new Dimension(125, 25));
        duration.setPreferredSize(new Dimension(75, 25));

        name.addMouseListener(this);
        name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        labelInit(id);
        labelInit(name);
        labelInit(company);
        labelInit(premium);
        labelInit(amount);
        labelInit(duration);
    }

    private void labelInit(JLabel label){
        label.setBackground(FrameHandler.LABEL_COLOR);
        label.setForeground(FrameHandler.TEXT_COLOR);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, FrameHandler.BROWSE_PANEL_COLOR));
        this.add(label);
    }

    private String processAmount(String amount){
        StringBuilder result = new StringBuilder();
        int count = 3;
        for(int i = amount.length() - 1; i >= 0; i--){
            result.insert(0, amount.charAt(i));
            count--;
            if(count == 0 && i != 0){
                result.insert(0, ",");
                count = 2;
            }
        }
        return result.toString();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Main.eventListener.insuranceSelected(this.insuranceID);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

class InfoPanel extends JPanel implements MouseListener {
    public static Color infoAttribColor = new Color(67,67,75);
    JLabel id, l1, l2, l3, l4, l5;

    InfoPanel(){
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(625, 45));

        id = new JLabel("ID", SwingConstants.CENTER);
        l1 = new JLabel("Name", SwingConstants.CENTER);
        l2 = new JLabel("Company", SwingConstants.CENTER);
        l3 = new JLabel("Premium", SwingConstants.CENTER);
        l4 = new JLabel("Amount", SwingConstants.CENTER);
        l5 = new JLabel("Duration", SwingConstants.CENTER);

        id.setPreferredSize(new Dimension(25, 40));
        l1.setPreferredSize(new Dimension(225, 40));
        l2.setPreferredSize(new Dimension(75, 40));
        l3.setPreferredSize(new Dimension(100,40));
        l4.setPreferredSize(new Dimension(125,40));
        l5.setPreferredSize(new Dimension(75, 40));

        labelInit(id);
        labelInit(l1);
        labelInit(l2);
        labelInit(l3);
        labelInit(l4);
        labelInit(l5);
    }

    InfoPanel(boolean isAdminRequest){
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));

        if(!isAdminRequest) {
            MainFrame.logError("Only admins can access this!");
            return;
        }

        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(625, 45));

        id = new JLabel("ID", SwingConstants.CENTER);
        l1 = new JLabel("Name", SwingConstants.CENTER);
        l2 = new JLabel("Age", SwingConstants.CENTER);
        l3 = new JLabel("Gender", SwingConstants.CENTER);
        l4 = new JLabel("Contact", SwingConstants.CENTER);
        l5 = new JLabel("Admin", SwingConstants.CENTER);

        id.setPreferredSize(new Dimension(25, 40));
        l1.setPreferredSize(new Dimension(250, 40));
        l2.setPreferredSize(new Dimension(50, 40));
        l3.setPreferredSize(new Dimension(75,40));
        l4.setPreferredSize(new Dimension(125,40));
        l5.setPreferredSize(new Dimension(100, 40));

        labelInit(id);
        labelInit(l1);
        labelInit(l2);
        labelInit(l3);
        labelInit(l4);
        labelInit(l5);
    }

    private void labelInit(JLabel label){
        label.setBackground(infoAttribColor);
        label.setForeground(FrameHandler.HEADER_COLOR);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createRaisedBevelBorder());
        label.addMouseListener(this);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.add(label);
    }

    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        Main.eventListener.infoLabelClicked(label.getText());
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}

class InsuranceDetailsPanel extends JPanel implements ActionListener{

    int insuranceID;
    JLabel head;
    JPanel buttonPanel;
    NButton purchase, cancel, deselect;

    InsuranceDetailsPanel(Insurance insurance, boolean isPurchased){
        super(new FlowLayout(FlowLayout.CENTER, 10, 0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(250, 800));

        insuranceID = insurance.getKey();

        head = new JLabel(insurance.getAttribute("name"), SwingConstants.CENTER);
        head.setPreferredSize(new Dimension(250, 100));
        head.setBackground(InfoPanel.infoAttribColor);
        head.setForeground(FrameHandler.HEADER_COLOR);
        head.setOpaque(true);
        head.setFont(new Font("Times New Roman", Font.BOLD, 15));
        head.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(head);

        JLabel empty1 = new JLabel(), empty2 = new JLabel();
        empty1.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        empty1.setPreferredSize(new Dimension(250, 30));
        this.add(empty1);
        empty2.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        empty2.setPreferredSize(new Dimension(250, 30));

        this.add(createTextFieldPanel("Company:", insurance.getAttribute("company")));
        this.add(createTextFieldPanel("Premium:", processAmount(insurance.getAttribute("premium"))));
        this.add(createTextFieldPanel("Amount:", processAmount(insurance.getAttribute("amount"))));
        this.add(createTextFieldPanel("Duration:", insurance.getAttribute("duration") + " years"));

        purchase = new NButton("Purchase", this);
        cancel = new NButton("Cancel", this);
        deselect = new NButton("Deselect", this);

        if(isPurchased)
            purchase.setEnabled(false);

        else
            cancel.setEnabled(false);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(purchase);
        buttonPanel.add(cancel);
        buttonPanel.add(deselect);
        buttonPanel.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        buttonPanel.setPreferredSize(new Dimension(250, 100));

        this.add(empty2);
        this.add(buttonPanel);
    }

    private JPanel createTextFieldPanel(String text, String value){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(250, 35));
        panel.setBackground(FrameHandler.BROWSE_PANEL_COLOR);

        NLabel label = new NLabel(text);
        label.setPreferredSize(new Dimension(60, 30));

        NLabel dataField = new NLabel(value);
        dataField.setPreferredSize(new Dimension(160, 30));
        dataField.setBorder(BorderFactory.createMatteBorder(1, 10, 3, 1, FrameHandler.LABEL_COLOR));
        dataField.setBackground(FrameHandler.LABEL_COLOR);
        dataField.setOpaque(true);

        panel.add(label);
        panel.add(dataField);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Purchase":
                cancel.setEnabled(true);
                purchase.setEnabled(false);
                Main.eventListener.buyInsurance(insuranceID);
                break;
            case "Cancel":
                purchase.setEnabled(true);
                cancel.setEnabled(false);
                Main.eventListener.cancelInsurance(insuranceID);
                break;
            case "Deselect":
                Main.eventListener.deselectDetails();
                break;
        }
    }

    private String processAmount(String amount){
        StringBuilder result = new StringBuilder(" Rs.");
        int count = 3;
        for(int i = amount.length() - 1; i >= 0; i--){
            result.insert(0, amount.charAt(i));
            count--;
            if(count == 0 && i != 0){
                result.insert(0, ",");
                count = 2;
            }
        }
        return result.toString();
    }
}

class UserDetailsPanel extends JPanel implements ActionListener{

    int id;
    ArrayList<JTextField> activeTextFields = new ArrayList<>();

    UserDetailsPanel(User user){
        super(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(250, 800));

        id = user.getKey();

        JLabel head = new JLabel("User ID: " + id, SwingConstants.CENTER);
        head.setPreferredSize(new Dimension(250, 100));
        head.setBackground(InfoPanel.infoAttribColor);
        head.setForeground(FrameHandler.HEADER_COLOR);
        head.setOpaque(true);
        head.setFont(new Font("Times New Roman", Font.BOLD, 25));
        head.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(head);

        JLabel empty1 = new JLabel(), empty2 = new JLabel();
        empty1.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        empty1.setPreferredSize(new Dimension(250, 30));
        this.add(empty1);
        empty2.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        empty2.setPreferredSize(new Dimension(250, 30));

        this.add(this.createTextFieldPanel("Name: ", activeTextFields));
        this.add(this.createTextFieldPanel("Age: ", activeTextFields));
        this.add(this.createTextFieldPanel("Gender: ", activeTextFields));
        this.add(this.createTextFieldPanel("Contact: ", activeTextFields));

        activeTextFields.get(0).setText(user.getAttribute("name"));
        activeTextFields.get(1).setText(user.getAttribute("age"));
        activeTextFields.get(2).setText(user.getAttribute("gender"));
        activeTextFields.get(3).setText(user.getAttribute("contact"));

        this.add(empty2);
        NButton update = new NButton("Update", this), cancel = new NButton("Cancel", this);
        this.add(update);
        this.add(cancel);
    }
    private JPanel createTextFieldPanel(String text, ArrayList<JTextField> al){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(250, 35));
        panel.setBackground(FrameHandler.BROWSE_PANEL_COLOR);

        NLabel label = new NLabel(text);
        label.setPreferredSize(new Dimension(60, 30));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(160, 30));
        textField.setBorder(BorderFactory.createMatteBorder(1, 10, 3, 1, FrameHandler.LABEL_COLOR));
        textField.setBackground(FrameHandler.LABEL_COLOR);
        textField.setForeground(FrameHandler.TEXT_COLOR);
        al.add(textField);

        panel.add(label);
        panel.add(textField);
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Update":
                EventListener.activeTextFields = activeTextFields;
                Main.eventListener.updateUserData(id);
                break;
            case "Cancel":
                Main.eventListener.deselectDetails();
                break;
        }
    }
}

class UserFunctionPanel extends JPanel{

    public UserFunctionPanel(User user){
        super(new FlowLayout(FlowLayout.LEADING, 10, 0));
        this.setBackground(FrameHandler.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(900, 60));

        JLabel wishLabel = new JLabel("Hello, " + user.getAttribute("name") + "!", SwingConstants.CENTER);
        wishLabel.setPreferredSize(new Dimension(250, 60));
        wishLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        wishLabel.setForeground(FrameHandler.HEADER_COLOR);
        this.add(wishLabel);

        JLabel empty = new JLabel();
        empty.setPreferredSize(new Dimension(20, 60));
        this.add(empty);

        NButton viewInsurances = new NButton("My Insurances");
        viewInsurances.setPreferredSize(new Dimension(130, 40));
        this.add(viewInsurances);

        NButton viewProfile = new NButton("My Profile");
        viewProfile.setPreferredSize(new Dimension(130, 40));
        this.add(viewProfile);

        if(user.isAdmin){
            NButton admin = new NButton("Admin Functions");
            admin.setPreferredSize(new Dimension(130, 40));
            this.add(admin);
        }

        NButton logout = new NButton("Logout");
        logout.setPreferredSize(new Dimension(130, 40));
        this.add(logout);
    }
}

class AdminCommandPanel extends JPanel{

    public AdminCommandPanel(){
        super(new FlowLayout(FlowLayout.CENTER, 10, 0));
        this.setBackground(FrameHandler.BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(900, 60));

        JLabel head = new JLabel("Admin Commands", SwingConstants.CENTER);
        head.setPreferredSize(new Dimension(250, 100));
        head.setBackground(InfoPanel.infoAttribColor);
        head.setForeground(FrameHandler.HEADER_COLOR);
        head.setOpaque(true);
        head.setFont(new Font("Times New Roman", Font.BOLD, 25));
        head.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(head);

        JLabel empty1 = new JLabel();
        empty1.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        empty1.setPreferredSize(new Dimension(250, 10));
        this.add(empty1);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setBackground(FrameHandler.BACKGROUND_COLOR);
        buttonPanel.setPreferredSize(new Dimension(250, 300));

        NButton addInsurance = new NButton("New Insurance");
        addInsurance.setPreferredSize(new Dimension(130, 40));
        buttonPanel.add(addInsurance);

        NButton addPlaceholderInsurances = new NButton("Temp Insurances");
        addPlaceholderInsurances.setPreferredSize(new Dimension(130, 40));
        buttonPanel.add(addPlaceholderInsurances);

        NButton viewUsers = new NButton("View Users");
        viewUsers.setPreferredSize(new Dimension(130, 40));
        buttonPanel.add(viewUsers);

        NButton back = new NButton("Go Back");
        back.setPreferredSize(new Dimension(130, 40));
        buttonPanel.add(back);

        this.add(buttonPanel);
    }
}
