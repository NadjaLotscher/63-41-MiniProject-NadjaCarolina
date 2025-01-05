package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Book;

public interface BorrowSession {

    void addBookToBorrow(String bookId);

    void removeBookFromBorrow(String bookId);

    List<Book> getBorrowList();

    void confirmBorrowing();
}