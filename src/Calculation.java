import java.util.*;

public class Calculation {
    private static String formula;

    public static void main(String args[]) {
        try {
            //[+-]?([0-9]*[.])?[0-9]+])
            Scanner scanner = new Scanner(System.in);
            System.out.print("INPUT:");
            formula = scanner.nextLine();
            scanner.close();
        } catch (InputMismatchException e) {
            System.out.println(e);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }


        String[] rawInputArray = formula.split("", -1);
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
        String inputString = String.join("", inputArray);
        //((12.9-80)*70ab/4)

        Calculation cal = new Calculation();
        double result = cal.calc(inputString);
        System.out.println(result);
    }

    private double calc(String sFormula) {
//        BigDecimal result = new BigDecimal(0);
        // 実装start[^([-+/*]\d+(\.\d+)?)*]
        // 実装end
        int index;
        if (sFormula.charAt(0) == '-' || sFormula.charAt(0) == '+') {
            sFormula = '0' + sFormula;
        }
        if ((index = find(sFormula, '+')) >= 0) {
            return (calc(sFormula.substring(0, index) + calc(sFormula.substring(index + 1, sFormula.length()))));
        } else if ((index = find(sFormula, '-')) >= 0) {
            return (calc(sFormula.substring(0, index)) - calc(sFormula.substring(index + 1, sFormula.length())));
        } else if ((index = find(sFormula, '*')) >= 0) {
            return (calc(sFormula.substring(0, index)) * calc(sFormula.substring(index + 1, sFormula.length())));
        } else if ((index = find(sFormula, '/')) >= 0) {
            return (calc(sFormula.substring(0, index)) / calc(sFormula.substring(index + 1, sFormula.length())));
        }
        if (sFormula.charAt(0) == '(') {
            if (sFormula.charAt(sFormula.length() - 1) == ')') {
                return (calc(sFormula.substring(1, sFormula.length() - 1)));
            } else {

            }
        }
        return Double.parseDouble(sFormula);
    }

    private int find(String inpuString, char ch) {
        int count = 0;
        for (int i = inpuString.length() - 1; i >= 0; i--) {
            if (inpuString.charAt(i) == '(') {
                count++;
            }
            if (inpuString.charAt(i) == ')') {
                count--;
            }
            if (inpuString.charAt(i) == ch && count == 0) {
                return i;
            }
        }
        return -1;
    }


}