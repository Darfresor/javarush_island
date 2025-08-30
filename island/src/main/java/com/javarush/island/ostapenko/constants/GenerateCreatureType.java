package com.javarush.island.ostapenko.constants;

import com.javarush.island.ostapenko.core.exception.ApplicationException;

public enum GenerateCreatureType {
    FOR_EXAMPLE("Пример для просмотра в консоли"),
    DEFAULT("Стандартная генерация");

    private String generateTypeName;

    GenerateCreatureType(String generateTypeName) {
        this.generateTypeName = generateTypeName;
    }

    public String getGenerateTypeName() {
        return generateTypeName;
    }

    public static GenerateCreatureType getTypeByName(String typeName){
        for(GenerateCreatureType type :values()){
            if(type.generateTypeName.equals(typeName)){
                return type;
            }
        }
        throw new ApplicationException("Unknown generate type"+ typeName);
    }
}
