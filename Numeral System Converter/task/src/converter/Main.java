package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            if (!scanner.hasNextInt()) {
                throw new Exception("Error: Unknown base radix!");
            }
            int baseRadix = scanner.nextInt();

            String[] number = scanner.next().split("\\.");
            for (String part : number) {
                for (int i = 0; i < part.length(); i++) {
                    if (baseRadix != 1 && Character.getNumericValue(part.charAt(i)) >= baseRadix) {
                        throw new Exception("Error: source number out of range in source radix!");
                    }
                }
            }

            if (!scanner.hasNextInt()) {
                throw new Exception("Error: Unknown result radix!");
            }
            int resultRadix = scanner.nextInt();

            if (baseRadix < 1 || baseRadix > 36 || resultRadix < 1 || resultRadix > 36) {
                throw new Exception("Error: Radix should be in range [1:36]!");
            }

            StringBuilder stringBuilder = new StringBuilder();
            String integerPart = number[0];
            int integerPartTemp = baseRadix == 1 ? integerPart.length() : Integer.parseInt(integerPart, baseRadix);

            if (resultRadix == 1) {
                stringBuilder.append("1".repeat(integerPartTemp));
            } else {
                stringBuilder.append(Long.toString(integerPartTemp, resultRadix));
            }

            if (number.length == 2) {
                stringBuilder.append(".");
                String fractionalPart = number[1];
                double fractionalPartTemp = 0.0;

                for (int i = 0; i < fractionalPart.length(); i++) {
                    String f = fractionalPart.substring(i, i + 1);
                    fractionalPartTemp += Integer.parseInt(f, baseRadix) / Math.pow(baseRadix, i + 1);
                }
                for (int i = 0; i < 5; i++) {
                    stringBuilder.append(Integer.toString((int) (fractionalPartTemp * resultRadix), resultRadix));
                    fractionalPartTemp = (fractionalPartTemp * resultRadix) % 1;
                }
            }
            System.out.println(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}