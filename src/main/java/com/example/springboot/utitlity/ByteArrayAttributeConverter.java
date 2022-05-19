package com.example.springboot.utitlity;

import com.yubico.webauthn.data.ByteArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ByteArrayAttributeConverter implements AttributeConverter<ByteArray, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(ByteArray attribute) {
        return attribute.getBytes();
    }

    @Override
    public ByteArray convertToEntityAttribute(byte[] dbData) {
        return new ByteArray(dbData);
    }

}
