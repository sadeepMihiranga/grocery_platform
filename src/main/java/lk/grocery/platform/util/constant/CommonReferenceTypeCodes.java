package lk.grocery.platform.util.constant;

public enum CommonReferenceTypeCodes {

    PARTY_TYPES("PTYPE"),
    PARTY_CONTACT_TYPES("CONTC"),
    GENDER_TYPES("GENDR"),
    MEASUREMENT_TYPES("UOFMS"),
    ITEM_TYPES("ITMTP"),
    PAYMENT_TYPES("PAYTP"),
    ITEM_CATEGORY_TYPE("ITCTP");

    private String value;
    private short shortValue;
    private int intValue;

    CommonReferenceTypeCodes(String value) {
        this.value = value;
    }

    CommonReferenceTypeCodes(short shortValue) {
        this.shortValue = shortValue;
    }

    CommonReferenceTypeCodes(int intValue) {
        this.intValue = intValue;
    }

    public String getValue() {
        return value;
    }

    public short getShortValue() {
        return shortValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
