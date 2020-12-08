package org.abliger.lang.counst;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import lombok.Data;
import sun.util.resources.cldr.rw.CurrencyNames_rw;

import java.util.HashMap;
import java.util.Map;

/**
 * @author abliger
 * @date 2020/12/4
 * @description
 */

public class AttributeMapping {
    public  static Map<String,String> map= new HashMap<>();
    static {
        map.put("java.lang.String","varchar");
        map.put("java.lang.Integer","bigint");
    }
}
