import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JScrollPane;
import java.security.PrivateKey;
import java.sql.SQLOutput;

public class Layout implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JLabel statusLabel1;
    private JLabel statusLabel2;
    private JTextArea ta; // Text area in the middle
    private JTextArea ta1;
    private JTextArea ta2;
    private JPanel gamePanel;
    private JPanel smallPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JScrollPane scroll;
    private JMenuItem cut, copy, paste, selectAll;
    private int WIDTH=800;
    private int HEIGHT=700;


    public Layout() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Layout Layout = new Layout();
        Layout.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Hard Example");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        // Adding JTextArea in the middle
        ta = new JTextArea();
        ta.setRows(3); // Adjust rows for size
        ta.setColumns(20); // Adjust columns for width


//            mb = new JMenuBar();
//            file = new JMenu("File");
//            edit = new JMenu("Edit");
//            help = new JMenu("Help");
//            edit.add(cut);
//            edit.add(copy);
//            edit.add(paste);
//            edit.add(selectAll);
//            mb.add(file);
//            mb.add(edit);
//            mb.add(help);
////            //end menu at top


//            mainFrame.add(ta);
//            mainFrame.add(mb);  //add menu bar
//            mainFrame.add(ta);//add typing area
//            mainFrame.setJMenuBar(mb); //set menu bar

        statusLabel = new JLabel("input a url", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        statusLabel1 = new JLabel("input a search term", JLabel.CENTER);
        statusLabel1.setSize(350, 100);

        statusLabel2 = new JLabel("Results", JLabel.CENTER);
        statusLabel2.setSize(350, 100);

        statusLabel.setFont(new Font("Silom", Font.PLAIN,14));
        statusLabel1.setFont(new Font("Silom", Font.PLAIN,14));
        statusLabel2.setFont(new Font("Silom", Font.PLAIN,14));



        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        gamePanel = new JPanel();
        smallPanel = new JPanel();

        gamePanel.setLayout(new GridLayout(7,1));
        smallPanel.setLayout(new GridLayout(1,2));
     //   mainFrame.add(scrollPane);

        ta = new JTextArea(); //A text area so the user can input an url
        ta.setBounds(300, 5, WIDTH-100, HEIGHT-100);

        ta1 = new JTextArea();  // A text area so the user can input a search term
        ta1.setBounds(300, 5, WIDTH-100, HEIGHT-100);

        ta2 = new JTextArea();  // A text area that has the links from the url that contain the search term displayed
        ta2.setBounds(300, 5, WIDTH-100, HEIGHT-100);

        mainFrame.add(gamePanel);
        mainFrame.setVisible(true);
    }

    public void showEventDemo() {

        JButton startButton = new JButton("Start");

        startButton.setActionCommand("Start");

        JButton resetButton = new JButton("Reset");

        resetButton.setActionCommand("Reset");

        startButton.addActionListener(new ButtonClickListener());

        resetButton.addActionListener(new ButtonClickListener());

        scroll = new JScrollPane(ta2);
        gamePanel.add(statusLabel);
        gamePanel.add(ta);
        gamePanel.add(statusLabel1);
        gamePanel.add(ta1);
        gamePanel.add(statusLabel2);
        gamePanel.add(scroll);
        gamePanel.add(smallPanel);
        smallPanel.add(resetButton);
        smallPanel.add(startButton);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Reset")) {
                ta.setText("");
                ta1.setText("");
                ta2.setText("");

            }
            if (command.equals("Start")) {
//                statusLabel.setText(");
                try {
                    System.out.println();
                    System.out.print("hello \n");
                    URL url = new URL(ta.getText());
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(url.openStream())
                    );
                    String line;
                    while ( (line = reader.readLine()) != null ) {
                        if (line.contains("href")) {

                            int start = line.indexOf("href")+6;
                            System.out.println(line);
                            //task: chop off the html before the https
                            System.out.println(ta.getText());
                            System.out.println(line.substring(start));
                            String link = line.substring(start);
                            int end = link.indexOf("\"");
                            int finish = link.indexOf("\'");
                            if (finish == -1) {
                                System.out.println("chop start:"+ link);
                                System.out.print("link "+ link.substring(0,end) + "\n");
                                if (link.substring(0,end).contains(ta1.getText())){
                                    ta2.append(link.substring(0,end) + "\n");
                                }
                                System.out.println(ta1.getText());

                            } else if(end == -1){
                                System.out.println("chop start:"+ link);
                                System.out.println("link "+ link.substring(0,finish));
                                if (link.substring(0,end).contains(ta1.getText())){
                                    ta2.append(link.substring(0,end) + "\n");
                                }
                            }
                            else if (finish > end) {
                                System.out.println("chop start:"+ link);
                                System.out.println("link "+ link.substring(0,end));
                                if (link.substring(0,end).contains(ta1.getText())){
                                    ta2.append(link.substring(0,end) + "\n");
                                }
                            } else {
                                System.out.println("chop start:"+ link);
                                System.out.println("link "+ link.substring(0,finish));
                                if (link.substring(0,end).contains(ta1.getText())){
                                    ta2.append(link.substring(0,end) + "\n");
                                }
                            }
                        }
                    }
                    reader.close();
                } catch(Exception ex) {
                    System.out.println(ex);
                    System.out.println("There's an error somewhere try again");
                    System.out.println("You messed up your link");
                    statusLabel.setText("You messed up your link");
                }
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Reset Button clicked.");
            }
        }
    }
}