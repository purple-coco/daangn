package com.market.carrot.daangn.validation;

import com.market.carrot.daangn.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        //파라미터로 넘어오는 클래스가 item에 지원되냐
        //자식 클래스도 통과 가능
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item form = (Item) target;

        if (!StringUtils.hasText(form.getName())) {
//            bindingResult.addError(new FieldError("form", "name", form.getName(), false, new String[]{"required.item.itemName"}, null, null));
            errors.rejectValue("name", "required");
        }




        if (form.getPrice() < 1000 || form.getPrice() > 1000000) {
//            bindingResult.addError(new FieldError("form", "price", form.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 100000}, null));
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);

        }



        if (form.getStockQuantity() > 1000) {
//            bindingResult.addError(new FieldError("form", "stockQuantity", form.getStockQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{999}, null));
            errors.rejectValue("stockQuantity", "max", new Object[]{9999}, null);
        }


    }
}
