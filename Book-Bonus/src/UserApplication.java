import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserApplication {
    public static void main(String[] args) throws SQLException {

       BookDAO books=new BookDAO();
       System.out.println(books.getAll());
       Book a= new Book(6,"zbz","aga","5.06.1900",true);
       books.addItemT(a);
       books.addItemT(new Book (5,"Harap Alb","Ion Creanga","03.04.1877",true));
       books.removeT(a);

    }
}

