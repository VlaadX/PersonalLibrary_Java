import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> biblioteca = new ArrayList<>();
    private JTextArea textArea;

    public Biblioteca() {
        JFrame frame = new JFrame("Minha Biblioteca Pessoal");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton botao1 = new JButton("Adicionar Livro");
        JButton botao2 = new JButton("Listar Livros");
        JButton botao3 = new JButton("Remover Livro");

        botao1.addActionListener(e -> adicionarLivro());
        botao2.addActionListener(e -> listarLivros());

        panel.add(botao1);
        panel.add(botao2);
        panel.add(botao3);

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void adicionarLivro() {
        JTextField tituloField = new JTextField(20);
        JTextField autorField = new JTextField(20);
        JTextField generoField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Livro",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String genero = generoField.getText();

            Livro livro = new Livro(titulo, autor, genero);
            biblioteca.add(livro);

            JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
        }
    }

    private void listarLivros() {
        StringBuilder builder = new StringBuilder();
        for (Livro livro : biblioteca) {
            builder.append("Título: ").append(livro.getTitulo()).append("\n");
            builder.append("Autor: ").append(livro.getAutor()).append("\n");
            builder.append("Gênero: ").append(livro.getGenero()).append("\n\n");
        }
        textArea.setText(builder.toString());
    }
}
