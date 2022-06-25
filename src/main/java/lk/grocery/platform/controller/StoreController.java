package lk.grocery.platform.controller;

import lk.grocery.platform.dto.StoreDTO;
import lk.grocery.platform.response.SuccessResponse;
import lk.grocery.platform.response.SuccessResponseHandler;
import lk.grocery.platform.service.StoreService;
import lk.grocery.platform.service.VendorStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;
    private final VendorStoreService vendorStoreService;

    public StoreController(StoreService storeService,
                           VendorStoreService vendorStoreService) {
        this.storeService = storeService;
        this.vendorStoreService = vendorStoreService;
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

    @PostMapping("/{storeId}/vendor")
    public ResponseEntity<SuccessResponse> addVendorsToStore(@PathVariable("storeId") Long storeId, @RequestBody List<String> vendorList) {
        return SuccessResponseHandler.generateResponse(vendorStoreService.addVendorsToStore(storeId, vendorList));
    }

    @DeleteMapping("/{storeId}/vendor")
    public ResponseEntity<SuccessResponse> removeVendorsFromStore(@PathVariable("storeId") Long storeId, @RequestBody List<String> vendorList) {
        return SuccessResponseHandler.generateResponse(vendorStoreService.removeVendorsFromAStore(storeId, vendorList));
    }

    @GetMapping("/{storeId}/vendor")
    public ResponseEntity<SuccessResponse> getAllVendors(@PathVariable("storeId") Long storeId) {
        return SuccessResponseHandler.generateResponse(vendorStoreService.getVendorsByStoreId(storeId));
    }
}
