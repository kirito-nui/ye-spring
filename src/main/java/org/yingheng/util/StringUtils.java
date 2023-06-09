package org.yingheng.util;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 20:55
 */
public class StringUtils {

    public static String replace(String inString, String oldPattern, String newPattern) {
//        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
//            return inString;
//        }
        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            // no occurrence -> can return input as-is
            return inString;
        }

        int capacity = inString.length();
        if (newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }
        StringBuilder sb = new StringBuilder(capacity);

        int pos = 0;  // our position in the old string
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString, pos, index);
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }

        // append any characters to the right of a match
        sb.append(inString, pos, inString.length());
        return sb.toString();
    }
}
