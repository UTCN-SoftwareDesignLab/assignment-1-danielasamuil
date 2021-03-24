package view;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import view.DTOs.AccountDTO;
import view.DTOs.ClientDTO;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame {

    AccountDTO accountDTO;
    ClientDTO clientDTO;

    private JButton buttonCreateAccount;
    private JButton buttonUpdateAccount;
    private JButton buttonDeleteAccount;
    private JButton buttonViewAccount;
    private JButton buttonTransfer;

    private JButton buttonCreateClient;
    private JButton buttonUpdateClient;
    private JButton buttonDeleteClient;
    private JButton buttonViewClient;

    //Acount info
    private JTextField identificationNumberText;
    private JTextField typeText;
    private JTextField amountOfMoneyTxt;
    private JTextField creationDateTxt;
    private JTextField accountId1;
    private JTextField accountId2;
    private JTextField moneyAmountForTransfer;

    //Client info
    private JTextField clientIdTxt;
    private JTextField clientNameTxt;
    private JTextField clientIdentityCardNumberTxt;
    private JTextField clientAddressTxt;
    private JTextField clientPersonalNumericalCodeTxt;

    public EmployeeView() {
        setSize(800, 800);
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
        add(clientIdTxt);
        add(clientNameTxt);
        add(clientAddressTxt);
        add(clientIdentityCardNumberTxt);
        add(clientPersonalNumericalCodeTxt);
        add(buttonCreateAccount);
        add(buttonUpdateAccount);
        add(buttonDeleteAccount);
        add(buttonViewAccount);
        add(buttonTransfer);
        add(buttonCreateClient);
        add(buttonUpdateClient);
        add(buttonDeleteClient);
        add(buttonViewClient);
    }

    public void initializeFields() {
        this.clientDTO = new ClientDTO();
        this.accountDTO = new AccountDTO();
        identificationNumberText = new JTextField("identification nr account");
        typeText = new JTextField("type account");
        amountOfMoneyTxt = new JTextField("money amount");
        creationDateTxt = new JTextField("creation date");
        accountId1 = new JTextField("id account1");
        accountId2 = new JTextField("id account2");
        moneyAmountForTransfer = new JTextField("amount transfer");
        clientIdTxt = new JTextField("client ID");
        clientAddressTxt = new JTextField("client address");
        clientNameTxt = new JTextField("client name");
        clientIdentityCardNumberTxt = new JTextField("client identity card number");
        clientPersonalNumericalCodeTxt = new JTextField("client personal numerical code");
        buttonCreateAccount = new JButton("Create Account");
        buttonUpdateAccount = new JButton("Update Account");
        buttonDeleteAccount = new JButton("Delete Account");
        buttonViewAccount = new JButton("View Account");
        buttonTransfer = new JButton("Transfer");
        buttonCreateClient = new JButton("Create Client");
        buttonUpdateClient = new JButton("Update Client");
        buttonDeleteClient = new JButton("Delete Client");
        buttonViewClient = new JButton("View Client");
    }

    public String getIdentificationNumber() {
        return identificationNumberText.getText();
    }

    public String getTypeTxt() {
        return typeText.getText();
    }

    public String getAmountOfMoney() {
        return amountOfMoneyTxt.getText();
    }

    public String getCreationDate() {
        return creationDateTxt.getText();
    }

    public String getId1() {
        return accountId1.getText();
    }

    public String getId2() {
        return accountId2.getText();
    }

    public String getMoneyAmountForTransfer() {
        return moneyAmountForTransfer.getText();
    }

    public String getClientId() {
        return clientIdTxt.getText();
    }

    public String getClientName() {
        return clientNameTxt.getText();
    }

    public String getClientIdentityCardNumber() {
        return clientIdentityCardNumberTxt.getText();
    }

    public String getClientAddress() {
        return clientAddressTxt.getText();
    }

    public String getClientPersonalNumericalCode() {
        return clientPersonalNumericalCodeTxt.getText();
    }

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

    public void setCreateClientButtonListener(ActionListener createClientButtonListener) {
        buttonCreateClient.addActionListener(createClientButtonListener);
    }

    public void setUpdateClientButtonListener(ActionListener updateClientButtonListener) {
        buttonUpdateClient.addActionListener(updateClientButtonListener);
    }

    public void setDeleteClientButtonListener(ActionListener deleteClientButtonListener) {
        buttonDeleteClient.addActionListener(deleteClientButtonListener);
    }

    public void setViewClientButtonListener(ActionListener viewClientButtonListener) {
        buttonViewClient.addActionListener(viewClientButtonListener);
    }


    public AccountDTO getAccountDTO() {
        initializeAccountDTO();
        return accountDTO;
    }

    public ClientDTO getClientDTO() {
        initializeClientDTO();
        return clientDTO;
    }

    public void initializeAccountDTO() {

        this.accountDTO.setIdentificationNumber(Integer.parseInt(getIdentificationNumber()));
        this.accountDTO.setType(getTypeTxt());
        this.accountDTO.setAmountOfMoney(Integer.parseInt(getAmountOfMoney()));
        this.accountDTO.setCreationDate(LocalDate.parse(getCreationDate()));
    }

    public void initializeClientDTO() {

        this.clientDTO.setName(getClientName());
        this.clientDTO.setAddress(getClientAddress());
        this.clientDTO.setIdentityCardNumber(Integer.parseInt(getClientIdentityCardNumber()));
        this.clientDTO.setPersonalNumber(getClientPersonalNumericalCode());
    }
}
