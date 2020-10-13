import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements Dao{

    public Connection conn;
    List<Book> list;



    public BookDAO() {
        conn=DaoManager.getConnection();
        list=new ArrayList<>();


    }


    @Override
    public Book get(long id) {
        return null;
    }

    public String getAll() throws SQLException {
        ResultSet rs = null;
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("Select * from books");
        while (rs.next()) {
            int Id = rs.getInt("id");
            String nume = rs.getString("name_");
            String author = rs.getString("author");
            String publisheddate=rs.getString("publishedData");
            boolean isAvailable=rs.getBoolean("isAvailable");
            this.list.add(new Book(Id,nume,author,publisheddate,isAvailable));

        }
        return this.list.toString();
    }

    @Override
    public void addItemT(Book b) throws SQLException {
        try{
        String sql = "INSERT INTO books (id,name_,author, publishedData,isAvailable)VALUES(?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,b.getId());
        statement.setString(2,b.getName());
        statement.setString(3, b.getAuthor());
        statement.setString(4, b.getPublishedDate());
        statement.setBoolean(5, b.isAvailable());
        statement.executeUpdate();
    }catch(SQLException e){

    }

         this.list.add(b);

    }

    @Override
    public void removeT(Book b) throws SQLException
    {
        String query = "delete from books where id = ? and isAvailable=true";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1,b.getId());
        preparedStmt.execute();
        if(b.isAvailable())
            this.list.remove( b);



    }


}
