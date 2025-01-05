package ch.hevs.managedbeans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

import ch.hevs.bankservice.BorrowSessionBean;
import ch.hevs.businessobject.Book;

@Named
@SessionScoped
public class BorrowBean implements Serializable {

    @EJB
    private BorrowSessionBean borrowSession;
    
    @Inject
    private BooksBean booksBean;

    private Long selectedBookId;

    public Long getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(Long selectedBookId) {
        this.selectedBookId = selectedBookId;
    }

    public void addToBorrowList() {
        if (booksBean.getBook() == null) {
            throw new IllegalStateException("No book is currently selected.");
        }

        // Add the book's ID to the borrow list
        borrowSession.addBookToBorrow(booksBean.getBook().getIsbn());
    }

    // Remove a book from the borrow list
    public void removeFromBorrowList(String bookId) {
        borrowSession.removeBookFromBorrow(bookId);
    }

    // Get the borrow list
    public List<Book> getBorrowList() {
        return borrowSession.getBorrowList();
    }

    // Confirm borrowing
    public String confirmBorrowing() {
    	Long userId = (long) 2; // temproarly set id to 2
        borrowSession.confirmBorrowing(userId);
        return "borrowConfirmation.xhtml?faces-redirect=true"; // Redirect to confirmation page
    }
}
