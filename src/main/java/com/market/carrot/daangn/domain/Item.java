package com.market.carrot.daangn.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//한 테이블에 때려박기
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
      private Long id;

    //상속관계 mapping이 필요

    @NotBlank
    private String name;

    @Range(min = 1000, max = 1000000)
    private int price;

    @NotBlank
    private String description;

    @NotBlank
    private String place;

    private int interest_count;


    @OneToMany(mappedBy = "item", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    List<UploadFile> imageFiles = new ArrayList<>();

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();


    /**
     * 비즈니스 로직 구현
     * Entity 안에서 비즈니스 로직을 구현하는 게 객체지향적
     */

    /**
     * 상품 생성 메서드
     */
    public static Item createItem(String name, int price, String description, String place, List<UploadFile> imageFiles) {
        Item item = new Item();

        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
        item.setPlace(place);
        item.setImageFiles(imageFiles);

        return item;
    }

    /**
     * 좋아요 증가하는 로직
     */
    public void addLike() {
        this.interest_count++;
    }

    /**
     * 좋아요 감소하는 로직
     */
    public void removeLike() {
        this.interest_count--;
    }



}
