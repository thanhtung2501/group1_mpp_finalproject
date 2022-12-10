package mpp.business.model;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
    private String isbn;
    private String bookCopyId;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double fines;
    private LocalDate finesDatePaid;

    public CheckoutRecordEntry(String isbn, String bookCopyId, LocalDate checkoutDate, LocalDate dueDate) {
        this.isbn = isbn;
        this.bookCopyId = bookCopyId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }
    public CheckoutRecordEntry(String isbn, String bookCopyId, LocalDate checkoutDate, LocalDate dueDate, Double fines, LocalDate finesDatePaid) {
        this.isbn = isbn;
        this.bookCopyId = bookCopyId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.fines = fines;
        this.finesDatePaid = finesDatePaid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public double getFines() {
        return fines;
    }

    public void setFines(double fines) {
        this.fines = fines;
    }

    public LocalDate getFinesDatePaid() {
        return finesDatePaid;
    }

    public void setFinesDatePaid(LocalDate finesDatePaid) {
        this.finesDatePaid = finesDatePaid;
    }

    public void setBookCopyId(String bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
