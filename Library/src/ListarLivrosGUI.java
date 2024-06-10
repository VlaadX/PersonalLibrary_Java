import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListarLivrosGUI extends JFrame {
    public ListarLivrosGUI(List<Livro> biblioteca) {
        setTitle("Lista de Livros");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder builder = new StringBuilder();
        for (Livro livro : biblioteca) {
            builder.append("Título: ").append(livro.getTitulo()).append("\n");
            builder.append("Volume: ").append(livro.getVolume()).append("\n");
            builder.append("Autor: ").append(livro.getAutor()).append("\n");
            builder.append("Gênero: ").append(livro.getGenero()).append("\n");
            builder.append("----------------------------------------").append("\n");
        }
        textArea.setText(builder.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        setVisible(true);
    }
}
