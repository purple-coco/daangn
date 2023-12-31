package com.market.carrot.daangn.domain.form.item;

import com.market.carrot.daangn.domain.UploadFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long id;
    private String name;
    private String thumbnail;
    private int price;
    private String description;
    private String place;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
