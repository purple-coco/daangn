package com.market.carrot.daangn.controller;

import com.market.carrot.daangn.domain.UploadFile;
import com.market.carrot.daangn.domain.form.item.ItemForm;
import com.market.carrot.daangn.domain.Item;
import com.market.carrot.daangn.file.FileStore;
import com.market.carrot.daangn.repository.ItemRepository;
import com.market.carrot.daangn.service.ItemService;
import com.market.carrot.daangn.validation.ItemValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemValidator itemValidator;
    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@Validated @ModelAttribute("form") ItemForm form, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/createItemForm";
        }

        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        Item item = Item.createItem(form.getName(), form.getPrice(), form.getDescription(), form.getPlace(), storeImageFiles);

        itemService.saveItem(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }


    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item item = itemService.findOne(id);

        model.addAttribute("item", item);

        return "items/detailItemForm";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);

        ItemForm form = new ItemForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setDescription(item.getDescription());
        form.setPlace(item.getPlace());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }


    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @Validated @ModelAttribute("form") ItemForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/updateItemForm";
        }

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getDescription(), form.getPlace());

        return "redirect:/items";
    }
}
