package view;

import javax.swing.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame {

    private JButton buttonCreateAccount;
    private JButton buttonUpdateAccount;
    private JButton buttonDeleteAccount;
    private JButton buttonViewAccount;
    private JButton buttonTransfer;

    //Acount info
    private JTextField identificationNumberText;
    private JTextField typeText;
    private JTextField amountOfMoneyTxt;
    private JTextField creationDateTxt;
    private JTextField accountId1;
    private JTextField accountId2;
    private JTextField moneyAmountForTransfer;

    public EmployeeView() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(identificationNumberText);
        add(typeText);
        add(amountOfMoneyTxt);
        add(creationDateTxt);
        add(accountId1);
        add(accountId2);
        add(moneyAmountForTransfer);
        add(buttonCreateAccount);
        add(buttonUpdateAccount);
        add(buttonDeleteAccount);
        add(buttonViewAccount);
        add(buttonTransfer);
    }

    public void initializeFields(){
        identificationNumberText = new JTextField("identification nr account");
        typeText = new JTextField("type account");
        amountOfMoneyTxt = new JTextField("money amount");
        creationDateTxt = new JTextField("creation date");
        accountId1 = new JTextField("id account1");
        accountId2 = new JTextField("id account2");
        moneyAmountForTransfer = new JTextField("amount transfer");
        buttonCreateAccount = new JButton("Create Account");
        buttonUpdateAccount = new JButton("Update Account");
        buttonDeleteAccount = new JButton("Delete Account");
        buttonViewAccount = new JButton("View Account");
        buttonTransfer = new JButton("Transfer");
    }

    public String getIdentificationNumber() {
        return identificationNumberText.getText();
    }

    public String getTypeTxt() { return typeText.getText();}

    public String getAmountOfMoney() { return amountOfMoneyTxt.getText();}

    public String getCreationDate() { return creationDateTxt.getText(); }

    public String getId1() { return accountId1.getText(); }

    public String getId2() { return accountId2.getText(); }

    public String getMoneyAmountForTransfer() { return moneyAmountForTransfer.getText(); }

    public void setCreateAccountButtonListener(ActionListener createAccountButtonListener) {
        buttonCreateAccount.addActionListener(createAccountButtonListener);
    }

    public void setUpdateAccountButtonListener(ActionListener updateAccountButtonListener) {
        buttonUpdateAccount.addActionListener(updateAccountButtonListener);
    }

    public void setDeleteAccountButtonListener(ActionListener deleteAccountButtonListener) {
        buttonDeleteAccount.addActionListener(deleteAccountButtonListener);
    }

    public void setViewAccountButtonListener(ActionListener viewAccountButtonListener) {
        buttonViewAccount.addActionListener(viewAccountButtonListener);
    }

    public void setTransferButtonListener(ActionListener transferAccountButtonListener) {
        buttonTransfer.addActionListener(transferAccountButtonListener);
    }
}
