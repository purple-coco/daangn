package com.market.carrot.daangn.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter @Setter
public class BookForm {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private int price;

    @NotNull
    @Max(value = 9999)
    private int stockQuantity;
    private String author;
    private String isbn;
}
