import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Auther: zhang
 * @Date: 2019-05-18 17:46
 * @Description:
 */
public class test {

    public static void main(String[] args) {
        String str = "abcdef";
        for (int i = 0; i < 1000; i++) {
            str = str + i;
        }
    }
}
