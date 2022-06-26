package lk.grocery.platform.util.constant.status;

public enum OrderStatus {

    CREATED,
    PLACED,
    CONFIRMED,
    PREPARED,
    DELIVERED;

    private short shortValue;
    private String stringValue;

    OrderStatus(){}

    OrderStatus(short shortValue) {
        this.shortValue = shortValue;
    }

    OrderStatus(String stringValue) { this.stringValue = stringValue; }

    public short getShortValue() {
        return shortValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static OrderStatus getNameByCode(short status) {

        for(OrderStatus inquiryStatus : values()) {
            if(status == inquiryStatus.getShortValue())
                return inquiryStatus;
        }

        return null;
    }
}
