package com.intern.practice22.app;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.IOException;
import java.util.Map;

public class ApplicationJsonSerializer extends JsonSerializer<Application> {
    @Override
    public void serialize(Application application, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ToXmlGenerator xmlGen = (ToXmlGenerator) jsonGenerator;
        xmlGen.writeStartObject();
        for (Map.Entry<String, Integer> entry : application.getEntry().entrySet()) {
            xmlGen.writeObjectFieldStart("fine");
            writeAttributes(xmlGen, entry.getKey());
            xmlGen.writeRaw(entry.getValue().toString());
            xmlGen.writeEndObject();
        }
        xmlGen.writeEndObject();
    }

    private void writeAttributes(ToXmlGenerator gen, String key) throws IOException {
        gen.setNextIsAttribute(true);
        gen.writeFieldName("type");
        gen.writeString(key);
        gen.setNextIsAttribute(false);
    }
}
