package launcher;

import controller.LoginController;
import database.DBConnectionFactory;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthentificationService;
import service.user.AuthentificationServiceMySQL;
import view.LoginView;

import java.sql.Connection;

public class ComponentFactory {

    private final LoginView loginView;

    private final LoginController loginController;

    private final AuthentificationService authenticationService;
    private final ClientService clientService;
    private final AccountService accountService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepositoryMySQL clientRepositoryMySQL;
    private final AccountRepositoryMySQL accountRepositoryMySQL;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthentificationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.loginView = new LoginView();
        this.loginController = new LoginController(loginView, authenticationService);
        this.clientRepositoryMySQL = new ClientRepositoryMySQL(connection);
        this.accountRepositoryMySQL = new AccountRepositoryMySQL(connection);
        this.clientService = new ClientServiceImpl(clientRepositoryMySQL);
        this.accountService = new AccountServiceImpl(accountRepositoryMySQL);
    }

    public AuthentificationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public ClientRepositoryMySQL getClientRepositoryMySQL() {
        return clientRepositoryMySQL;
    }

    public AccountRepositoryMySQL getAccountRepositoryMySQL() { return accountRepositoryMySQL;}

    public LoginController getLoginController() {
        return loginController;
    }

    public static ComponentFactory getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
