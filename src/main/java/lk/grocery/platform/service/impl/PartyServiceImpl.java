package lk.grocery.platform.service.impl;

import lk.grocery.platform.exception.DataNotFoundException;
import lk.grocery.platform.exception.InvalidDataException;
import lk.grocery.platform.exception.OperationException;
import lk.grocery.platform.exception.TransactionConflictException;
import lk.grocery.platform.repository.BranchRepository;
import lk.grocery.platform.repository.NumberGeneratorRepository;
import lk.grocery.platform.repository.PartyRepository;
import lk.grocery.platform.dto.CommonReferenceDTO;
import lk.grocery.platform.dto.PaginatedEntity;
import lk.grocery.platform.dto.PartyContactDTO;
import lk.grocery.platform.dto.PartyDTO;
import lk.grocery.platform.entity.TMsParty;
import lk.grocery.platform.entity.TRfBranch;
import lk.grocery.platform.mapper.PartyMapper;
import lk.grocery.platform.service.CommonReferenceService;
import lk.grocery.platform.service.PartyContactService;
import lk.grocery.platform.service.PartyService;
import lk.grocery.platform.service.UserService;
import lk.grocery.platform.util.constant.CommonReferenceCodes;
import lk.grocery.platform.util.constant.Constants;
import lk.grocery.platform.config.EntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lk.grocery.platform.util.constant.CommonReferenceCodes.PARTY_TYPE_VENDOR;
import static lk.grocery.platform.util.constant.CommonReferenceTypeCodes.*;
import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;

@Slf4j
@Service
public class PartyServiceImpl extends EntityValidator implements PartyService {

    private final CommonReferenceService commonReferenceService;
    private final PartyContactService partyContactService;
    private final UserService userService;

    private final PartyRepository partyRepository;
    private final BranchRepository branchRepository;
    private final NumberGeneratorRepository numberGeneratorRepository;

    public PartyServiceImpl(PartyRepository partyRepository,
                            CommonReferenceService commonReferenceService,
                            PartyContactService partyContactService,
                            UserService userService,
                            BranchRepository branchRepository,
                            NumberGeneratorRepository numberGeneratorRepository) {
        this.partyRepository = partyRepository;
        this.commonReferenceService = commonReferenceService;
        this.partyContactService = partyContactService;
        this.userService = userService;
        this.branchRepository = branchRepository;
        this.numberGeneratorRepository = numberGeneratorRepository;
    }

    @Transactional
    @Override
    public PartyDTO createParty(PartyDTO partyDTO) {

        validateEntity(partyDTO);

        partyDTO.setName(partyDTO.getFirstName() + " " + partyDTO.getLastName());

        TMsParty tMsParty = PartyMapper.INSTANCE.dtoToEntity(partyDTO);

        populateAndValidatePartyReferenceDetailsOnPersist(tMsParty, partyDTO);

        tMsParty.setPrtyStatus(STATUS_ACTIVE.getShortValue());
        tMsParty.setPrtyCode(createPartyCode(partyDTO.getType()));

        final TMsParty createdParty = persistEntity(tMsParty);

        if(partyDTO.getContactList() != null) {
            partyDTO.getContactList().forEach(partyContactDTO -> {
                commonReferenceService
                        .getByCmrfCodeAndCmrtCode(PARTY_CONTACT_TYPES.getValue(), partyContactDTO.getContactType());

                partyContactDTO.setPartyCode(createdParty.getPrtyCode());

                partyContactService.insertPartyContact(partyContactDTO, true);
            });
        }

        return PartyMapper.INSTANCE.entityToDTO(createdParty);
    }

    private String createPartyCode(String partyType) {

        String createdPartyCode = null;
        String refNumType = null;

        CommonReferenceCodes partyTypeEum = CommonReferenceCodes.findByString(partyType);

        try {
            switch (partyTypeEum) {
                case PARTY_TYPE_VENDOR: refNumType = "VN"; break;
                case PARTY_TYPE_CUSTOMER: refNumType = "CU"; break;
                default: throw new OperationException("Party type not found");
            }
            createdPartyCode = numberGeneratorRepository.generateNumber(refNumType, "Y", "#", "#", "#", "#");

        } catch (Exception e) {
            log.error("Error while creating a Party Code : " + e.getMessage());
            throw new OperationException("Error while creating a Party Code");
        }

        return createdPartyCode;
    }

