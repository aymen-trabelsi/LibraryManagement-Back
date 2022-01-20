package com.codewithGAS.livre.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter @Setter private String bookName;
    @Column
    @Getter @Setter private String bookAuthor;
    @Column
    @Getter @Setter private int nbCopy;
    @Column
    @Getter @Setter private String description;
    @Column
    @Getter @Setter private String img;


}
