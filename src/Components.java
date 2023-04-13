import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

    public JPanel masterPanel, topPanel, middlePanel, middleMasterPanel, rightPanel, msgPanel;
    private static JLabel msgLabel;

    MainFrame(){
        super();
        this.setBounds(300, 100, 900, 600);
        this.setTitle("Insurance Management System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        msgLabel = new JLabel();
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
        rightPanel.setPreferredSize(new Dimension(250, 900));
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

class InsurancePanel extends JPanel{
    public static Color insuranceAttribColor = new Color(87,87,92);
    public static Color insuranceTextColor = new Color(138, 199, 219);

    JLabel id, name, company, premium, amount, duration;

    InsurancePanel(int id_, Insurance ins){
        super(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(625, 25));

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
        label.setBorder(BorderFactory.createLineBorder(FrameHandler.BROWSE_PANEL_COLOR, 1));

        this.add(label);
    }
}

class InfoPanel extends JPanel{
    public static Color infoAttribColor = new Color(67,67,75);
    public static Color infoTextColor = new Color(51, 139, 168);

    JLabel id, l1, l2, l3, l4, l5;

    InfoPanel(String text1, String text2, String text3, String text4, String text5){
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(FrameHandler.BROWSE_PANEL_COLOR);
        this.setPreferredSize(new Dimension(625, 40));

        id = new JLabel("ID", SwingConstants.CENTER);
        l1 = new JLabel(text1, SwingConstants.CENTER);
        l2 = new JLabel(text2, SwingConstants.CENTER);
        l3 = new JLabel(text3, SwingConstants.CENTER);
        l4 = new JLabel(text4, SwingConstants.CENTER);
        l5 = new JLabel(text5, SwingConstants.CENTER);

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
        label.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        this.add(label);
    }
}
