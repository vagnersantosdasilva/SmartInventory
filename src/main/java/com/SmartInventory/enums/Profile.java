package com.SmartInventory.enums;

public enum Profile {
    ADMIN (1,"ROLE_ADMIN"),
    USER (2,"ROLE_USER"),
    MACHINE(3,"ROLE_MACHINE");

    private int code;
    private String description;

    private Profile(int code,String description){
        this.code = code;
        this.description=description;
    }

    public int getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }

    public static Profile toEnum(Integer code){
        if (code==null) return null;
        for (Profile x : Profile.values()){
            if (code.equals(x.getCode())) return  x;
        }
        throw new IllegalArgumentException("ID inválido :"+code);
    }
}
