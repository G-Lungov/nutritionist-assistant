package com.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlService {

    private final XmlMapper xmlMapper;

    public XmlService() {
        this.xmlMapper = new XmlMapper();
    }

    /**
     * Read XML file to convert in-tags to Map<K,V>
     *
     * @param xml path
     * @return map with fomated keys to substitution
     * @throws exception in case file not found
     */

    public Map<String, String> readXmlData(String xmlPath) throws Exception {
        File file = new File(xmlPath);

        if (!file.exists()) {
            throw new FileNotFoundException("The specified XML could not be found in" + xmlPath);
        }

        // Read raw XMl and converts to generic Java map
        Map<String, String> rawData = xmlMapper.readValue(
            file,
            new TypeReference<Map<String, String>>() {}
        );

        // Formats keys adding Word tag pattern
        Map<String, String> formatedData = new HashMap<>();
        for(Map.Entry<String, String> valueEntry : rawData.entrySet()) {
            String tagKey = "${" + valueEntry.getKey() + "}";
            formatedData.put(tagKey, valueEntry.getValue());
        }

        return formatedData;
    }
}
