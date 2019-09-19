import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spider.common.AliyunUtil;
import com.spider.common.miaocang.PicParams;
import com.spider.common.pojo.UserInfo;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-05-18 17:46
 * @Description:
 */
public class test {


    public static void main(String[] args) throws IOException {
        String aaa = "[\"http://img.miaocang.cc/mc/5617532814/201909161714530235.jpg\",\"http://img.miaocang.cc/mc/5617532814/201909161714530312.jpg\",\"http://img.miaocang.cc/mc/5617532814/201909161714530379.jpg\",\"http://img.miaocang.cc/mc/5617532814/201909161714530374.jpg\"]";
        List<String> array = JSON.parseArray(aaa,String.class);
        System.out.println(array);
    }

    public static SQLManager getSqlManager() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://123.57.6.171:3306/miaotu_formal?characterEncoding=utf8";
        String userName = "root";
        String password = "c2bwERZKB3H1";
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        return sqlManager;
    }
}
