package com.moezguer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String surname;

    public Author (String name, String surname){
        this.name = name;
        this.surname = surname;
    }

}
