package com.market.carrot.daangn.controller;

import com.market.carrot.daangn.domain.item.Book;
import com.market.carrot.daangn.domain.item.Item;
import com.market.carrot.daangn.service.ItemService;
import com.market.carrot.daangn.validation.ItemValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemValidator itemValidator;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@Validated @ModelAttribute("form") BookForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/createItemForm";
        }

        Book book = new Book();
        //createbook으로 설계
        //order처럼
        //set 사용 지양해야!

        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());



        itemService.saveItem(book);

        return "redirect:/items";
    }



    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

//    @PostMapping("items/{itemId}/edit")
//    public String updateItem(@ModelAttribute("form") BookFrom form) {
//        Book book = new Book();
//
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//
//        itemService.saveItem(book);
//
//        return "redirect:/items";
//    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @Validated @ModelAttribute("form") BookForm form, BindingResult bindingResult) {
//        Book book = new Book();
//
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/updateItemForm";
        }

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}
