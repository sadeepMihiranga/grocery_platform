package lk.grocery.platform.config;

import lk.grocery.platform.exception.DataNotFoundException;
import lk.grocery.platform.exception.InvalidDataException;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.util.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class EntityValidator {

    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @Autowired
    private HttpServletRequest request;

    protected void validateEntity(Object dto) {
        Set<ConstraintViolation<Object>> violations = localValidatorFactoryBean.validate(dto);
        if (violations.stream().findFirst().isPresent()) {
            throw new DataNotFoundException(violations.stream().findFirst().get().getMessage());
        }
    }

    protected final List<Long> captureBranchIds() {

        final Object attribute = request.getAttribute(Constants.REQUEST_BRANCHES.getValue());

        if(attribute == null)
            throw new NoRequiredInfoException("Branch Id is missing");

        String branchesString = attribute.toString();

        if(Strings.isNullOrEmpty(branchesString))
            throw new NoRequiredInfoException("Branch Id is missing");

        String[] branchesArray = branchesString
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\s", "")
                .split(",");

        List<Long> branches = new ArrayList<>();

        for (int i = 0; i < branchesArray.length; i++) {
            branches.add(Long.valueOf(branchesArray[i]));
        }

        log.info("Request came from branch {} ", branchesArray[0]);

        return branches;
    }

    protected final void validatePaginateIndexes(Integer page, Integer size) {

        if (page < 1)
            throw new InvalidDataException("Page should be a value greater than 0");

        if (size < 1)
            throw new InvalidDataException("Limit should be a value greater than 0");
    }

    protected final boolean patternMatches(String stringToMatch, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(stringToMatch)
                .matches();
    }

    protected final void validateLocation(BigDecimal longitude, BigDecimal latitude) {

        if(longitude.longValue() < -180 || longitude.longValue() > 180) {
            throw new InvalidDataException("Invalid longitude");
        }

        if(latitude.longValue() < -90 || latitude.longValue() > 90) {
            throw new InvalidDataException("Invalid latitude");
        }
    }
}
