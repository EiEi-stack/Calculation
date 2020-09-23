import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculation {
    private static String formula;
    private static String operatorArrRaw[];
    private static String operandArrRaw[];

    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Formula:");
            formula = scanner.nextLine().trim();
            int rec = calc(formula);
            System.out.println("Result is:" + rec);
            scanner.close();
        } catch (InputMismatchException e) {
            System.out.println(e);

        }catch (NumberFormatException e){
            System.out.println(e);
        }
    }

    public static int calc(String sFormula) {
//		BigDecimal result = new BigDecimal(0);
        int result = 0;
        // 実装start
        int aggregate = 0;
        operatorArrRaw = sFormula.split("[0-9]+", -1);
        operandArrRaw = sFormula.split("[()+\\-*/]", -1);

        if (!operandArrRaw[0].isEmpty()) {
            aggregate = Integer.parseInt(operandArrRaw[0]);
        }
        for (int i = 0; i < operatorArrRaw.length; i++) {

            switch (operatorArrRaw[i]) {
                case "+":
                    aggregate += Integer.parseInt(operandArrRaw[i]);
                    break;
                case "-":
                    aggregate -= Integer.parseInt(operandArrRaw[i]);
                    break;
                case "*":
                    aggregate *= Integer.parseInt(operandArrRaw[i]);
                    break;
                case "/":
                    aggregate /= Integer.parseInt(operandArrRaw[i]);
                    break;
                default:
                    break;
            }

        }

        // 実装end
        return aggregate;
    }

}
