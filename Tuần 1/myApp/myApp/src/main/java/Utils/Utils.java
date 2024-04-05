package Utils;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    public static String pathImage(String path) {
        int index = path.lastIndexOf("\\");
        int index2 = path.lastIndexOf("\\", index - 1);
        return path.substring(index2 + 1);
    }

    public static boolean isSpecialCharacters(String value) {
        return value.contains("'") || value.contains("\"");
        //   return !Pattern.matches("^[a-zA-Z0-9 :-]*$", value);
    }


    //region numberFormat
    public static String numberToString(Number number) {
        // 125.000 đ
        // tạo 1 NumberFormat để định dạng tiền tệ theo tiêu chuẩn của Việt Nam
        // đơn vị tiền tệ của Việt Nam là đồng
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(number);
    }

    public static Number StringToNumber(String currency) {
        return Long.parseLong(currency.replaceAll("[.đ,]", "").trim());
    }
    //endregion

    //region tab
    public static int getIndexWithTitle(JTabbedPane tabbedPane, String title) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            String tabTitle = tabbedPane.getTitleAt(i);
            if (tabTitle.equals(title))
                return i;
        }
        return -1;
    }
    //endregion
}