    @Transactional
    @Override
    public PartyDTO getPartyByPartyCode(String partyCode) {

        final TMsParty tMsParty = validateByPartyCode(partyCode);

        PartyDTO partyDTO = PartyMapper.INSTANCE.entityToDTO(tMsParty);

        setReferenceData(tMsParty, partyDTO);

        final List<PartyContactDTO> contactDTOList = partyContactService.getContactsByPartyCode(partyDTO.getPartyCode(), true);
        partyDTO.setContactList(contactDTOList);

        return partyDTO;
    }

    @Transactional
    @Override
    public PartyDTO updateParty(String partyCode, PartyDTO partyDTO) {

        validateEntity(partyDTO);

        TMsParty tMsParty = validateByPartyCode(partyCode);

        populateAndValidatePartyReferenceDetailsOnPersist(tMsParty, partyDTO);

        partyDTO.setName(partyDTO.getFirstName() + " " + partyDTO.getLastName());
        tMsParty.setPrtyAddress1(partyDTO.getAddress1());
        tMsParty.setPrtyAddress2(partyDTO.getAddress2());
        tMsParty.setPrtyAddress3(partyDTO.getAddress3());
        tMsParty.setPrtyDob(partyDTO.getDob());
        tMsParty.setPrtyFirstName(partyDTO.getFirstName());
        tMsParty.setPrtyLastName(partyDTO.getLastName());
        tMsParty.setPrtyName(partyDTO.getName());
        tMsParty.setPrtyNic(partyDTO.getNic());
        tMsParty.setPrtyManagedBy(partyDTO.getManagedBy());
        tMsParty.setPrtyGender(partyDTO.getGender());

        tMsParty.setPrtyStatus(STATUS_ACTIVE.getShortValue());

        if(partyDTO.getType().equals(CommonReferenceCodes.PARTY_TYPE_CUSTOMER.getValue())) {
            tMsParty.setPrtyLongitude(partyDTO.getLongitude());
            tMsParty.setPrtyLatitude(partyDTO.getLatitude());
        }

        partyDTO.getContactList().forEach(partyContactDTO -> {
            partyContactDTO.setPartyCode(partyCode);

            if(partyContactDTO.getContactId() != null)
                partyContactService.updatePartyContactById(partyContactDTO);
            else
                partyContactService.insertPartyContact(partyContactDTO, true);
        });

        return PartyMapper.INSTANCE.entityToDTO(persistEntity(tMsParty));
    }

    private void populateAndValidatePartyReferenceDetailsOnPersist(TMsParty tMsParty, PartyDTO partyDTO) {

        commonReferenceService
                .getByCmrfCodeAndCmrtCode(PARTY_TYPES.getValue(), partyDTO.getType());

        /*tMsParty.setDepartment(null);
        if(!Strings.isNullOrEmpty(partyDTO.getDepartmentCode())) {
            final TMsDepartment tMsDepartment = departmentRepository
                    .findByDpmtCodeAndDpmtStatus(partyDTO.getDepartmentCode(), STATUS_ACTIVE.getShortValue());

            tMsParty.setDepartment(tMsDepartment);
        }*/

        /*tMsParty.setBranch(null);
        if(partyDTO.getBranchId() != null) {
            final TRfBranch tRfBranch = branchRepository
                    .findByBrnhIdAndBrnhStatus(partyDTO.getBranchId(), STATUS_ACTIVE.getShortValue());

            tMsParty.setBranch(tRfBranch);
        }*/

        commonReferenceService
                .getByCmrfCodeAndCmrtCode(GENDER_TYPES.getValue(), partyDTO.getGender());
    }

