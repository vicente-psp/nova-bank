package br.com.desafionttdata.utils;

import java.util.Scanner;

public class UtilsFormat {
    public static int intValue(Scanner sc) {
        do {
            try {
                String menuStr = sc.next();
                return new Integer(menuStr);
            } catch (Exception e) {
                System.out.println("== VALOR INVÁLIDO ==");
            }
        } while (true);
    }

    public static String valorFormatado(long valor) {
        if (valor > 0) {
            Double reais = valor / 100.0;
            String list[] = reais.toString().split("[\\.]");
            String centavos = list[1].length() == 1 ? list[1] + "0" : list[1];
            formatarMilhar(list[0]);
            return "R$ " + formatarMilhar(list[0]) + "," + centavos;
        }
        return "R$ 0,00";
    }

    public static String formatarMilhar(String valor) {
        String novoValor = "";
        char[] values = valor.toCharArray();
        int contador = 1;
        for (int i = values.length - 1; i >= 0; i--) { // 7
            if (contador % 3 == 0) {
                novoValor = "." + values[i] + novoValor;
            } else {
                novoValor = values[i] + novoValor;
            }
            contador++;
        }
        return novoValor;
    }
}
