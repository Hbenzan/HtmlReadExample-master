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
import java.security.PrivateKey;

public class Layout implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JLabel statusLabel1;
    private JLabel statusLabel2;
    private JTextArea ta; // Text area in the middle
    private JTextArea ta1;
    private JTextArea ta2;
    private JPanel gamePanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
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


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(7,1));

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

        startButton.addActionListener(new ButtonClickListener());

        JTextArea ta = new JTextArea();
        JTextArea ta1 = new JTextArea();
        JTextArea ta2 = new JTextArea();

        gamePanel.add(startButton);
        gamePanel.add(statusLabel);
        gamePanel.add(ta);
        gamePanel.add(statusLabel1);
        gamePanel.add(ta1);
        gamePanel.add(statusLabel2);
        gamePanel.add(ta2);

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

            if (command.equals("Start")) {
//                statusLabel.setText(");
                try {
                    System.out.println();
                    System.out.print("hello \n");
                    URL url = new URL("https://www.milton.edu/");
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(url.openStream())
                    );
                    String line;
                    while ( (line = reader.readLine()) != null ) {
                        if (line.contains("href")) {

                            int start = line.indexOf("href")+6;
                            System.out.println(line);
                            //task: chop off the html before the https
                            System.out.println(line.substring(start));
                            String link = line.substring(start);
                            int end = link.indexOf("\"");
                            System.out.println("chop start:"+ link);
                            System.out.println("link "+ link.substring(7,end));
                        }
                    }
                    reader.close();
                } catch(Exception ex) {
                    System.out.println(ex);
                    System.out.println("There's an error somewhere try again");
                }
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
}
