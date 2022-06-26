package lk.grocery.platform.controller;

import lk.grocery.platform.response.SuccessResponse;
import lk.grocery.platform.response.SuccessResponseHandler;
import lk.grocery.platform.service.ItemCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemCategoryService itemCategoryService;

    public ItemController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

}
