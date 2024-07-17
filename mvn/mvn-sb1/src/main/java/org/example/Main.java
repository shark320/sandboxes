package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Test{
        private int i = 0;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        Object message = '-';
        System.out.println(message.toString());
    }
}