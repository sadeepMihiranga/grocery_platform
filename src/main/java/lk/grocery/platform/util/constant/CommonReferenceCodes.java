package lk.grocery.platform.util.constant;

public enum CommonReferenceCodes {

    /** party types */
    PARTY_TYPE_CUSTOMER("CUSTM"),
    PARTY_TYPE_EMPLOYEE("EMPLY"),
    PARTY_TYPE_VENDOR("VENDR"),

    /** party contact types */
    PARTY_CONTACT_MOBILE("CNMBL"),
    PARTY_CONTACT_EMAIL("CNEML"),

    PAYMENT_CASH("PTCASH"),
    PAYMENT_BANK_DEPOSIT("PTBDEP"),
    PAYMENT_ONLINE_TRANSFER("PTONLN");

    private String value;
    private short shortValue;
    private int intValue;

    CommonReferenceCodes(String value) {
        this.value = value;
    }

    CommonReferenceCodes(short shortValue) {
        this.shortValue = shortValue;
    }

    CommonReferenceCodes(int intValue) {
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

    public static CommonReferenceCodes findByString(String codeStringValue){
        for(CommonReferenceCodes codes : values()){
            if( codes.getValue().equals(codeStringValue)){
                return codes;
            }
        }
        return null;
    }
}
