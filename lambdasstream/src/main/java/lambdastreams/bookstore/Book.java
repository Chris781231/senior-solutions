package lambdastreams.bookstore;

public class Book {

    private final String title;
    private final int yearOfPublish;
    private final int price;
    private final int numberOfBook;

    public Book(String title, int yearOfPublish, int price, int numberOfBook) {
        this.title = title;
        this.yearOfPublish = yearOfPublish;
        this.price = price;
        this.numberOfBook = numberOfBook;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfBook() {
        return numberOfBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", yearOfPublish=" + yearOfPublish +
                ", price=" + price +
                ", numberOfBook=" + numberOfBook +
                '}';
    }
}
