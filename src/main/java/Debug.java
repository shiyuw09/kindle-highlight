/**
 * @(#)Debug.java, Apr 08, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangsy
 */
public class Debug {
    public static void main(String[] args) throws ParseException {
        //        February 10, 2020 11:11:17 PM
        String pattern = "EE, MMMM dd, yyyy hh:mm:ss a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("Monday, February 10, 2020 11:11:17 PM");
        System.out.println(date);
    }
}