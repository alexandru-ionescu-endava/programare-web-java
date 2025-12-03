package com.example.repository;

import com.example.model.AuthorStats;
import com.example.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepository {

    @Autowired
    private DataSource dataSource;


    public List<Book> badExample() {
        List<Book> result = new ArrayList<>();

        String input = "test_title'; DELETE FROM book; --";
        String sql = "select * from book where title = '" + input + "'";

        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt("id"))
                        .author(rs.getString("author"))
                        .title(rs.getString("title"))
                        .pages(rs.getInt("pages"))
                        .build();

                result.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Book> findAllExtended() {
        List<Book> result = new ArrayList<>();
        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            PreparedStatement ps = connection.prepareStatement("select * from book");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt("id"))
                        .author(rs.getString("author"))
                        .title(rs.getString("title"))
                        .pages(rs.getInt("pages"))
                        .build();

                result.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



    private final JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                Book.builder()
                        .id(rs.getInt("id"))
                        .author(rs.getString("author"))
                        .title(rs.getString("title"))
                        .pages(rs.getInt("pages"))
                        .build());
    }

    public void insertBook(Book book) {
//        update este pentru query care intorc rezultate
        String sql = "INSERT INTO book (author, title, pages) VALUES (?, ?, ?)";
        int rows = jdbcTemplate.update(sql, book.getAuthor(), book.getTitle(), book.getPages());
        System.out.println("affected rows  = " + rows);
    }

    public Book findById(Integer id) {
        String sql = "SELECT * FROM book WHERE id = ?";
//        query for object intoarce un singur rezultat
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        Book.builder()
                                .id(rs.getInt("id"))
                                .author(rs.getString("author"))
                                .title(rs.getString("title"))
                                .pages(rs.getInt("pages"))
                                .build(),
                id);
    }

    public List<AuthorStats> groupByAuthor() {
        String sql = """
                select author, count(*)
                from book
                group by author
                """;

        return jdbcTemplate.query(sql, ((rs, rowNum) ->
                AuthorStats.builder()
                        .author(rs.getString("author"))
                        .count(rs.getInt(2))
                        .build()
        ));
    }

    public List<Book> findAllByPages(int pages) {
        String sql = "select * from book where pages > ?";

        return jdbcTemplate.query(conn -> {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, pages);
                    return ps;
                },
                (rs, rowNum) ->
                        Book.builder()
                                .pages(rs.getInt("pages"))
                                .author(rs.getString("author"))
                                .title(rs.getString("title"))
                                .id(rs.getInt("id"))
                                .build()
        );
    }
}
