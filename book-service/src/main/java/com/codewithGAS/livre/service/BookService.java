package com.codewithGAS.livre.service;


import com.codewithGAS.livre.entity.Book;
import com.codewithGAS.livre.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Properties;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
//   @Autowired
//   private RestTemplate restTemplate;


    public Book saveBook(Book book){
        return  bookRepository.save(book);
    }

    public  Book findBookById(Long bookID) {
        return bookRepository.findBookById(bookID);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long bookId ) {
        Book book=bookRepository.getById(bookId);
         bookRepository.delete(book);
    }

    public Book updateBook(Long bookId) {
        Book bookToUpdate = bookRepository.getById(bookId);
        bookToUpdate.setBookName(bookToUpdate.getBookName());
        bookToUpdate.setBookAuthor(bookToUpdate.getBookAuthor());
        bookToUpdate.setNbCopy(bookToUpdate.getNbCopy());
        bookToUpdate.setDescription(bookToUpdate.getDescription());
        return bookRepository.save(bookToUpdate);

    }

//    public ResponseTemplateVO getLivreWithEtudiant(Long id){
//        ResponseTemplateVO vo =new ResponseTemplateVO();
//        Book l=bookRepository.findByBookId(id);
//        Etudiant e=restTemplate.getForObject("http://USER-SERVICE/etudiants/"+ l.getEtudiantId(), Etudiant.class);
//       vo.setLivre(l);
//       vo.setEtudiant(e);
//       return  vo;
//    }

}
