package ui;

import model.PasswordGenerator;
import model.exceptions.LengthNotEneteredException;
import model.exceptions.NegativeLengthException;
import model.exceptions.NoAllowedCharacterException;
import model.exceptions.TooLongLengthException;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
Represents the graphical user interface window of the password generator application
 */

public class GeneratorAppGUI extends JFrame {

    private static final int SPACING = 10;
    private static final int PANEL_HEIGHT = 50;
    private static final int MAIN_MENU_WIDTH = 800;
    private static final int MAIN_MENU_HEIGHT = 10 * PANEL_HEIGHT + 10 * SPACING;
    private static final int BUTTON_WIDTH = 60;
    private static final int POP_WINDOW_WIDTH = 200;
    private static final int POP_WINDOW_HEIGHT = 200;

    protected SpringLayout mainLayout;

    private ArrayList<JComponent> list;
    private JComponent menuMessagePanel;
    private JComponent passwordDisplayPanel;
    private JLabel passwordWindow;
    private JComponent passwordLengthInputPanel;
    private JTextField passwordLengthInputField;
    private JComponent characterTypesPanel;
    private JComponent lowerCaseLettersPanel;
    private JCheckBox lowerCaseLettersBox;
    private JComponent upperCaseLettersPanel;
    private JCheckBox upperCaseLettersBox;
    private JComponent numbersPanel;
    private JCheckBox numbersBox;
    private JComponent specialSymbolsPanel;
    private JCheckBox specialSymbolsBox;
    private JComponent buttonPanel;
    private JButton generateButton;
    private JButton copyButton;

    protected PasswordGenerator generator;


    // MODIFIES: this
    // EFFECTS: constructs the graphical user interface window of the application
    public GeneratorAppGUI() {

        generator = new PasswordGenerator();

        mainLayout = new SpringLayout();

        displayGUI();
    }

