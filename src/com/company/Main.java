package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(getResult("345+6*2/2/1.3"));
    }

    public static double getResult(String str) {
        char[] chars = str.toCharArray();
        double result = 0;
        String oper1 = null, oper2 = null;
        Character actionChar = null;

        for (int i = 0; i < chars.length; i++) {
            if(!Character.isDigit(chars[i]) && chars[i] != '.') {
                actionChar = chars[i];
            }
            else
            {
                if(actionChar == null) {
                    oper1 = oper1 == null ? Character.toString(chars[i]) : oper1 + chars[i];
                }
                else {
                    if(Character.isDigit(chars[i]) || chars[i] == '.') {
                        oper2 = oper2 == null ? Character.toString(chars[i]) : oper2 + chars[i];
                        if(i + 1 < chars.length) {
                            if ((!Character.isDigit(chars[i + 1]) && chars[i + 1] != '.')
                                    || i + 1 == chars.length) {
                                oper1 = Double.toString(calc(
                                        Double.parseDouble(oper1),
                                        Double.parseDouble(oper2),
                                        actionChar));
                                oper2 = null;
                            }
                        }
                        else {
                            result = calc(
                                        Double.parseDouble(oper1),
                                        Double.parseDouble(oper2),
                                        actionChar);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static double calc(double opr1, double opr2, Character action) {
        switch (action) {
            case '+':
                return opr1 + opr2;
            case '-':
                return opr1 - opr2;
            case '*':
                return opr1 * opr2;
            case '/':
                return opr1 / opr2;
            default:
                return 0;
        }
    }
}
