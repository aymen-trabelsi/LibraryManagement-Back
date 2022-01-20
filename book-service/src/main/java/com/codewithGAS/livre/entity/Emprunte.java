package com.codewithGAS.livre.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "emprunte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprunte {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Column
    @Getter @Setter private Long bookId;
    @Column
    @Getter @Setter private Long studentId;
    @Column
    @Getter @Setter private Date dateEmprunte;
    @Column
    @Getter @Setter private Date datePrevue;
    @Column
    @Getter @Setter private Date dateRetour;
}
