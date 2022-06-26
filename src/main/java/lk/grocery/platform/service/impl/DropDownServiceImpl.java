package lk.grocery.platform.service.impl;

import com.google.common.base.Strings;
import lk.grocery.platform.dto.DropDownDTO;
import lk.grocery.platform.exception.InvalidDataException;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.repository.FunctionRepository;
import lk.grocery.platform.repository.RoleRepository;
import lk.grocery.platform.service.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DropDownServiceImpl implements DropDownService {

    private static final String BRANCHES = "BRANCH";
    private static final String PERMISSIONS = "PERMIS";
    private static final String ROLES = "ROLES";
    private static final String CUSTOMERS = "CUSTM";
    private static final String EMPLOYEES = "EMPLY";
    private static final String MEASUREMENTS_UNITS = "UOFMS";
    private static final String ITEM_TYPES = "ITMTP";
    private static final String PAYMENT_OPTIONS = "PAYOP";
    private static final String DELIVERY_OPTIONS = "DILOP";
    private static final String ITEM_CATEGORY_TYPES = "ITCTP";
    private static final String PARTY_TYPE = "PTYPE";
    private static final String ITEM_CATEGORIES = "ITCAT";
    private static final String ITEM_CATEGORIES_BY_ITEM_CATEGORY_TYPES = "ITBCT";
    private static final String ITEM_BRANDS = "ITBRD";

    private final BranchService branchService;
    private final PartyService partyService;
    private final CommonReferenceService commonReferenceService;
    private final PaymentOptionService paymentOptionService;
    private final DeliveryOptionService deliveryOptionService;
    private final ItemCategoryService itemCategoryService;
    private final ItemBrandService itemBrandService;

    private final FunctionRepository functionRepository;
    private final RoleRepository roleRepository;

    public DropDownServiceImpl(BranchService branchService,
                               PartyService partyService,
                               CommonReferenceService commonReferenceService,
                               PaymentOptionService paymentOptionService,
                               DeliveryOptionService deliveryOptionService,
                               ItemCategoryService itemCategoryService,
                               ItemBrandService itemBrandService,
                               FunctionRepository functionRepository,
                               RoleRepository roleRepository) {
        this.branchService = branchService;
        this.partyService = partyService;
        this.commonReferenceService = commonReferenceService;
        this.paymentOptionService = paymentOptionService;
        this.deliveryOptionService = deliveryOptionService;
        this.itemCategoryService = itemCategoryService;
        this.itemBrandService = itemBrandService;
        this.functionRepository = functionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Map<String, String> getDropDownCodes() {

        Map<String, String> dropDownCodes = new HashMap<>();

        dropDownCodes.put("PERMISSIONS", PERMISSIONS);
        dropDownCodes.put("ROLES", ROLES);
        dropDownCodes.put("CUSTOMERS", CUSTOMERS);
        dropDownCodes.put("EMPLOYEES", EMPLOYEES);
        dropDownCodes.put("MEASUREMENTS_UNITS", MEASUREMENTS_UNITS);
        dropDownCodes.put("PAYMENT_OPTIONS", PAYMENT_OPTIONS);
        dropDownCodes.put("DELIVERY_OPTIONS", DELIVERY_OPTIONS);
        dropDownCodes.put("ITEM_CATEGORY_TYPES", ITEM_CATEGORY_TYPES);
        dropDownCodes.put("PARTY_TYPE", PARTY_TYPE);
        dropDownCodes.put("ITEM_CATEGORIES", ITEM_CATEGORIES);
        dropDownCodes.put("ITEM_CATEGORIES_BY_ITEM_CATEGORY_TYPES", ITEM_CATEGORIES_BY_ITEM_CATEGORY_TYPES);
        dropDownCodes.put("ITEM_BRANDS", ITEM_BRANDS);

        return dropDownCodes;
    }

    @Override
    public List<DropDownDTO> getDropDownByCode(String code, String subCode) {

        if(Strings.isNullOrEmpty(code))
            throw new NoRequiredInfoException("Code is required");

        List<DropDownDTO> downDTOList = new ArrayList<>();

        switch (code) {
            case BRANCHES :
                List<DropDownDTO> branchList = downDTOList;
                branchService.getAllBranches().forEach(branchDTO -> {
                    branchList.add(new DropDownDTO(
                            branchDTO.getBranchId().toString(),
                            branchDTO.getMame(),
                            null,
                            branchDTO.getStatus()));
                });
                break;
            case PERMISSIONS :
                List<DropDownDTO> permissionList = downDTOList;
                functionRepository.findAll().forEach(tMsFunction -> {
                    permissionList.add(new DropDownDTO(
                            tMsFunction.getFuncId(),
                            tMsFunction.getDunsDescription(),
                            null,
                            tMsFunction.getFuncStatus()));
                });
                break;
            case ROLES :
                List<DropDownDTO> roleList = downDTOList;
                roleRepository.findAll().forEach(tMsRole -> {
                    roleList.add(new DropDownDTO(
                            tMsRole.getRoleName(),
                            tMsRole.getRoleName(),
                            tMsRole.getRoleDescription(),
                            tMsRole.getRoleStatus()));
                });
                break;
            case CUSTOMERS :
                List<DropDownDTO> customerList = downDTOList;
                partyService.getPartyListByType(CUSTOMERS).forEach(partyDTO -> {
                    customerList.add(new DropDownDTO(
                            partyDTO.getPartyCode(),
                            partyDTO.getName(),
                            null,
                            null
                    ));
                });
                break;
            case EMPLOYEES :
                List<DropDownDTO> roomList = downDTOList;
                partyService.getPartyListByType(EMPLOYEES).forEach(partyDTO -> {
                    roomList.add(new DropDownDTO(
                            partyDTO.getPartyCode(),
                            partyDTO.getName(),
                            null,
                            null
                    ));
                });
                break;
            case PAYMENT_OPTIONS :
                List<DropDownDTO> paymentOptions = downDTOList;
                paymentOptionService.getPaymentOptions().forEach(paymentOptionDTO -> {
                    paymentOptions.add(new DropDownDTO(
                            paymentOptionDTO.getPaymentOptionId().toString(),
                            paymentOptionDTO.getPaymentOptionName(),
                            null,
                            null
                    ));
                });
                break;
            case DELIVERY_OPTIONS :
                List<DropDownDTO> deliveryOptions = downDTOList;
                deliveryOptionService.getDeliveryOptions().forEach(deliveryOptionDTO -> {
                    deliveryOptions.add(new DropDownDTO(
                            deliveryOptionDTO.getDeliveryOptionId().toString(),
                            deliveryOptionDTO.getDeliveryOptionName(),
                            null,
                            null
                    ));
                });
                break;
            case ITEM_CATEGORIES :
                List<DropDownDTO> itemCategories = downDTOList;
                itemCategoryService.getAllItemCategories().forEach(itemCategoryDTO -> {
                    itemCategories.add(new DropDownDTO(
                            itemCategoryDTO.getItemCategoryId().toString(),
                            itemCategoryDTO.getItemCategoryName(),
                            null,
                            null
                    ));
                });
                break;
            case ITEM_CATEGORIES_BY_ITEM_CATEGORY_TYPES :
                List<DropDownDTO> itemCategoriesByType = downDTOList;
                itemCategoryService.getItemCategoriesByType(subCode).forEach(itemCategoryDTO -> {
                    itemCategoriesByType.add(new DropDownDTO(
                            itemCategoryDTO.getItemCategoryId().toString(),
                            itemCategoryDTO.getItemCategoryName(),
                            null,
                            null
                    ));
                });
                break;
            case ITEM_BRANDS :
                List<DropDownDTO> itemBrands = downDTOList;
                itemBrandService.getAllItemBrands().forEach(itemBrandDTO -> {
                    itemBrands.add(new DropDownDTO(
                            itemBrandDTO.getItemBrandId().toString(),
                            itemBrandDTO.getItemBrandName(),
                            null,
                            null
                    ));
                });
                break;
            case MEASUREMENTS_UNITS :
                downDTOList = populateFromCommonReference(MEASUREMENTS_UNITS);
                break;
            case ITEM_TYPES :
                downDTOList = populateFromCommonReference(ITEM_TYPES);
                break;
            case ITEM_CATEGORY_TYPES :
                downDTOList = populateFromCommonReference(ITEM_CATEGORY_TYPES);
                break;
            case PARTY_TYPE :
                downDTOList = populateFromCommonReference(PARTY_TYPE);
                break;
            default:
                throw new InvalidDataException("Requested Dropdown Code is invalid");
        }

        return downDTOList;
    }

    private List<DropDownDTO> populateFromCommonReference(String code) {

        List<DropDownDTO> downDTOList = new ArrayList<>();

        commonReferenceService.getAllByCmrtCode(code).forEach(commonReferenceDTO -> {
            downDTOList.add(new DropDownDTO(
                    commonReferenceDTO.getCmrfCode(),
                    commonReferenceDTO.getDescription(),
                    null,
                    null
            ));
        });

        return downDTOList;
    }
}
