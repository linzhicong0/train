package org.jack.train.member.enums;

public enum PassengerType {
    ADULT("1", "成人"),
    CHILD("2", "儿童"),
    STUDENT("3", "学生"),


    private String code;
    private String description;

    PassengerType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
