package com.example.administrator.courcemanager.utils;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取XML中角色信息
 */
public class RoleData {
    public static List<String> getRoleInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        String role = null;
        List<String> roles = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_DOCUMENT:
                    roles = new ArrayList<String>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("role".equals(parser.getName())) {
                        role = parser.getAttributeValue(0);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("role".equals(parser.getName())) {
                        roles.add(role);
                        role = null;
                    }
                    break;
            }
            type=parser.next();
        }
        return roles;
    }
}
