import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MainFrame extends JFrame {

    public JPanel masterPanel, topPanel, middlePanel, middleMasterPanel, rightPanel, msgPanel;
    private static final JLabel msgLabel = new JLabel();

    MainFrame(){
        super();
        this.setBounds(300, 100, 900, 600);
        this.setTitle("Insurance Management System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel emptyRight = new JPanel();
        emptyRight.setPreferredSize(new Dimension(250, 800));
        emptyRight.setBackground(FrameHandler.BACKGROUND_COLOR);

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

        this.add(masterPanel);
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

class MiniFrame extends JFrame{
    MiniFrame(){
        super();
        this.setBounds(500, 250, 400, 200);
        this.setTitle("Insurance Management System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static final Color TEXT_COLOR = new Color(188, 208, 105);
    public static final Color HEAD_COLOR = new Color(169, 190, 52);
    public NLabel(String text){
        super(text);
        this.setForeground(TEXT_COLOR);
    }
    public NLabel(String text, int fontSize){
        super(text);
        this.setForeground(HEAD_COLOR);
        this.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
    }
}

class InsurancePanel extends JPanel implements MouseListener{
    public static Color insuranceAttribColor = new Color(87,87,92);
    public static Color insuranceTextColor = new Color(138, 199, 219);

    int insuranceID;
    JLabel id, name, company, premium, amount, duration;

    InsurancePanel(int id_, Insurance ins){
        super(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(625, 28));

        insuranceID = ins.getKey();

        name = new JLabel(ins.getAttribute("name"), SwingConstants.CENTER);
        company = new JLabel(ins.getAttribute("company"), SwingConstants.CENTER);
        premium = new JLabel(ins.getAttribute("premium"), SwingConstants.CENTER);
        amount = new JLabel(ins.getAttribute("amount"), SwingConstants.CENTER);
        duration = new JLabel(ins.getAttribute("duration"), SwingConstants.CENTER);
        id = new JLabel(id_+"", SwingConstants.CENTER);

        id.setPreferredSize(new Dimension(25, 25));
        name.setPreferredSize(new Dimension(250, 25));
        company.setPreferredSize(new Dimension(75,25));
        premium.setPreferredSize(new Dimension(75,25));
        amount.setPreferredSize(new Dimension(100, 25));
        duration.setPreferredSize(new Dimension(100, 25));

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
        label.setBackground(insuranceAttribColor);
        label.setForeground(insuranceTextColor);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, FrameHandler.BROWSE_PANEL_COLOR));
        this.add(label);
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
    public static Color infoTextColor = new Color(51, 139, 168);

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
        l1.setPreferredSize(new Dimension(250, 40));
        l2.setPreferredSize(new Dimension(75, 40));
        l3.setPreferredSize(new Dimension(75,40));
        l4.setPreferredSize(new Dimension(100,40));
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
        label.setForeground(infoTextColor);
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
    JLabel head, company, premium, amount, duration;
    JPanel buttonPanel;
    NButton purchase, cancel, deselect;

    InsuranceDetailsPanel(Insurance insurance, boolean isPurchased){
        super(new FlowLayout(FlowLayout.CENTER, 10, 5));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(250, 800));

        insuranceID = insurance.getKey();

        head = new JLabel(insurance.getAttribute("name"), SwingConstants.CENTER);
        head.setPreferredSize(new Dimension(250, 100));
        head.setBackground(InfoPanel.infoAttribColor);
        head.setForeground(InfoPanel.infoTextColor);
        head.setOpaque(true);
        head.setFont(new Font("Times New Roman", Font.BOLD, 15));
        head.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.add(head);

        JPanel empty = new JPanel();
        empty.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        empty.setPreferredSize(new Dimension(250, 20));
        this.add(empty);

        company = new JLabel("Company: "+insurance.getAttribute("company"), SwingConstants.CENTER);
        premium = new JLabel("Premium: "+insurance.getAttribute("premium"), SwingConstants.CENTER);
        amount = new JLabel("Amount: "+insurance.getAttribute("amount"), SwingConstants.CENTER);
        duration = new JLabel("Duration: "+insurance.getAttribute("duration"), SwingConstants.CENTER);

        initLabel(company);
        initLabel(premium);
        initLabel(amount);
        initLabel(duration);

        purchase = new NButton("Purchase", this);
        cancel = new NButton("Cancel", this);
        deselect = new NButton("Deselect", this);

        if(isPurchased){
            purchase.setEnabled(false);
            cancel.setEnabled(true);
        }
        else{
            purchase.setEnabled(true);
            cancel.setEnabled(false);
        }
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(purchase);
        buttonPanel.add(cancel);
        buttonPanel.add(deselect);
        buttonPanel.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        buttonPanel.setPreferredSize(new Dimension(250, 100));
        this.add(buttonPanel);
    }

    private void initLabel(JLabel label){
        label.setPreferredSize(new Dimension(250, 30));
        label.setForeground(InsurancePanel.insuranceTextColor);
        label.setBackground(InsurancePanel.insuranceAttribColor);
        label.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        label.setOpaque(true);

        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Purchase":
                Main.eventListener.buyInsurance(insuranceID);
                break;
            case "Cancel":
                Main.eventListener.cancelInsurance(insuranceID);
                break;
            case "Deselect":
                Main.eventListener.deselectInsurance();
                break;
        }
    }
}
