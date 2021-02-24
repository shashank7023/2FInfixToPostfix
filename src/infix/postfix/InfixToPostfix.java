package infix.postfix;

import java.util.Stack;

public class InfixToPostfix {
    public String infixToPostfix(String infixString){
        String postfix = "";
        //step1
        Stack<Character> stack=new Stack<>();
        stack.push('(');
        infixString=infixString.concat(""+')');

        //Step2
        for (int i = 0; i < infixString.length(); i++) {
            char character =infixString.charAt(i);
            //Step3
            if(Character.isAlphabetic(character)){
                postfix=postfix.concat(""+ character);
            }
            //Step4
            else if(character=='('){
                 stack.push(character);

            }
            //Step5
            else if(isOperator(character)){
                if(stack.peek()!='('){
                    while (!stack.empty()){
                        if(precedence(stack.peek())>=precedence(character)){
                            postfix=postfix.concat(""+stack.pop());
                        }else {
                            stack.push(character);
                            break;

                        }
                    }
                }
                else {
                    stack.push(character);

                }
            }
            //Step6
            else if(character==')'){
                while(!stack.empty()){
                    if(stack.peek()!='('){
                        postfix=postfix.concat(""+stack.pop());

                    }
                    else {
                        stack.pop();
                        break;
                    }
                }
            }
        }
        return postfix;
    }
    private boolean isOperator(char character){
        boolean response = false;
        switch (character){
            case '^':
            case '/':
            case '*':
            case '+':
            case '-':
                response = true;
        }

        return response;
    }
    private int precedence(char operator){
        int response = 0;
        switch (operator) {
            case '^':
                response = 3;
                break;
            case '/':
            case '*':
                response = 2;
                break;
            case '+':
            case '-':
                response = 1;
        }

        return response;
    }
}
