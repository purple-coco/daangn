package com.market.carrot.daangn.controller;

import com.market.carrot.daangn.domain.Member;
import com.market.carrot.daangn.domain.Order;
import com.market.carrot.daangn.domain.item.Item;
import com.market.carrot.daangn.repository.OrderSearch;
import com.market.carrot.daangn.service.ItemService;
import com.market.carrot.daangn.service.MemberService;
import com.market.carrot.daangn.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";

    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        //여러 개의 상품 주문할 수 있도록 수정 필요 +화면 수정
        orderService.order(memberId, itemId, count);

        //주문 확인으로 넘겨도 됨
        return "redirect:/orders";

    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
