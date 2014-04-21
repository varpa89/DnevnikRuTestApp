package com.varpa89.dnevnik.util;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: varpa89
 * Date: 21.04.14
 * Time: 17:40
 */
@Service
public class SecurityBeanImpl implements SecurityBean {
    public static final org.apache.commons.logging.Log log =
            LogFactory.getLog(SecurityBeanImpl.class);

    public String getStringMD5fromString(String value) {
        StringBuffer MD5 = new StringBuffer();
        try {
            MessageDigest algorithm = null;
            try {
                algorithm = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                log.error("getStringMD5fromString" + e);
            }
            byte digest[] = algorithm.digest(value.getBytes());
            String str_unit;
            for (int i = 0; i < digest.length; i++) {
                str_unit = Integer.toHexString(0xFF & digest[i]);
                if (str_unit.length() <= 1) str_unit = "0" + str_unit;
                MD5.append(str_unit);
            }
        } catch (Exception e) {
            log.error("getStringMD5fromString", e);
        }
        return MD5.toString();
    }
}
