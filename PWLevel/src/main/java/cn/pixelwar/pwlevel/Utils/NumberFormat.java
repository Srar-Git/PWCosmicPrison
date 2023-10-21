package cn.pixelwar.pwlevel.Utils;

import java.text.DecimalFormat;

public class NumberFormat {
    public String getIntFormat(int Num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Num);
    }

    public String getLongFormat(long Num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Num);
    }

    public String getFloatFormat(float Num) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(Num);
    }


}