    // MODIFIES: this
    // EFFECTS: adds various components and displays the graphical user interface window
    public void displayGUI() {

        this.setTitle("Password Generator");
        setLayout(mainLayout);
        setPreferredSize(new Dimension(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        createPanels();
        createListOfPanels();
        renderPanels();
        addListener();
        pack();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates all the panels that will be added to the main window
    public void createPanels() {

        createMenuMessage();
        createDisplayPanel();
        createPasswordLengthPanel();
        createCharacterTypeMessage();
        createCharacterTypePanels();
        createButtonPanel();

    }

    // EFFECTS: creates the panel with the menu message
    public void createMenuMessage() {
        menuMessagePanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        menuMessagePanel.setLayout(layout);
        menuMessagePanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        JLabel message = new JLabel("<html>Welcome to Password Generator</html>");
        message.setFont(new Font("Dialog", Font.BOLD, 20));

        menuMessagePanel.add(message);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0,
                SpringLayout.HORIZONTAL_CENTER, menuMessagePanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0,
                SpringLayout.VERTICAL_CENTER, menuMessagePanel);
    }

    // EFFECTS: creates the panel where generated password is displayed
    public void createDisplayPanel() {
        passwordDisplayPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        passwordWindow = new JLabel("<html>Generated password will be displayed here</html>");

        passwordDisplayPanel.setLayout(layout);
        passwordDisplayPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT * 2));
        passwordWindow.setPreferredSize(new Dimension(MAIN_MENU_WIDTH - SPACING * 2, PANEL_HEIGHT * 2));
        passwordWindow.setBackground(Color.WHITE);
        passwordWindow.setOpaque(true);


        passwordDisplayPanel.add(passwordWindow);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordWindow, 0,
                SpringLayout.HORIZONTAL_CENTER, passwordDisplayPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordWindow, 0,
                SpringLayout.VERTICAL_CENTER, passwordDisplayPanel);


    }

    // EFFECTS: creates the panel where user can enter the password length
    public void createPasswordLengthPanel() {
        passwordLengthInputPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        passwordLengthInputPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        JLabel label = new JLabel("Password Length (up to 100 characters, inclusive):", SwingConstants.RIGHT);
        passwordLengthInputField = new JTextField();
        passwordLengthInputField.setPreferredSize(new Dimension(60, 30));

        passwordLengthInputPanel.add(label);
        passwordLengthInputPanel.add(passwordLengthInputField);

        layout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, passwordDisplayPanel);
        layout.putConstraint(SpringLayout.WEST, label, SPACING, SpringLayout.WEST, passwordDisplayPanel);
        layout.putConstraint(SpringLayout.NORTH, passwordLengthInputField, 0, SpringLayout.NORTH, passwordDisplayPanel);
        layout.putConstraint(SpringLayout.WEST, passwordLengthInputField, SPACING, SpringLayout.EAST, label);
    }

    // EFFECTS: creates the panel with a message
    public void createCharacterTypeMessage() {

        characterTypesPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        characterTypesPanel.setLayout(layout);
        characterTypesPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        JLabel message = new JLabel("<html>Allowed Character Types:</html>");
        message.setFont(new Font("Dialog", Font.BOLD, 16));

        characterTypesPanel.add(message);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0,
                SpringLayout.HORIZONTAL_CENTER, characterTypesPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0,
                SpringLayout.VERTICAL_CENTER, characterTypesPanel);

    }

    // EFFECTS: creates the panels with the allowed character types
    public void createCharacterTypePanels() {

        createLowerCaseLettersPanel();
        createUpperCaseLettersPanel();
        createNumbersPanel();
        createSpecialSymbolsPanel();

    }

    // EFFECTS: creates the panel with a checkbox that can enable or disable lower case letters
    public void createLowerCaseLettersPanel() {

        lowerCaseLettersPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        lowerCaseLettersPanel.setLayout(layout);
        lowerCaseLettersPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        lowerCaseLettersBox = new JCheckBox("Lower Case Letters");
        lowerCaseLettersBox.setHorizontalTextPosition(SwingConstants.LEFT);

        lowerCaseLettersPanel.add(lowerCaseLettersBox);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lowerCaseLettersBox, 0,
                SpringLayout.HORIZONTAL_CENTER, lowerCaseLettersPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lowerCaseLettersBox, 0,
                SpringLayout.VERTICAL_CENTER, lowerCaseLettersPanel);

    }

    // EFFECTS: creates the panel with a checkbox that can enable or disable upper case letters
    public void createUpperCaseLettersPanel() {

        upperCaseLettersPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        upperCaseLettersPanel.setLayout(layout);
        upperCaseLettersPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        upperCaseLettersBox = new JCheckBox("Upper Case Letters");
        upperCaseLettersBox.setHorizontalTextPosition(SwingConstants.LEFT);

        upperCaseLettersPanel.add(upperCaseLettersBox);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, upperCaseLettersBox, 0,
                SpringLayout.HORIZONTAL_CENTER, upperCaseLettersPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, upperCaseLettersBox, 0,
                SpringLayout.VERTICAL_CENTER, upperCaseLettersPanel);

    }

    // EFFECTS: creates the panel with a checkbox that can enable or disable numbers
    public void createNumbersPanel() {

        numbersPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        numbersPanel.setLayout(layout);
        numbersPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        numbersBox = new JCheckBox("Numbers");
        numbersBox.setHorizontalTextPosition(SwingConstants.LEFT);

        numbersPanel.add(numbersBox);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, numbersBox, 0,
                SpringLayout.HORIZONTAL_CENTER, numbersPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, numbersBox, 0,
                SpringLayout.VERTICAL_CENTER, numbersPanel);

    }

    // EFFECTS: creates the panel with a checkbox that can enable or disable special characters
    public void createSpecialSymbolsPanel() {

        specialSymbolsPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        specialSymbolsPanel.setLayout(layout);
        specialSymbolsPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        specialSymbolsBox = new JCheckBox("Special Symbols");
        specialSymbolsBox.setHorizontalTextPosition(SwingConstants.LEFT);

        specialSymbolsPanel.add(specialSymbolsBox);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, specialSymbolsBox, 0,
                SpringLayout.HORIZONTAL_CENTER, specialSymbolsPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, specialSymbolsBox, 0,
                SpringLayout.VERTICAL_CENTER, specialSymbolsPanel);

    }

    // EFFECTS: creates the panel that contains the button
    public void createButtonPanel() {

        buttonPanel = new JPanel();
        SpringLayout layout = new SpringLayout();

        buttonPanel.setLayout(layout);
        buttonPanel.setPreferredSize(new Dimension(MAIN_MENU_WIDTH, PANEL_HEIGHT));

        generateButton = new JButton("Generate password");
        generateButton.setMinimumSize(new Dimension(BUTTON_WIDTH, PANEL_HEIGHT));

        copyButton = new JButton("Copy password");
        copyButton.setMinimumSize(new Dimension(BUTTON_WIDTH, PANEL_HEIGHT));

        buttonPanel.add(generateButton);

        layout.putConstraint(SpringLayout.WEST, generateButton, 230,
                SpringLayout.WEST, buttonPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, generateButton, 0,
                SpringLayout.VERTICAL_CENTER, buttonPanel);

        buttonPanel.add(copyButton);

        layout.putConstraint(SpringLayout.WEST, copyButton, 20,
                SpringLayout.EAST, generateButton);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, copyButton, 0,
                SpringLayout.VERTICAL_CENTER, buttonPanel);


    }

    // MODIFIES: this
    // EFFECTS: puts all the panels onto the main window at the corresponding positions
    public void renderPanels() {

        for (int i = 0; i < list.size(); i++) {

            JComponent component = list.get(i);

            this.add(component);

            mainLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component, 0,
                    SpringLayout.HORIZONTAL_CENTER, this);

            if (i <= 1) {

                mainLayout.putConstraint(SpringLayout.NORTH, component, SPACING * (i + 1) + PANEL_HEIGHT * i,
                        SpringLayout.NORTH, this);

            } else {

                mainLayout.putConstraint(SpringLayout.NORTH, component, SPACING * (i + 1) + PANEL_HEIGHT * (i + 1),
                        SpringLayout.NORTH, this);

            }

        }

    }

    // MODIFIES: this
    // EFFECTS: creates a list of all the panels that will be added to the main window
    public void createListOfPanels() {

        list = new ArrayList<>();
        list.add(menuMessagePanel);
        list.add(passwordDisplayPanel);
        list.add(passwordLengthInputPanel);
        list.add(characterTypesPanel);
        list.add(lowerCaseLettersPanel);
        list.add(upperCaseLettersPanel);
        list.add(numbersPanel);
        list.add(specialSymbolsPanel);
        list.add(buttonPanel);

    }

    // MODIFIES: this
    // EFFECTS: adds the listener to the two buttons
    public void addListener() {

        generateButton.addActionListener(new GeneratePasswordListener());
        copyButton.addActionListener(new CopyPasswordListener());
    }

    private class GeneratePasswordListener implements ActionListener {

        /*
        Represents the listener for the button that upon being clicked, will generate a new password
         */

        // MODIFIES: GeneratorAPPGUI
        // EFFECTS: gathers all the inputs to finalize the list of all the allowed characters;
        //          generates a new password from those characters and displays it;
        //          throws NoAllowedCharacterException when no characters are allowed but the password
        //          length is strictly between 0 and 100, not including the endpoints
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                getInputs();
                generator.finalizeAllowedCharacters();
                passwordWindow.setText(generator.generate());
                passwordWindow.setFont(new Font("Dialog", Font.PLAIN, 12));
            } catch (NoAllowedCharacterException e1) {
                new NoAllowedCharacterMessageWindow();
            }
        }

    }

    private class CopyPasswordListener implements ActionListener {

        /*
        Represents the listener for the button that upon being clicked, will copy the generated password
        to the clipboard
         */

        // EFFECTS: copies the generated password to the clipboard for the user to paste
        @Override
        public void actionPerformed(ActionEvent e) {

            StringSelection copiedPassword = new StringSelection(passwordWindow.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(copiedPassword, copiedPassword);
        }

    }

    // MODIFIES: this
    // EFFECTS:
    public void getInputs() {

        try {
            generator.setPasswordLength(stringToInt(passwordLengthInputField));
            getInputFromCheckBoxes();
            generator.finalizeAllowedCharacters();
        } catch (LengthNotEneteredException e1) {
            new LengthNotEnteredMessageWindow();
        } catch (NegativeLengthException e2) {
            new PasswordLengthMessageWindow();
        } catch (TooLongLengthException e3) {
            new PasswordLengthMessageWindow();
        }

    }

    // MODIFIES: this
    // EFFECTS:
    public void getInputFromCheckBoxes() {

        getInputFromLowerCaseLettersBox();
        getInputFromUpperCaseLettersBox();
        getInputFromNumbersBox();
        getInputFromSpecialSymbolsBox();

    }

    // MODIFIES: this
    // EFFECTS:
    public void getInputFromLowerCaseLettersBox() {
        if (lowerCaseLettersBox.isSelected()) {
            generator.enableLowerCaseLetters();
        } else {
            generator.disableLowerCaseLetters();
        }
    }

    // MODIFIES: this
    // EFFECTS:
    public void getInputFromUpperCaseLettersBox() {
        if (upperCaseLettersBox.isSelected()) {
            generator.enableUpperCaseLetters();
        } else {
            generator.disableUpperCaseLetters();
        }
    }

    // MODIFIES: this
    // EFFECTS:
    public void getInputFromNumbersBox() {
        if (numbersBox.isSelected()) {
            generator.enableNumbers();
        } else {
            generator.disableNumbers();
        }
    }

    // MODIFIES: this
    // EFFECTS:
    public void getInputFromSpecialSymbolsBox() {
        if (specialSymbolsBox.isSelected()) {
            generator.enableSpecialSymbols();
        } else {
            generator.disableSpecialSymbols();
        }
    }


    // EFFECTS: returns the int representation of the string from the text field;
    //          throws LengthNotEnteredException when nothing is entered into the text field
    public int stringToInt(JTextField field) throws LengthNotEneteredException {
        String text = field.getText();
        if (text.equals("")) {
            throw new LengthNotEneteredException();
        } else {
            int length = Integer.parseInt(text);
            return length;
        }
    }

    private abstract class WarningWindow extends JDialog {

        /*
        Represents a generic warning window displaying a given message
         */

        protected SpringLayout layout = new SpringLayout();

        // MODIFIES: this
        // EFFECTS: constructs and displays the warning window with the given message
        public WarningWindow(JLabel message) {

            this.setLayout(layout);
            this.setMinimumSize(new Dimension(POP_WINDOW_WIDTH, POP_WINDOW_HEIGHT));
            this.setPreferredSize(new Dimension(POP_WINDOW_WIDTH, POP_WINDOW_HEIGHT));

            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            message.setMinimumSize(new Dimension(POP_WINDOW_WIDTH, POP_WINDOW_HEIGHT));
            message.setPreferredSize(new Dimension(POP_WINDOW_WIDTH, POP_WINDOW_HEIGHT));

            this.add(message);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0,
                    SpringLayout.HORIZONTAL_CENTER, this);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0,
                    SpringLayout.VERTICAL_CENTER, this);

            setVisible(true);

        }

    }

    private class PasswordLengthMessageWindow extends WarningWindow {

        /*
        Represents the warning window that will be displayed when the user wants to generate a password but
        the entered length is either negative or greater than 100
         */

        // MODIFIES: this
        // EFFECTS: constructs the warning window
        public PasswordLengthMessageWindow() {

            super(new JLabel("<html>Password length must be between 0 and 100. Please re-enter the " +
                    "length.</html>"));

        }
    }

    private class LengthNotEnteredMessageWindow extends WarningWindow {

        /*
        Represents the warning window that will be displayed when the user wants to generate a password but
        the password length is not entered
         */

        // MODIFIES: this
        // EFFECTS: constructs the warning window
        public LengthNotEnteredMessageWindow() {

            super(new JLabel("<html>Please enter a password length between 0 and 100.</html>"));

        }
    }

    private class NoAllowedCharacterMessageWindow extends WarningWindow {

        /*
        Represents the warning window that will be displayed when the user wants to generate a password with
        length greater than 0 but disables all 4 character types
         */

        // MODIFIES: this
        // EFFECTS: constructs the warning window
        public NoAllowedCharacterMessageWindow() {

            super(new JLabel("<html>All of the character types are not selected. A password cannot be " +
                    "generate. Please select at least one character type.</html>"));

        }
    }


}