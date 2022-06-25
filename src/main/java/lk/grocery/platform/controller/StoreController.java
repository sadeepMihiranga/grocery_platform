package lk.grocery.platform.controller;

import lk.grocery.platform.dto.StoreDTO;
import lk.grocery.platform.dto.UserDTO;
import lk.grocery.platform.response.SuccessResponse;
import lk.grocery.platform.response.SuccessResponseHandler;
import lk.grocery.platform.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createAStore(@RequestBody StoreDTO storeDTO) {
        return SuccessResponseHandler.generateResponse(storeService.createStore(storeDTO));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<SuccessResponse> getAStore(@PathVariable("storeId") Long storeId) {
        return SuccessResponseHandler.generateResponse(storeService.getStoreById(storeId));
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<SuccessResponse> updateStore(@PathVariable("storeId") Long storeId, @RequestBody StoreDTO storeDTO) {
        return SuccessResponseHandler.generateResponse(storeService.updateStore(storeId, storeDTO));
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<SuccessResponse> removeStore(@PathVariable("storeId") Long storeId) {
        return SuccessResponseHandler.generateResponse(storeService.removeStore(storeId));
    }

    @GetMapping("/search")
    public ResponseEntity<SuccessResponse> getPaginatedUsers(@RequestParam(name = "name", required = false) String name,
                                                             @RequestParam(name = "regNo", required = false) String regNo,
                                                             @RequestParam(name = "page", required = true) Integer page,
                                                             @RequestParam(name = "size", required = true) Integer size) {
        return SuccessResponseHandler.generateResponse(storeService.storePaginatedSearch(name, regNo, page, size));
    }
}
