package com.shchayuk.library.SpringBoot.dao;

//@Component
public class PersonDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index(){
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public void save(Person person){
//        jdbcTemplate.update("INSERT INTO Person (name, year) VALUES (?, ?)",
//                person.getName(), person.getYear());
//    }
//
//    //method for PersonValidator:
//    public Optional<Person> showPersonByName(String name){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public Person show(int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public Person showOwner(int id){
//        return jdbcTemplate.query("SELECT p.name FROM person p JOIN book b on p.id = b.person_id WHERE b.id=? ", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//
//    public void update(Person person, int id) {
//        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?", person.getName(),
//                person.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
}
