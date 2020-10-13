import java.util.Date;

public class Book {
    private int Id;
    private String name_;
    private String author;
    private String publishedDate;
    private boolean isAvailable;

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", name_='" + name_ + '\'' +
                ", author='" + author + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public Book(int Id, String name_, String author, String publishedDate, boolean isAvailable)
 {
     this.Id=Id;
     this.name_=name_;
     this.author=author;
     this.publishedDate=publishedDate;
     this.isAvailable=isAvailable;
 }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

