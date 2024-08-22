package nzuzi.joao.criacao_consulta_numero_bi.util;

import java.util.HashMap;
import java.util.Map;

public class BIUtils {

    static final Map<Integer, Integer> MAPS;
    static {
        MAPS = new HashMap<>();
        MAPS.put(3, 1_000);
        MAPS.put(9, 1_000_000_000);
    }

    public static String generateBINumber(final String provinceCode) {
        return number(9) + provinceCode + number(3);
    }

    public static String number(int numberOfDigits) {
        int value = (int)(Math.random() * MAPS.get(numberOfDigits));

        return String.format("%0" + numberOfDigits + "d", value);
    }
}
