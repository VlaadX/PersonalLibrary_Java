public class Livro {
    private String titulo;
    private String autor;
    private String genero;
    private int volume;

    public Livro(String titulo, String autor, String genero, int volume) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.volume = volume;
    }

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }
    public int getVolume() { return volume; }
}
