package com.market.carrot.daangn.service.validation;

import com.market.carrot.daangn.domain.item.Book;
import com.market.carrot.daangn.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Book book = new Book();

        book.setName(" ");
        book.setPrice(0);
        book.setStockQuantity(10000);
        book.setAuthor(" ");
        book.setIsbn(" ");

        Set<ConstraintViolation<Item>> violations = validator.validate(book);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
    }
}
