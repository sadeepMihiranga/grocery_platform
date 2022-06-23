package lk.grocery.platform.test;

import lk.grocery.platform.GroceryPlatformApplication;
import lk.grocery.platform.service.PartyService;
import lk.grocery.platform.dto.PartyDTO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GroceryPlatformApplication.class)
public class TestPartyServices {

    @Autowired
    private PartyService partyService;

    @Ignore
    @Test
    public void filterContactNoTest() {
        final PartyDTO partyDTO = partyService.getPartyByPartyCode("CC00000004");

        final List<String> cnmbl = partyDTO.getContactList()
                .stream()
                .filter(partyContactDTO -> partyContactDTO.getContactType().equals("CNMBL"))
                .map(partyContactDTO -> partyContactDTO.getContactNumber())
                .collect(Collectors.toList());

        cnmbl.forEach(System.out::println);
    }
}
