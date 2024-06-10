import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> biblioteca = new ArrayList<>();

    public Biblioteca() {
        JFrame frame = new JFrame("Minha Biblioteca Pessoal");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Seja bem-vindo à sua biblioteca pessoal", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(welcomeLabel, gbc);

        JButton botao1 = new JButton("Adicionar Livro");
        JButton botao2 = new JButton("Listar Livros");
        JButton botao3 = new JButton("Remover Livro");

        Dimension buttonSize = new Dimension(150, 50);
        botao1.setPreferredSize(buttonSize);
        botao2.setPreferredSize(buttonSize);
        botao3.setPreferredSize(buttonSize);

        botao1.addActionListener(e -> adicionarLivro());
        botao2.addActionListener(e -> listarLivros());
        botao3.addActionListener(e -> removerLivro());

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(botao1, gbc);

        gbc.gridy = 2;
        panel.add(botao2, gbc);

        gbc.gridy = 3;
        panel.add(botao3, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void adicionarLivro() {
        JTextField tituloField = new JTextField(20);
        JTextField autorField = new JTextField(20);
        JTextField generoField = new JTextField(20);
        JTextField volumeField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoField);
        panel.add(new JLabel("Volume:"));
        panel.add(volumeField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Livro",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String genero = generoField.getText();
            int volume = Integer.parseInt(volumeField.getText());

            Livro livro = new Livro(titulo, autor, genero, volume);
            biblioteca.add(livro);
            saveBookToDatabase(livro);

            JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
        }
    }

    private void saveBookToDatabase(Livro livro) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Livros (titulo, autor, genero, volume) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setInt(4, livro.getVolume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listarLivros() {
        new ListarLivrosGUI(loadBooksFromDatabase());
    }

    private List<Livro> loadBooksFromDatabase() {
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Livros")) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                int volume = rs.getInt("volume");
                livros.add(new Livro(titulo, autor, genero, volume));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    private void removerLivro() {
        String titulo = JOptionPane.showInputDialog("Digite o título do livro a ser removido:");
        if (titulo != null && !titulo.isEmpty()) {
            boolean encontrado = false;
            try (Connection conn = Database.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM Livros WHERE titulo = ?")) {
                stmt.setString(1, titulo);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
                    encontrado = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Livro não encontrado!");
            }
        }
    }
}
