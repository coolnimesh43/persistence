package com.coolnimesh43.persistence.enums;

public enum ProjectPriority {

    LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);

    private final Integer priority;

    private ProjectPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

}
