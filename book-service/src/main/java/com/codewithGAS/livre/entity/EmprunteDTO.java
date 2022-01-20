package com.codewithGAS.livre.entity;
import java.util.Date;

public class EmprunteDTO {

    private Long id;
    private Long bookId;
    private Long studentId;
    private Date dateEmprunte;
    private Date datePrevue;
    private Date dateRetour;
    private String bookName;
    private String studentName;

    public EmprunteDTO(Long id,Long bookId, Long studentId, Date dateEmprunte, Date datePrevue, Date dateRetour, String bookName, String studentName) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateEmprunte = dateEmprunte;
        this.datePrevue = datePrevue;
        this.dateRetour = dateRetour;
        this.bookName = bookName;
        this.studentName = studentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Date getDateEmprunte() {
        return dateEmprunte;
    }

    public void setDateEmprunte(Date dateEmprunte) {
        this.dateEmprunte = dateEmprunte;
    }

    public Date getDatePrevue() {
        return datePrevue;
    }

    public void setDatePrevue(Date datePrevue) {
        this.datePrevue = datePrevue;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
