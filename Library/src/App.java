import java.util.Scanner;

public class App 
{

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception 
    {

        Biblioteca biblioteca = new Biblioteca();


        int opcao;

        do
        { System.out.println("Seja Bem-Vindo a sua Biblioteca Pessoal!!!");
            System.out.println("---------------------------------------------");
            System.out.println("Menu:");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Listar Livro");
            System.out.println("3 - Remover Livro");
            System.out.println("4 - Sair!!");
            System.out.println("---------------------------------------------");
            opcao = scanner.nextInt();

            switch (opcao) 
            {
                case 1:
                    biblioteca.adicionarLivro();
                    limparTela();
                    break;
                case 2:
                    biblioteca.listarLivros();
                    limparTela();
                    break;
                   
                case 3:
                    biblioteca.removerLivro();
                    limparTela();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);
    }

    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Se ocorrer algum erro ao limpar a tela, apenas continue
        }
    }
}
