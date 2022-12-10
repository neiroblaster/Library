package com.shchayuk.library.SpringBoot.servicies;

import com.shchayuk.library.SpringBoot.models.Book;
import com.shchayuk.library.SpringBoot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PersonService personService;

    @Autowired
    public BookService(BookRepository bookRepository, PersonService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(int page, int itemsPerPage) {
        return bookRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }

    public List<Book> findAll(String year) {
        return bookRepository.findAll(Sort.by(year));
    }

    public List<Book> findAll(int page, int itemsPerPage, String year) {
        return bookRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(year))).getContent();
    }

    public Book show(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = bookRepository.findById(id).get();

        updatedBook.setId(id);
        // обработка с двух сторон отношений для поддержания кэша Hibernate актуальным
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void lendTheBook(int id, int personId) {
        Book book = show(id);
        book.setOwner(personService.show(personId));
        book.setLentDate(new Date());
    }

    @Transactional
    public void releaseTheBook(int id) {
        Book book = show(id);
        book.setOwner(null);
        book.setLentDate(null);
    }

    public Boolean checkIsTimeOver(Book book){
        Date currentDate = new Date();
        if (currentDate.getTime() - book.getLentDate().getTime() >= 864_000_000){
            return true;
        }
        return false;
    }

    public List<Book> findByOwnerId(int id) {
        List<Book> books = bookRepository.findByOwnerId(id);
        for (Book book : books){
            book.setOver(checkIsTimeOver(book));
        }
        return books;
    }

    public List<Book> findByNameIsStartingWith(String partName){
                return bookRepository.findByNameIsStartingWith(partName);
    }

}

