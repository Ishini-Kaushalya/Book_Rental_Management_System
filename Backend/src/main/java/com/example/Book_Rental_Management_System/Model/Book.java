package com.example.Book_Rental_Management_System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name ="Book")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "genre",nullable = false)
    private String genre;

    @Column(name = "available",nullable = false)
    private Boolean available = true;
}

