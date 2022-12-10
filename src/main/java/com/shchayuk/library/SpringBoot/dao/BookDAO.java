package com.shchayuk.library.SpringBoot.dao;


//@Component
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO Book (name, author, year) VALUES (?, ?, ?)",
//                book.getName(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book book) {
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
//                book.getName(), book.getAuthor(), book.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//    public List<Book> showLentBooks(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public void lendTheBook(int id, int personId) {
//        jdbcTemplate.update("UPDATE Book SET islent=true WHERE id=?", id);
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?",
//                personId, id);
//    }
//
//    public void releaseTheBook(int id) {
//        jdbcTemplate.update("UPDATE Book SET islent=false WHERE id=?", id);
//        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
//    }


}
