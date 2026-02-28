import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GPanel extends JFrame {

    private JTextField lengthField;
    private JCheckBox useNumbersCheckBox;
    private JCheckBox useSymbolsCheckBox;
    private JCheckBox useSpecialSymbolsCheckBox;
    private JLabel resultLabel;

    public GPanel() {
        setTitle("Password generator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 480);


        JPanel panel = new JPanel();
        panel.setLayout(null);


        JLabel lengthLabel = new JLabel("Password length: ");
        lengthLabel.setBounds(10, 20, 120, 25);
        panel.add(lengthLabel);

        lengthField = new JTextField("12");
        lengthField.setBounds(130, 20, 50, 25);
        panel.add(lengthField);

        useNumbersCheckBox = new JCheckBox("  Includes numbers", true);
        useNumbersCheckBox.setBounds(5, 60, 150, 25);
        panel.add(useNumbersCheckBox);

        useSymbolsCheckBox = new JCheckBox("  Includes symbols", true);
        useSymbolsCheckBox.setBounds(5, 100, 150, 25);
        panel.add(useSymbolsCheckBox);

        useSpecialSymbolsCheckBox = new JCheckBox("  Includes special symbols", true);
        useSpecialSymbolsCheckBox.setBounds(5, 140, 250, 25);
        panel.add(useSpecialSymbolsCheckBox);

        resultLabel = new JLabel("Here will be your password");
        resultLabel.setBounds(10, 180, 450, 25);
        panel.add(resultLabel);

        JButton generateButton = new JButton("Generate Password");
        generateButton.setBounds(5, 405, 200, 30);
        panel.add(generateButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = generatePassword();
                resultLabel.setText(password);
            }
        });


        add(panel);
        setVisible(true);
    }

    private String generatePassword() {

        StringBuilder allowedChars = new StringBuilder();


        if (useNumbersCheckBox.isSelected()) {
            allowedChars.append("0123456789");
        }
        if (useSymbolsCheckBox.isSelected()) {
            allowedChars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        }
        if (useSpecialSymbolsCheckBox.isSelected()) {
            allowedChars.append("!@#$%^&*()_+-=[]{}|;:,.<>?/");
        }

        if (allowedChars.length() == 0) { // если галочки не стоят то только символы
            allowedChars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        }

        int length;
        try {
            length = Integer.parseInt(lengthField.getText().trim());
            if (length <= 0) {
                length = 8;
            }
        } catch (NumberFormatException e) {
            length = 8; // в случае неправильно ввода дефолтная длина пароля - 8
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder(length); // длина length сразу указывается для выделения памяти
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }
}
