package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        Account account = new Account();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id = " + id;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                account = getAccountFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }

        return account;
    }

    @Override
    public Account findByClientId(Long clientId) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client_account where client_id=" + clientId;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                Account ac = new AccountBuilder()
                        .setId(rs.getLong("account_id"))
                        .build();

                return findById(ac.getId());
            } else {
                throw new EntityNotFoundException(clientId, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(clientId, Account.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertStatement.setInt(1, account.getIdentificationNumber());
            insertStatement.setString(2, account.getType());
            insertStatement.setInt(3, account.getAmountOfMoney());
            insertStatement.setDate(4, new java.sql.Date(account.getCreationDate().toEpochDay()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id = " + id;
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE account SET identificationNumber = ?, type = ?, moneyAmount = ?, dateCreation = ? WHERE id = " + account.getId());
            updateStatement.setLong(1, account.getIdentificationNumber());
            updateStatement.setString(2, account.getType());
            updateStatement.setInt(3, account.getAmountOfMoney());
            updateStatement.setDate(4, new java.sql.Date(account.getCreationDate().toEpochDay()));
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setIdentificationNumber(rs.getInt("identificationNumber"))
                .setType(rs.getString("type"))
                .setAmountOfMoney(rs.getInt("moneyAmount"))
                .setCreationDate(LocalDate.ofEpochDay(rs.getDate("dateCreation").getTime()))
                .build();
    }

    @Override
    public void transfer(Account account1, Account account2, Integer amount) throws EntityNotFoundException {

        account1.setAmountOfMoney(account1.getAmountOfMoney() - amount);
        account2.setAmountOfMoney(account2.getAmountOfMoney() + amount);

        update(account1);
        update(account2);
    }
}
