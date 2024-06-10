import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca 
{

    private ArrayList<Livro> biblioteca = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void adicionarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o gênero do livro: ");
        String genero = scanner.nextLine();

        Livro novoLivro = new Livro(titulo, autor, genero);
        biblioteca.add(novoLivro);
        System.out.println();
        System.out.println("Sucesso!!, Pressione Enter para voltar ao menu...");
        scanner.nextLine(); // aguarda a entrada do usuário
    }

    public void listarLivros() {
        if (biblioteca.isEmpty()) {
            System.out.println("Não há livros na biblioteca.");
        } else {
            System.out.println("Lista de Livros:");
            System.out.println(); 
            for (Livro livro : biblioteca) {
             
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Gênero: " + livro.getGenero());
                System.out.println(); // linha em branco entre os livros
            }
            System.out.println("Pressione Enter para voltar ao menu...");
            scanner.nextLine(); // aguarda a entrada do usuário
        }
    }

    public void removerLivro() {
        if (biblioteca.isEmpty()) {
            System.out.println("Não há livros para remover.");
        } else {
            System.out.println("Escolha o número do livro a ser removido:");
            for (int i = 0; i < biblioteca.size(); i++) {
                System.out.println((i + 1) + ". " + biblioteca.get(i).getTitulo());
            }
            int indice = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer
            if (indice > 0 && indice <= biblioteca.size()) {
                biblioteca.remove(indice - 1);
                System.out.println("Livro removido com sucesso!");
            } else {
                System.out.println("Número de livro inválido.");
            }
        }
    }
}

