package com.ll.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by liaoli
 * date: 2020/4/2
 * time: 17:37
 *
 * @author: liaoli
 */
public class StringUtils {

    public static String join(Collection c,String s){
        StringBuffer sb = new StringBuffer();

        for(Iterator iterator = c.iterator(); iterator.hasNext(); sb.append((String)iterator.next())) {
            if (sb.length() != 0) {
                sb.append(s);
            }
        }

        return sb.toString();
    }
}
