package ar.elea.apx.backend.entity.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;

/**
 * @author Guillermo Nasi
 */
public class LowerCaseConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return StringUtils.lowerCase(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return StringUtils.lowerCase(dbData);
    }
}
