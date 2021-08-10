package com.example.help_me_out;

import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {


        public static String Calculate (String s) {
            try {

                ArrayList<String> exp = infixToPostfix(toArray(s));
                for (String t : exp) {
                    System.out.print(t);
                }
                Stack<Double> num = new Stack<Double>();
                int i = 0;
                int size = exp.size() - 1;

                for (int x = 0; x < exp.size(); x++) {
                    String ch = exp.get(x);

                    try {

                        num.push(Double.parseDouble(ch));
                    } catch (Exception e) {
                        try {
                            double a = num.pop();
                            double b = num.pop();
                            if (ch.equals("+")) {
                                exp.set(x, (Double.toString(a + b)));
                                if (x != size) {
                                    x--;
                                }

                                continue;
                            } else if (ch.equals("-")) {
                                exp.set(x, (Double.toString(b - a)));
                                if (x != size) {
                                    x--;
                                }

                                continue;
                            } else if (ch.equals("*")) {
                                exp.set(x, (Double.toString(a * b)));
                                if (x != size) {
                                    x--;
                                }
                                continue;
                            } else if (ch.equals("/")) {
                                exp.set(x, (Double.toString(b / a)));
                                if (x != size) {
                                    x--;
                                }
                                continue;
                            } else if (ch.equals("^")) {
                                exp.set(x, (Double.toString(Math.pow(b, a))));
                                if (x != size) {
                                    x--;
                                }
                                continue;
                            }
                        } catch (Exception e2) {
                            return "Invalid Expression";
                        }
                    }
                }
                return exp.get(exp.size() - 1);

            } catch (Exception e3) {
                return "Invalid";
            }
        }
        public static int Prec (String ch)
        {
            if (ch.equals("+") || ch.equals("-")) {
                return 1;
            } else if (ch.equals("*") || ch.equals("/")) {
                return 2;
            } else if (ch.equals("^")) {
                return 3;
            } else {
                return 0;
            }
        }

        public static ArrayList<String> infixToPostfix (ArrayList < String > exp)
        {
            // initializing empty String for result
            ArrayList<String> postexp = new ArrayList<String>();

            // initializing empty stack
            Stack<String> stack = new Stack<>();

            for (int i = 0; i < exp.size()-1; i++) {
                String c = exp.get(i);

                // If the scanned character is an
                // operand, add it to output.
                if (Character.isLetterOrDigit(c.charAt(0)))
                    postexp.add(c);

                    // If the scanned character is an '(',
                    // push it to the stack.
                else if (c.equals("("))
                    stack.push(c);

                    // If the scanned character is an ')',
                    // pop and output from the stack
                    // until an '(' is encountered.
                else if (c.equals(")")) {
                    while (!stack.isEmpty() && !(stack.peek().equals("("))) {
                        postexp.add(stack.pop());
                    }

                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else // an operator is encountered
                {
                    while (!stack.isEmpty() && Prec(c)
                            <= Prec(stack.peek())) {

                        postexp.add(stack.pop());
                    }
                    stack.push(c);
                }

            }

            // pop all the operators from the stack
            while (!stack.isEmpty()) {
                if (stack.peek().equals("(")) {
                    ArrayList<String> inv = new ArrayList<String>();
                    inv.add("Invalid");
                    return inv;
                }
                postexp.add(stack.pop());

            }
            return postexp;
        }
    public static Stack<String> Topre(ArrayList<String> exp)
    {
        Stack<String> op=new Stack<String>();
        for(int x=0;x<exp.size();x++)
        {
            String ch=exp.get(x);
            if(ch.equals("+")||ch.equals("-")||ch.equals("*")||ch.equals("/")||ch.equals("^"))
            {
                String o1=op.pop();
                String o2=op.pop();
                String temp=ch+o2+o1;
                op.push(temp);
            }
            else
            {
                op.push(ch);
            }
        }
        return op;
    }
        public static ArrayList<String> toArray (String exp)
        {
            ArrayList<String> str = new ArrayList<String>();
            String num = "";
            for (int x = 0; x < exp.length(); x++) {

                char ch = exp.charAt(x);
                if (Character.isDigit(ch) || ch == '.') {
                    num = num + ch;
                } else {
                    if (num != "") {
                        str.add(num);
                    }
                    str.add(String.valueOf(ch));
                    num = "";
                }
            }
            str.add(num);
            return str;


        }
    }
