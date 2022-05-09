import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;
public class GameGUI extends JFrame implements ActionListener {
    JButton btnExitGame = new JButton("Back");
    JButton btnPlay = new JButton("Play");
    JLabel lblImage = new JLabel();
    JTextField txfLetter = new JTextField(1);
    String Gword;
    JLabel lblWord = new JLabel("************");
    String[] Words = {"GAMES", "WORK", "GIRL", "CONSTRUCTION", "HUMAN"};
    int Ecount = 0;
    int Rcount = 0;

    /**
     * Game Windows constructor.
     * Draws an image, a text field for an uppercase character, a hidden word, a button play and a button back.
     *
     *
     */
    public GameGUI() {
        setVisible(true);
        setSize(862,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        Gword = Words[(int)(Math.random() * 4)];

        //lblImage.setSize(130, 80);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco01.png")));
        txfLetter.setFont(new Font("Serif", Font.BOLD, 40));

        txfLetter.setText("   ");

        txfLetter.setDocument(new LimitJTF(1));

        DocumentFilter flt = new UppercaseJTextField();
        AbstractDocument docAbs = (AbstractDocument) txfLetter.getDocument();
        docAbs.setDocumentFilter(flt);

        lblWord.setFont(new Font("Serif", Font.BOLD, 40));

        add(lblImage);
        add(lblWord);
        add(txfLetter);
        add(btnPlay);
        add(btnExitGame);
        btnExitGame.addActionListener(this);
        btnPlay.addActionListener(this);
    }

    /**
     * Click functions to play or to exit the game.
     *
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae) {
        JButton btnActionSource = (JButton) ae.getSource();
        if(btnActionSource.equals(btnExitGame)) {
            dispose();
            MenuGUI Menu = new MenuGUI();
        }
        else if(btnActionSource.equals(btnPlay))
        {
            String letter = txfLetter.getText();
            ExistLetter(letter);
            if (Rcount == Gword.length())
            {
                JOptionPane.showMessageDialog(null, "You Win", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                MenuGUI Menu = new MenuGUI();
            }
        }

    }

    /**
     *
     * Checks if the writing letter is part of the selected word or not, and if the user write an already found letter
     *
     * @param l: writing letter
     */
    public void ExistLetter(String l){
        String[] letterOfWord = Gword.split("");
        String[] asteLabel = lblWord.getText().split("");
        String printL = "";
        boolean WordFound = false;

        for(int i = 0; i < Gword.length(); i++)
        {
            if(letterOfWord[i].equals(l) && !(asteLabel[i].equals(l))){
                asteLabel[i] = l;
                Rcount++;
                WordFound = true;
            }
            else if (asteLabel[i].equals(l) && WordFound == false) {
                JOptionPane.showMessageDialog(null, "Repeated Word", "Caution!!", JOptionPane.INFORMATION_MESSAGE);
                WordFound = true;
                Ecount++;
            }
            else if (i == Gword.length()-1 && WordFound == false)
                Ecount++;
        }

        switch (Ecount){
            case 0:
                break;
            case 1:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco02.png")));
                break;
            case 2:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco03.png")));
                break;

            case 3:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco04.png")));
                break;

            case 4:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco05.png")));
                break;

            case 5:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco06.png")));
                break;

            case 6:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco07.png")));
                break;

            case 7:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco08.png")));
                break;

            case 8:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco09.png")));
                break;

            case 9:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco10.png")));
                break;

            case 10:
                lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/muneco11.png")));
                JOptionPane.showMessageDialog(null, "You lose", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                MenuGUI Menu = new MenuGUI();
                break;
        }

        for(int i = 0; i < asteLabel.length; i++)
        {
            printL += asteLabel[i];
        }
        lblWord.setText(printL);
        txfLetter.setText("");

    }
}

class LimitJTF extends PlainDocument
{
    private int max;

    /**
     * Sets the max character for the text field.
     *
     * @param max
     */
    LimitJTF(int max) {
        super();
        this.max = max;
    }

    /**
     *
     * @param offset the starting offset &gt;= 0
     * @param text the string to insert; does nothing with null/empty strings
     * @param attr the attributes for the inserted content
     * @throws BadLocationException
     */
    public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
        if (text == null)
            return;
        if ((getLength() + text.length()) <= max) {
            super.insertString(offset, text, attr);
        }
    }
}
class UppercaseJTextField extends DocumentFilter
{
    /**
     *  Set the text field to uppercase.
     *
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset  the offset into the document to insert the content &gt;= 0.
     *    All positions that track change at or after the given location
     *    will move.
     * @param text the string to insert
     * @param attr the attributes to associate with the inserted
     *    content.  This may be null if there are no attributes.
     * @throws BadLocationException
     */
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        fb.insertString(offset, text.toUpperCase(), attr);
    }

    /**
     *
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset Location in Document
     * @param length Length of text to delete
     * @param text Text to insert, null indicates no text to insert
     * @param attrs AttributeSet indicating attributes of inserted text,
     *    null is legal.
     * @throws BadLocationException
     */
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        fb.replace(offset, length, text.toUpperCase(), attrs);
    }
}