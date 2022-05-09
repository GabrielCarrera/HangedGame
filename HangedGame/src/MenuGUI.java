import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuGUI extends JFrame implements ActionListener {
    JButton btnNGame = new JButton("New Game");
    JButton btnSettings = new JButton("Settings");

    /**
     * Constructor of the main menu with the options "New Game" and "Settings"
     *
     */
    public MenuGUI () {
        setVisible(true);
        setSize(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JFrame Menu = this;

        add(btnNGame);
        add(btnSettings);

        btnNGame.addActionListener(this);
        btnSettings.addActionListener(this);

    }

    /**
     * Click function for start a new game or go to settings
     *
     *
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae) {
        JButton btnActionSource = (JButton) ae.getSource();
        if(btnActionSource.equals(btnNGame)) {
            dispose();

            GameGUI newGame = new GameGUI();
        }
        else if(btnActionSource.equals(btnSettings)) {
            dispose();
            ManageWordsGUI settings = new ManageWordsGUI();
        }

    }


}
