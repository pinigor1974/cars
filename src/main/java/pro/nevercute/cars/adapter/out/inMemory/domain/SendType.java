package pro.nevercute.cars.adapter.out.inMemory.domain;

public enum SendType {
    HTTP(1),
    ERROR(3),
    SMS(2); // ToDo Всегда uppercase
    private final Integer priority;

    SendType(Integer priority) {

        this.priority = priority;
    }
    public Integer getValue() {
        return this.priority;
    }
}
