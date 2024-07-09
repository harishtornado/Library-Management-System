package models;

public class Author {
    private static int id = 0;
    private int author_id;
    private String author_name;

    public Author(String author_name) {
        this.author_id = id++;
        this.author_name = author_name;
    }

    @Override
    public String toString() {
        return "Author [author_id=" + author_id + ", author_name=" + author_name + "]";
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

}
