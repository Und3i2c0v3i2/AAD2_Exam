package com.example.aad2.utils;

import com.example.aad2.model.entity.Phone;

import java.util.List;

public class StringBuilderUtil {

    private StringBuilderUtil() {}

    public static StringBuilder displayPhones(List<Phone> phoneList) {

        StringBuilder builder = new StringBuilder();

        for (Phone p : phoneList) {

            switch (p.getType()) {
                case Phone.Type.MOBILE:
                    builder.append(p.getType())
                            .append(": +381")
                            .append(p.getNumber())
                            .append("\n");
                    break;
                case Phone.Type.HOME:
                    builder.append(p.getType())
                            .append(": +381")
                            .append(p.getNumber())
                            .append("\n");
                    break;
                case Phone.Type.WORK:
                    builder.append(p.getType())
                            .append(": +381")
                            .append(p.getNumber())
                            .append("\n");
                    break;
            }
        }

        return builder;
    }
}
