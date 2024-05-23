package com.urize.webapp.util;

import com.urize.webapp.model.Organization;

public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }
}
