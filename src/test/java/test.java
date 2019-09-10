import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
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
//        SQLManager sqlManager = getSqlManager();
//        for (int i = 0; i < 10; i++) {
//            UserInfo userInfo = sqlManager.query(UserInfo.class).orderBy("rand()").single();
//            System.out.println(userInfo.getUserId());
//        }

        URL urlModel = new URL("http://img.miaocang.cc/mc/df/201909101144340664.jpg");
        // 打开连接
        URLConnection con = urlModel.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream inputStream = con.getInputStream();
        // 输出的文件流
//            File file=new File("/alidata/www/miaoto.net/images/"+System.currentTimeMillis()+".jpg");
        File file = new File("E:\\" + System.currentTimeMillis() + ".jpg");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        int len;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.flush();
        inputStream = new FileInputStream(file);
        String key = AliyunUtil.uploadFile(inputStream, 3);//上传图片
        System.out.println(key);
        outputStream.close();
        inputStream.close();
        if (file.exists()) {
            file.delete();
        }
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
