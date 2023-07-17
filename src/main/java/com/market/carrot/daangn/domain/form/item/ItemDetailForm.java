package com.market.carrot.daangn.domain.form.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemDetailForm {

    private Long id;
    private String name;
    private int price;
    private String description;
    private String place;
    private int insert_count;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;

}
