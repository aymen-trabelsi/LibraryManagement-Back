package com.codewithGAS.livre.controller;
import com.codewithGAS.livre.entity.*;
import com.codewithGAS.livre.repository.EmprunteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
@CrossOrigin(allowedHeaders = "**")
public class EmprunteController {
    @Autowired
    private EmprunteRepository emprunteRepository;
    @Autowired
    private BookController bookController;
    @Autowired
    private RestTemplate restTemplate ;

    @GetMapping("/empruntes/all")
    public List<EmprunteDTO> getAllEmprunte() {
        List<Emprunte> Emp = emprunteRepository.findAll();
        List<EmprunteDTO> EmpDTO =  Emp.stream().map(emprunte -> {
            Student student = restTemplate.getForObject("http://localhost:9191/students/"+emprunte.getStudentId(),Student.class);
            EmprunteDTO empDto = new EmprunteDTO(emprunte.getId(),emprunte.getBookId(),emprunte.getStudentId(),emprunte.getDateEmprunte(),emprunte.getDatePrevue(),emprunte.getDateRetour()
            ,bookController.getBook(emprunte.getBookId()).getBookName(),student.getFirstName()+" "+student.getLastName());
            return empDto;
        }).collect(Collectors.toList());
        return EmpDTO;
    }

    @PostMapping("/empruntes/save")
    public Emprunte saveEmprunte(@RequestBody Emprunte emprunte) {
        Book b = bookController.getBook(emprunte.getBookId());
        b.setNbCopy(b.getNbCopy()-1);
        return emprunteRepository.save(emprunte);
    }
    @PutMapping("/empruntes/save")
    public Emprunte putEmprunte(@RequestBody Emprunte emprunte) {
        Emprunte e = emprunteRepository.getById(emprunte.getId());
        Book b = bookController.getBook(e.getBookId());
        b.setNbCopy(b.getNbCopy()+1);
        e.setDateRetour(emprunte.getDateRetour());
        return emprunteRepository.save(e);
    }

    @DeleteMapping("/empruntes/{id}")
    public void deleteEmprunte(@PathVariable("id") Long emprunteId) {  emprunteRepository.deleteById(emprunteId);  }


    @GetMapping("/all/{email}")
    public List<EmprunteStudentDTO> getAllBook(@PathVariable("email") String email) {

        Student s = restTemplate.getForObject("http://localhost:9191/students/email/"+email, Student.class);


        List<Emprunte> empruntes = emprunteRepository.getByStudentId(s.getStudentId());
        List<EmprunteStudentDTO> emprunteStudentDTOS = empruntes.stream().map(emprunte -> {
            EmprunteStudentDTO emprunteStudentDTO = new EmprunteStudentDTO(
                    bookController.getBook(emprunte.getBookId()).getBookName(),
                    emprunte.getDateEmprunte(),
                    emprunte.getDatePrevue(),
                    emprunte.getDateRetour(),
                    bookController.getBook(emprunte.getBookId()).getImg()
            );
            return emprunteStudentDTO;
        }).collect(Collectors.toList());
        return emprunteStudentDTOS;
    }


    // function to sort hashmap by values
    public static HashMap<Long, Integer> sortByValue(HashMap<Long, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Long, Integer> > list =
                new LinkedList<Map.Entry<Long, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Long, Integer> >() {
            public int compare(Map.Entry<Long, Integer> o1,
                               Map.Entry<Long, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Long, Integer> temp = new LinkedHashMap<Long, Integer>();
        for (Map.Entry<Long, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    @GetMapping("/empruntes/best")
    public List<Book> getBest(){
        List<Book> books = new ArrayList<Book>();
        HashMap<Long,Integer> idList = new HashMap<Long,Integer>();
        List<Emprunte> empruntes = emprunteRepository.findAll();
        empruntes = empruntes.stream().map(emprunte -> {
            if (!idList.containsKey(emprunte.getBookId())){
                idList.put(emprunte.getBookId(),1);
            }else {
                idList.put(emprunte.getBookId(),idList.get(emprunte.getBookId())+1);
            }
            return emprunte;
        }).collect(Collectors.toList());

        Map<Long, Integer> hm1 = sortByValue(idList);

        int count = 0;
        for (Map.Entry<Long, Integer> en : hm1.entrySet()) {
            books.add(bookController.getBook(en.getKey()));
            count +=1;
            if (count==3) break;
        }
        return books;
    }
}
