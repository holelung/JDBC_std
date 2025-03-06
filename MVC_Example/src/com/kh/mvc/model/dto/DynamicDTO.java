package com.kh.mvc.model.dto;

import java.util.HashMap;
import java.util.Map;

public class DynamicDTO {
	private Map<String, Object> data = new HashMap<String, Object>();

    public Object get(String key) {
        return data.get(key);
    }
    
    public void set(String key, Object value) {
        data.put(key, value);
    }
    
    public Map<String, Object> getData() {
        return data;
    }

}
