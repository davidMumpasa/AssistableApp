package com.example.demo1.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DataApi {
    private String title;
    private String body;
    private int userId;

}
