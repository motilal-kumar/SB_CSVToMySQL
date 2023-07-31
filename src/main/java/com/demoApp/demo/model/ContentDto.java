package com.demoApp.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {


    private long id;


    private String title;


    private String email;


    private String type;


    private String state;

    private String author;

    private String description;

 //   private boolean published;

}
