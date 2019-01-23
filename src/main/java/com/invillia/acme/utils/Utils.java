package com.invillia.acme.utils;

import org.apache.commons.lang3.StringUtils;

public class Utils {

    public String sanitizeStr(String str) {
        String retrieve = "";
        if (StringUtils.isNotBlank(str)) {
            retrieve = str.replaceAll("[/]", "");
            retrieve = retrieve.replaceAll("[-]", "");
            retrieve = retrieve.replaceAll("[.]", "");
        }
        return retrieve;
    }
}
