package id.go.kominfo.dts;

import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Utils {
    public static void setFullscreen(Window window) {
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
