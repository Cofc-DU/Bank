// BankAppGUI.java
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BankAppGUI {
    Frame frame;
    CardLayout cardLayout;
    Panel mainPanel;

    TextField loginUsername, loginPassword;
    Label loginStatus;

    User currentUser = null;
    Admin currentAdmin = null;

    public BankAppGUI() {
        frame = new Frame("Bank App");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        mainPanel.add(buildLoginPanel(), "login");
        mainPanel.add(buildUserPanel(), "user");
        mainPanel.add(buildAdminPanel(), "admin");

        frame.add(mainPanel);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    private Panel buildLoginPanel() {
        Panel panel = new Panel(new GridLayout(5, 1));
        loginUsername = new TextField();
        loginPassword = new TextField();
        loginPassword.setEchoChar('*');
        loginStatus = new Label();

        Button userLogin = new Button("Login as User");
        Button adminLogin = new Button("Login as Admin");

        userLogin.addActionListener(e -> {
            User user = Admin.searchUserName(loginUsername.getText());
            if (user != null && user.getPassword().equals(loginPassword.getText())) {
                currentUser = user;
                cardLayout.show(mainPanel, "user");
            } else {
                loginStatus.setText("Invalid user credentials");
            }
        });

        adminLogin.addActionListener(e -> {
            Admin admin = Admin.searchUserNameA(loginUsername.getText());
            if (admin != null && admin.getPassword().equals(loginPassword.getText())) {
                currentAdmin = admin;
                cardLayout.show(mainPanel, "admin");
            } else {
                loginStatus.setText("Invalid admin credentials");
            }
        });

        panel.add(new Label("Username:"));
        panel.add(loginUsername);
        panel.add(new Label("Password:"));
        panel.add(loginPassword);
        panel.add(userLogin);
        panel.add(adminLogin);
        panel.add(loginStatus);
        return panel;
    }

    private Panel buildUserPanel() {
        Panel panel = new Panel(new BorderLayout());
        Label userInfo = new Label();
        Button logout = new Button("Logout");

        logout.addActionListener(e -> {
            currentUser = null;
            cardLayout.show(mainPanel, "login");
        });

        panel.add(userInfo, BorderLayout.CENTER);
        panel.add(logout, BorderLayout.SOUTH);

        Panel wrapper = new Panel() {
            public void paint(Graphics g) {
                if (currentUser != null) {
                    userInfo.setText("User: " + currentUser.toString());
                }
            }
        };

        wrapper.add(panel);
        return wrapper;
    }

    private Panel buildAdminPanel() {
        Panel panel = new Panel(new GridLayout(10, 2));

        TextField fName = new TextField();
        TextField lName = new TextField();
        TextField uName = new TextField();
        TextField pass = new TextField();
        TextField id = new TextField();

        Label status = new Label();
        Button add = new Button("Add User");
        Button remove = new Button("Remove User");
        Button showAll = new Button("Show All Users");
        Button logout = new Button("Logout");

        TextArea userList = new TextArea();

        add.addActionListener(e -> {
            try {
                Admin.addUser(fName.getText(), lName.getText(), uName.getText(), pass.getText(), Integer.parseInt(id.getText()));
                status.setText("User added.");
            } catch (Exception ex) {
                status.setText("Error adding user.");
            }
        });

        remove.addActionListener(e -> {
            try {
                status.setText(Admin.removeUser(Integer.parseInt(id.getText())));
            } catch (Exception ex) {
                status.setText("User not found.");
            }
        });

        showAll.addActionListener(e -> {
            userList.setText("");
            for (User u : Bank.userInfo.values()) {
                userList.append(u.toString() + "\n");
            }
        });

        logout.addActionListener(e -> {
            currentAdmin = null;
            cardLayout.show(mainPanel, "login");
        });

        panel.add(new Label("First Name:")); panel.add(fName);
        panel.add(new Label("Last Name:")); panel.add(lName);
        panel.add(new Label("Username:")); panel.add(uName);
        panel.add(new Label("Password:")); panel.add(pass);
        panel.add(new Label("Client ID:")); panel.add(id);
        panel.add(add); panel.add(remove);
        panel.add(showAll); panel.add(logout);
        panel.add(status); panel.add(new Label(""));
        panel.add(new Label("Users List:")); panel.add(userList);

        return panel;
    }

    public static void main(String[] args) {
        Bank.addAdmin("Derrick","Usry","Admin123","Password123", 1234 );
        new BankAppGUI();

    }
}
