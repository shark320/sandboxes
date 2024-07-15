package com.vpavlov;

import me.shib.java.lib.diction.DictionService;
import me.shib.java.lib.diction.DictionWord;

public class Main {
    public static void main(String[] args) {
        DictionService dictionService = new DictionService();
        DictionWord word = dictionService.getDictionWord("versions");
        if (word == null){
            System.out.println("fff");
            return;
        }
        System.out.println(word.getWord());
    }
}