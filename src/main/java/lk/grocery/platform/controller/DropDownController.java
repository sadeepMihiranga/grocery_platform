package lk.grocery.platform.controller;

import lk.grocery.platform.response.SuccessResponse;
import lk.grocery.platform.response.SuccessResponseHandler;
import lk.grocery.platform.service.DropDownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/select/list")
public class DropDownController {

    private final DropDownService dropDownService;

    public DropDownController(DropDownService dropDownService) {
        this.dropDownService = dropDownService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<SuccessResponse> getDropDownByCode(@PathVariable("code") String code) throws IOException {
        return SuccessResponseHandler.generateResponse(dropDownService.getDropDownByCode(code));
    }

    @GetMapping("/check")
    public ResponseEntity<SuccessResponse> getCodes() throws IOException {
        return SuccessResponseHandler.generateResponse(dropDownService.getDropDownCodes());
    }
}
