package service.user;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;

import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.EMPLOYEE;
import static database.Constants.Tables.ROLE;
import static database.Constants.Tables.USER;


public class AuthentificationServiceMySQL implements AuthentificationService {

        private final UserRepository userRepository;
        private final RightsRolesRepository rightsRolesRepository;

    public AuthentificationServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
            this.userRepository = userRepository;
            this.rightsRolesRepository = rightsRolesRepository;
        }

        @Override
        public Notification<Boolean> register(String username, String password) {
            if (username.equals("admin@application.com")) {
                Role role = rightsRolesRepository.findRoleByTitle(ADMINISTRATOR);
                User user = new UserBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .setRoles(Collections.singletonList(role))
                        .build();

                UserValidator userValidator = new UserValidator(user);
                boolean userValid = userValidator.validate();
                Notification<Boolean> userRegisterNotification = new Notification<>();

                if (!userValid) {
                    userValidator.getErrors().forEach(userRegisterNotification::addError);
                    userRegisterNotification.setResult(Boolean.FALSE);
                } else {
                    user.setPassword(encodePassword(password));
                    userRegisterNotification.setResult(userRepository.save(user));
                }
                return userRegisterNotification;
            } else {
                Role role = rightsRolesRepository.findRoleByTitle(EMPLOYEE);
                User user = new UserBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .setRoles(Collections.singletonList(role))
                        .build();

                UserValidator userValidator = new UserValidator(user);
                boolean userValid = userValidator.validate();
                Notification<Boolean> userRegisterNotification = new Notification<>();

                if (!userValid) {
                    userValidator.getErrors().forEach(userRegisterNotification::addError);
                    userRegisterNotification.setResult(Boolean.FALSE);
                } else {
                    user.setPassword(encodePassword(password));
                    userRegisterNotification.setResult(userRepository.save(user));
                }
                return userRegisterNotification;
            }
        }

        @Override
        public Notification<User> login(String username, String password) {
            return userRepository.findByUsernameAndPassword(username, encodePassword(password));
        }

        @Override
        public boolean logout(User user) {
            return false;
        }

        private String encodePassword(String password) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                StringBuilder hexString = new StringBuilder();

                for (byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }

                return hexString.toString();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
}