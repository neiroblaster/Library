package com.shchayuk.library.SpringBoot.servicies;

import com.shchayuk.library.SpringBoot.models.Book;
import com.shchayuk.library.SpringBoot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PersonService personService;

    private List<Integer> batchOfPageNumbers;
    private int previousItemsPerPage = 10;

    @Autowired
    public BookService(BookRepository bookRepository, PersonService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

//    public Page<Book> findAll(Pageable pageable) {
//
//        int itemsPerPage = pageable.getPageSize();
//        int page = pageable.getPageNumber();
//
//        Page<Book> pageOfBooks = bookRepository.findAll(PageRequest.of(page,itemsPerPage));
//
//        double totalElements = pageOfBooks.getTotalElements();
//
//        batchOfPageNumbers = new ArrayList<>();
//
//        int lastBatch = (int) (Math.ceil(totalElements / itemsPerPage) * 10);
//        if (totalElements > itemsPerPage){
//            for (int i = 10; i <= lastBatch; i+=10){
//                batchOfPageNumbers.add(i);
//            }
//        }
//
//        return pageOfBooks;
//    }

    public Page<Book> findAll(Pageable pageable, Boolean fromBatch) {

        int page = pageable.getPageNumber();
        int itemsPerPage = pageable.getPageSize();
        Page<Book> pageOfBooks = bookRepository.findAll(PageRequest.of(page, itemsPerPage));
        long totalElements = pageOfBooks.getTotalElements();

        int startItem = page * itemsPerPage;

        // Если, например, со 2й страницы запросить большее число страниц -> пересчитываем startItem так,
        // чтобы отобразить ту страницу, на кот. находились, начиная с того же элемента:
        if (fromBatch && itemsPerPage > previousItemsPerPage){
            startItem = page * previousItemsPerPage;
        }
        if(itemsPerPage < totalElements)
        previousItemsPerPage = itemsPerPage;



        int lastBatch = (int) (Math.ceil((double) totalElements / itemsPerPage) * 10);

        if (batchOfPageNumbers == null) {
            batchOfPageNumbers = new ArrayList<>();
            int MAX_NUMBER_OF_BATCHES = 50;
            for (int i = 10; i <= lastBatch && i <= MAX_NUMBER_OF_BATCHES; i += 10) {
                batchOfPageNumbers.add(i);
            }
        }

        int toIndex = (int) Math.min(startItem + itemsPerPage, totalElements);
        List<Book> books = bookRepository.findAll().subList(startItem, toIndex);

        return new PageImpl<>(books, PageRequest.of(page, itemsPerPage), totalElements);
    }


    public List<Integer> getBatchOfPageNumbers() {
        return batchOfPageNumbers;
    }

//    public List<Book> findAll() {
//        return bookRepository.findAll();
//    }
//
//    public List<Book> findAll(int page, int itemsPerPage) {
//        return bookRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
//    }
//
//    public List<Book> findAll(String year) {
//        return bookRepository.findAll(Sort.by(year));
//    }
//
//    public List<Book> findAll(int page, int itemsPerPage, String year) {
//        return bookRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(year))).getContent();
//    }

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

    public Boolean checkIsTimeOver(Book book) {
        Date currentDate = new Date();
        if (currentDate.getTime() - book.getLentDate().getTime() >= 864_000_000) {
            return true;
        }
        return false;
    }

    public List<Book> findByOwnerId(int id) {
        List<Book> books = bookRepository.findByOwnerId(id);
        for (Book book : books) {
            book.setOver(checkIsTimeOver(book));
        }
        return books;
    }

    public List<Book> findByNameIsStartingWith(String partName) {
        return bookRepository.findByNameIsStartingWith(partName);
    }

}

