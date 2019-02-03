package com.marchesani.clair.freezer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

/**
 * Enumerates various types of food that can be in the freezer
 */
public enum FoodType {

    MEAT,
    FISH,
    SPICES,
    VEGETABLES;

    /**
     * Enables Restlet to serialise the enum values to {@link String}
     */
    public static class Serialiser extends JsonSerializer<FoodType> {

        @Override
        public void serialize(FoodType foodType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {
            jsonGenerator.writeString(foodType.name());
        }
    }

    /**
     * Enables Restlet to deserialise the enum values from {@link String}
     */
    public static class Deserialiser extends JsonDeserializer<FoodType> {

        @Override
        public FoodType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return FoodType.valueOf(jsonParser.getValueAsString());
        }
    }
}
