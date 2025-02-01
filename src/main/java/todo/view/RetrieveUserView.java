package todo.view;

import javax.swing.*;
import java.awt.*;
import todo.controller.IUserController;
import todo.model.entity.User;

public class RetrieveUserView extends JFrame {
    private final IUserController userController;

    public RetrieveUserView(IUserController userController) {
        this.userController = userController;

        setTitle("Buscar Usuário");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Color bgColor = new Color(243, 244, 246); // Tailwind gray-100
        Color borderColor = new Color(209, 213, 219); // Tailwind gray-300
        Color primaryColor = new Color(59, 130, 246); // Tailwind blue-500
        Color textColor = new Color(31, 41, 55); // Tailwind gray-800

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createLineBorder(borderColor, 2));

        JLabel labelNome = new JLabel("Nome do Usuário:");
        labelNome.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNome, gbc);

        JTextField txtNome = new JTextField(20);
        txtNome.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        gbc.gridx = 1;
        panel.add(txtNome, gbc);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(primaryColor);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnBuscar, gbc);

        JTextArea userInfoArea = new JTextArea(5, 30);
        userInfoArea.setEditable(false);
        userInfoArea.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        JScrollPane scrollPane = new JScrollPane(userInfoArea);
        gbc.gridy = 2;
        panel.add(scrollPane, gbc);

        JButton btnUserView = new JButton("Ir para Cadastro");
        btnUserView.setBackground(new Color(16, 185, 129)); // Tailwind green-500
        btnUserView.setForeground(Color.WHITE);
        btnUserView.setFocusPainted(false);
        gbc.gridy = 3;
        panel.add(btnUserView, gbc);

        btnBuscar.addActionListener(e -> {
            String username = txtNome.getText().trim();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um nome de usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = userController.retrieveUser(username);
            if (user == null) {
                userInfoArea.setText("Usuário não encontrado!");
            } else {
                userInfoArea.setText("ID: " + user.getId() + "\n" +
                        "Nome: " + user.getName() + "\n" +
                        "Email: " + user.getEmail() + "\n" +
                        "Premium: " + (user.isPremium() ? "Sim" : "Não"));
            }
        });

        btnUserView.addActionListener(e -> {
            new UserView(userController);
        });

        add(panel);
        setVisible(true);
    }
}
