package lk.grocery.platform.util.constant.status;

public enum OrderUrgencyLevel {

    HIGH((short) 1),
    MEDIUM((short) 2),
    LOW((short) 3);

    private short shortValue;

    OrderUrgencyLevel(short shortValue) {
        this.shortValue = shortValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public static OrderUrgencyLevel getNameByNo(short status) {
        for(OrderUrgencyLevel urgencyLevel : values()) {
            if(status == urgencyLevel.getShortValue())
                return urgencyLevel;
        }
        return null;
    }
}
