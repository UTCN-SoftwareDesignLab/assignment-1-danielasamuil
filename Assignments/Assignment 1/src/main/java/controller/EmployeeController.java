package controller;

import model.Account;
import model.builder.AccountBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import service.account.AccountService;
import service.client.ClientService;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final AccountService accountService;
    private final ClientService clientService;

    public EmployeeController(EmployeeView employeeView, AccountService accountService, ClientService clientService) {

        this.employeeView = employeeView;
        this.employeeView.setVisible(false);

        this.accountService = accountService;
        this.clientService = clientService;

        employeeView.setCreateAccountButtonListener(new CreateAccountButtonListener());
        employeeView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
        employeeView.setDeleteAccountButtonListener(new DeleteAccountButtonListener());
        employeeView.setViewAccountButtonListener(new ViewAccountButtonListener());
        employeeView.setTransferButtonListener(new TransferButtonListener());

    }

    private class CreateAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String identificationNumber = employeeView.getIdentificationNumber();
            String type = employeeView.getTypeTxt();
            String amountOfMoney = employeeView.getAmountOfMoney();
            String creationDate = employeeView.getCreationDate();

            Account ac = new AccountBuilder()
                    .setIdentificationNumber(Integer.parseInt(identificationNumber))
                    .setType(type)
                    .setAmountOfMoney(Integer.parseInt(amountOfMoney))
                    .setCreationDate(LocalDate.parse(creationDate))
                    .build();

            Notification<Boolean> accountNotification = accountService.save(ac.getId(), ac.getIdentificationNumber(), ac.getType(), ac.getAmountOfMoney(), ac.getCreationDate());

            if (accountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), accountNotification.getFormattedErrors());
            } else {
                if (!accountNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account failed to be created");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account created with success");
                }
            }
        }
    }


    private class UpdateAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Account account = null;
            try {
                account = accountService.findById(Long.parseLong(employeeView.getId1()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            String identificationNumber = employeeView.getIdentificationNumber();
            String type = employeeView.getTypeTxt();
            String amountOfMoney = employeeView.getAmountOfMoney();
            String creationDate = employeeView.getCreationDate();

            Notification<Boolean> accountNotification = accountService.update(account.getId(), Integer.parseInt(identificationNumber), type, Integer.parseInt(amountOfMoney), LocalDate.parse(creationDate));

            if (accountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), accountNotification.getFormattedErrors());
            } else {
                if (!accountNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account failed to be updated");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account updated with success");
                }
            }
        }
    }

    private class DeleteAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Account account = null;
            try {
                account = accountService.findById(Long.parseLong(employeeView.getId1()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            Boolean b = accountService.remove(account.getId());

            if(b)
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account deleted with success");
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account failed to be deleted");

        }
    }

    private class ViewAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Account account = null;
            try {
                account = accountService.findById(Long.parseLong(employeeView.getId1()));
            } catch (
                    EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account information: Type: " + account.getType() + " , Creation Date: " + account.getCreationDate() + ", Amount of money: " + account.getAmountOfMoney() + ", identification nr: " + account.getIdentificationNumber());
        }
    }

    private class TransferButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String moneyAmountForTransfer = employeeView.getMoneyAmountForTransfer();

            Account account1 = null;
            Account account2 = null;

            try {
                account1 = accountService.findById(Long.parseLong(employeeView.getId1()));
                account2 = accountService.findById(Long.parseLong(employeeView.getId2()));
                accountService.transfer(account1,account2,Integer.parseInt(moneyAmountForTransfer));
            } catch (
                    EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    public void setVisible(boolean b) {
        employeeView.setVisible(b);
    }
}