    @Transactional
    @Override
    public Boolean removeParty(String partyCode) {

        TMsParty tMsParty = validateByPartyCode(partyCode);

        partyContactService.removePartyContactByPartyCode(partyCode);
        userService.removeUserByPartyCode(partyCode);

        tMsParty.setPrtyStatus(Constants.STATUS_INACTIVE.getShortValue());
        persistEntity(tMsParty);

        return true;
    }

    @Override
    public PaginatedEntity partyPaginatedSearch(String name, String partyType, Integer page, Integer size) {

        PaginatedEntity paginatedPartyList = null;
        List<PartyDTO> customerList = null;

        validatePaginateIndexes(page, size);

        partyType = partyType.isEmpty() ? null : partyType;

        Page<TMsParty> tMsPartyPage = partyRepository
                .getActiveParties(name, STATUS_ACTIVE.getShortValue(), partyType,
                        PageRequest.of(page - 1, size));

        if (tMsPartyPage.getSize() == 0)
            return null;

        paginatedPartyList = new PaginatedEntity();
        customerList = new ArrayList<>();

        for (TMsParty tMsParty : tMsPartyPage) {

            PartyDTO partyDTO = PartyMapper.INSTANCE.entityToDTO(tMsParty);

            setReferenceData(tMsParty, partyDTO);

            customerList.add(partyDTO);
        }

        paginatedPartyList.setTotalNoOfPages(tMsPartyPage.getTotalPages());
        paginatedPartyList.setTotalNoOfRecords(tMsPartyPage.getTotalElements());
        paginatedPartyList.setEntities(customerList);

        return paginatedPartyList;
    }

    @Override
    public List<PartyDTO> getPartyListByType(String partyType) {

        final List<TMsParty> tMsPartyList = partyRepository
                .findAllByPrtyStatusAndPrtyType(STATUS_ACTIVE.getShortValue(), partyType);

        if(tMsPartyList.isEmpty())
            return Collections.emptyList();

        List<PartyDTO> partyDTOList = new ArrayList<>();

        tMsPartyList.forEach(tMsParty -> {

            PartyDTO partyDTO = new PartyDTO();

            partyDTO.setPartyCode(tMsParty.getPrtyCode());
            partyDTO.setName(tMsParty.getPrtyName());

            partyDTOList.add(partyDTO);
        });

        return partyDTOList;
    }

    private void setReferenceData(TMsParty tMsParty, PartyDTO partyDTO) {

        /*if(tMsParty.getDepartment() != null)
            partyDTO.setDepartmentName(tMsParty.getDepartment().getDpmtName());*/

        if(!Strings.isNullOrEmpty(partyDTO.getGender())) {
            final CommonReferenceDTO commonReferenceDTO = commonReferenceService
                    .getByCmrfCodeAndCmrtCode(GENDER_TYPES.getValue(), partyDTO.getGender());

            partyDTO.setGenderName(commonReferenceDTO.getDescription());
        }

        partyDTO.setContactList(partyContactService.getContactsByPartyCode(partyDTO.getPartyCode(), true));

        if(!Strings.isNullOrEmpty(tMsParty.getPrtyManagedBy()))
            partyDTO.setManagedByName(partyRepository
                    .findByPrtyCodeAndPrtyStatus(tMsParty.getPrtyManagedBy(), STATUS_ACTIVE.getShortValue()).getPrtyName());
    }

    TMsParty validateByPartyCode(String partyCode) {

        if (Strings.isNullOrEmpty(partyCode))
            throw new InvalidDataException("Party Code is required");

        final TMsParty tMsParty = partyRepository.findByPrtyCodeAndPrtyStatus(partyCode, STATUS_ACTIVE.getShortValue());

        if(tMsParty == null)
            throw new DataNotFoundException("Party not found for the Code : " + partyCode);

        return tMsParty;
    }

    private TMsParty persistEntity(TMsParty tMsParty) {
        try {
            validateEntity(tMsParty);
            return partyRepository.save(tMsParty);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
    }
}
