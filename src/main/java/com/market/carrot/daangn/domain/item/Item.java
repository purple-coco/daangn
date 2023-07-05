package com.market.carrot.daangn.domain.item;

import com.market.carrot.daangn.domain.Category;
import com.market.carrot.daangn.domain.UploadFile;
import com.market.carrot.daangn.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//한 테이블에 때려박기
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
      private Long id;

    //상속관계 mapping이 필요

    @NotBlank
    private String name;

    @Range(min = 1000, max = 1000000)
    private int price;

    @NotNull
    @Max(999)
    private int stockQuantity;

    private UploadFile attachFile;

    private List<UploadFile> imageFiles;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    /**
     * 비즈니스 로직 구현
     * Entity 안에서 비즈니스 로직을 구현하는 게 객체지향적
     */
    /**
     * 재고 수량 증가하는 로직
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 재고 수량 감소하는 로직
     * @param quantity
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("ness more stock");
        }

        this.stockQuantity = restStock;
    }


}
