import java.util.List;
public class Main {
        static class Book{
            String isbn;
            String title;

            Book(String isbn,String title){
                this.isbn = isbn;
                this.title = title;
            }
        }

        public static String findBook(List<Book> catalog, String targetIsbn){
            for(Book book : catalog){
                if(book.isbn.equals(targetIsbn)){
                    return book.title;
                }
            }

            return "Not Found";
        }

    public static void main(String[] args){
            List<Book> catalog = List.of(
                new Book("0001112223","Introduction to Algebra"),
                new Book("0002223334","Beginning Pyhton"),
                new Book("0003334445","Classic Mythology"),
                new Book("0004445556","Data and Society"),
                new Book("0005556667","European History")

            );

            System.out.println(findBook(catalog, "0003334445"));
            System.out.println(findBook(catalog, "0009998887"));
        }
}