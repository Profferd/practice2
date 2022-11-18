package com.intern.practice22.app;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Collections;
import java.util.Map;

@JacksonXmlRootElement(localName = "type")
@JsonSerialize(using = ApplicationJsonSerializer.class)
public
class Application {

    private Map<String, Integer> entry;

    public Map<String, Integer> getEntry() {
        return Collections.unmodifiableMap(entry);
    }

    public void setEntry(Map<String, Integer> entry) {
        this.entry = entry;
    }
}