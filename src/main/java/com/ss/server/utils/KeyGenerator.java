package com.ss.server.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/3
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class KeyGenerator {

    /**
     * Make string.
     *
     * @param length the length
     * @return the string
     */
    public static String make(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
