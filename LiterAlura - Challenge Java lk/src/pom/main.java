import pom.git;

import java.util.List;
import java.util.Scanner;

public class ConsoleLiterAlura {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookApiClient apiClient = new BookApiClient();  // Cliente para consumir a API dos livros
    private static final BookDatabase bookDatabase = new BookDatabase();  // Banco de dados para armazenar e buscar livros

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Bem-vindo ao LiterAlura - Catálogo de Livros!");

        // Loop principal do menu
        while (running) {
            exibirMenu();

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    pesquisarPorAutor();
                    break;
                case 3:
                    filtrarPorTitulo();
                    break;
                case 4:
                    listarLivrosPopulares();
                    break;
                case 5:
                    System.out.println("Encerrando o programa. Até logo!");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nMenu de Opções:");
        System.out.println("1. Listar livros");
        System.out.println("2. Pesquisar por autor");
        System.out.println("3. Filtrar por título");
        System.out.println("4. Exibir livros mais populares");
        System.out.println("5. Sair");
    }

    private static void listarLivros() {
        System.out.println("\nLista de livros:");
        List<Book> books = apiClient.fetchBooks();  // Método que faz a requisição à API para obter a lista de livros

        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void pesquisarPorAutor() {
        System.out.print("\nDigite o nome do autor: ");
        String autor = scanner.nextLine();

        List<Book> books = bookDatabase.searchByAuthor(autor);  // Busca por autor no banco de dados
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o autor: " + autor);
        } else {
            System.out.println("Livros encontrados:");
            books.forEach(System.out::println);
        }
    }

    private static void filtrarPorTitulo() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scanner.nextLine();

        List<Book> books = bookDatabase.searchByTitle(titulo);  // Busca por título no banco de dados
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título: " + titulo);
        } else {
            System.out.println("Livros encontrados:");
            books.forEach(System.out::println);
        }
    }

    private static void listarLivrosPopulares() {
        System.out.println("\nLista de livros mais populares:");
        List<Book> books = bookDatabase.getMostPopularBooks();  // Recupera livros populares do banco de dados

        if (books.isEmpty()) {
            System.out.println("Nenhum livro popular encontrado.");
        } else {
            books.forEach(System.out::println);
        }
    }
}
