package com.example.help_me_out;

public class DataModule {
    private String expression;
    private String Result;
    private int id;

    public DataModule(String expression, String result, int id) {
        this.expression = expression;
        Result = result;
        this.id = id;
    }

    public DataModule() {
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
