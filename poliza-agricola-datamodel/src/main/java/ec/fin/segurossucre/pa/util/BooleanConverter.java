package ec.fin.segurossucre.pa.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String>{
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            return "1";
        } else {
            return "0";
        }
    }
    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "1".equals(value);
    }
}