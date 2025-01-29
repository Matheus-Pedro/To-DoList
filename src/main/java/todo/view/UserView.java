package todo.view;

import javax.swing.*;
import todo.controller.IUserController;

public class UserView extends JFrame {
    private final IUserController userController;

    public UserView(IUserController userController) {
        this.userController = userController;

        // Configuração da Janela
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(null); // Layout absoluto (pode usar outros como GridLayout)
        setResizable(false); // Desabilitar redimensionamento da janela

        // Criando componentes com estilo minimalista
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(40, 30, 80, 25);
        add(labelNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(120, 30, 200, 25);
        txtNome.setBorder(BorderFactory.createLineBorder(new java.awt.Color(28, 28, 28))); // Estilo minimalista
        add(txtNome);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(40, 70, 80, 25);
        add(labelEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(120, 70, 200, 25);
        txtEmail.setBorder(BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200))); // Estilo minimalista
        add(txtEmail);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(40, 110, 80, 25);
        add(labelSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 110, 200, 25);
        txtSenha.setBorder(BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200))); // Estilo minimalista
        add(txtSenha);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 160, 160, 40);
        btnSalvar.setBackground(new java.awt.Color(0, 123, 255)); // Cor de fundo do botão
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255)); // Cor do texto do botão
        btnSalvar.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 123, 255))); // Estilo do botão
        btnSalvar.setFocusPainted(false); // Remover borda ao clicar
        add(btnSalvar);

        // Adicionando a ação ao botão
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            // Chama o controller para criar o usuário
            int id = userController.createUser(nome, senha, email);

            if (id == -1) {
                JOptionPane.showMessageDialog(null, "ERRO: " + nome + " não pode ser salvo!");
            } else {
                // Exibe uma mensagem de confirmação
                JOptionPane.showMessageDialog(null, "Usuário " + nome + " salvo com sucesso!");
                // Limpa os campos
                txtNome.setText("");
                txtEmail.setText("");
                txtSenha.setText("");
            }
        });

        // Exibir a janela
        setVisible(true);
    }
}
