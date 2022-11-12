package com.company;

import java.text.Collator;
import java.util.Locale;
import java.util.Random;

public enum Nationality {
    Polish(new Locale("pl")),
    Ukrainian(new Locale("ua")),
    Belarussian(new Locale("by")),
    Slovak(new Locale("sk")),
    Lithuanian(new Locale("lt")),
    Latvian(new Locale("lv")),
    British(new Locale("en_GB")),
    Indian(new Locale("en_IN")),
    Chinese(new Locale("cn")),
    Vietnamese(new Locale("vn"));

    public static Nationality random (){
        Nationality[] values = values();
        int index = new Random().nextInt(values().length);
        return values[index];
    }

    private Locale locale;
    private Collator collator;

    private Nationality(Locale locale){
        this.locale= locale;
        collator = Collator.getInstance(locale);
    }

    public Locale getLocale(){
        return locale;
    }

    public Collator getCollator(){
        return collator;
    }
}
