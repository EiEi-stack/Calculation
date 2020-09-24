import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculation {
    private static BigDecimal output = new BigDecimal(0);

    public static void main(String args[]) {
        String userInput = null;
        output.setScale(2, RoundingMode.HALF_UP);
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("INPUT:");
            userInput = scanner.nextLine();
            scanner.close();
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        String[] rawInputArray = userInput.split("", -1);
        List<String> inputArray = new ArrayList<>();
        String temp = "";

        for (String value : rawInputArray) {
            if (value.matches("^[0-9]+|[.]|[0-9]+")) {
                temp += value;
                continue;
            }
            if (value.matches("[()+\\-*/]")) {
                inputArray.add(temp);
                inputArray.add(value);
                temp = "";
                continue;
            }
        }
        inputArray.add(temp);
        inputArray.removeAll(Arrays.asList("", null));
        String inputFormulas = String.join("", inputArray);

        Calculation cal = new Calculation();
        cal.findParentheses(inputFormulas);
        System.out.println("OUTPUT:" + output);
    }

    public BigDecimal calc(String sFormula) throws RuntimeException {
        BigDecimal result = new BigDecimal(0);
        result.setScale(2, RoundingMode.HALF_UP);
        int index = 0;

        if (sFormula.contains("+")) {
            index = sFormula.indexOf("+");
            return result = calc(sFormula.substring(0, index)).add(calc(sFormula.substring(index + 1, sFormula.length())));
        }
        if (sFormula.contains("-")) {
            index = sFormula.indexOf("-");
            return result = calc(sFormula.substring(0, index)).subtract(calc(sFormula.substring(index + 1, sFormula.length())));
        }
        if (sFormula.contains("*")) {
            index = sFormula.indexOf("*");
            return result = calc(sFormula.substring(0, index)).multiply(calc(sFormula.substring(index + 1, sFormula.length())));
        }
        if (sFormula.contains("/")) {
            index = sFormula.indexOf("/");
            return result = calc(sFormula.substring(0, index)).divide(calc(sFormula.substring(index + 1, sFormula.length())));
        }

        return new BigDecimal(sFormula).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    private String findParentheses(String inputFormula) {
        int startIndex = 0;
        int endIndex = 0;
        Boolean hasNoParentheses = false;
        for (int i = 0; i < inputFormula.length(); i++) {
            if (inputFormula.charAt(i) == '(') {
                startIndex = i + 1;
            }
            if (inputFormula.charAt(i) == ')') {
                endIndex = i;
                break;
            }
        }
        try {
            String subExpression = inputFormula.substring(startIndex, endIndex);
            if (!subExpression.equals("")) {
                BigDecimal subValue = calc(subExpression);
                inputFormula = inputFormula.replace(inputFormula.substring(startIndex - 1, endIndex + 1), subValue.toString());
            } else {
                output = calc(inputFormula);
            }
            if (startIndex == 0 && endIndex == 0) {
                hasNoParentheses = true;
            }
            if (hasNoParentheses != true) {
                findParentheses(inputFormula);
            }
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        return inputFormula;
    }
}