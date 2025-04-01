package com.example.demo.sample3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private int id;
    private String author;
    private String title;
    private int price;
    private int stock;
}
