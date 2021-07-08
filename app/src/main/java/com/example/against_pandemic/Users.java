package com.example.against_pandemic;

public class Users {
    public static String nid;
    public static String financial_condition;
    public static String coronaResult;

    public static String getCoronaResult() {
        return coronaResult;
    }

    public static void setCoronaResult(String coronaResult) {
        Users.coronaResult = coronaResult;
    }

    public static String getNid() {
        return nid;
    }

    public static void setNid(String nid) {
        Users.nid = nid;
    }

    public static String getFinancial_condition() {
        return financial_condition;
    }

    public static void setFinancial_condition(String financial_condition) {
        Users.financial_condition = financial_condition;
    }
}
