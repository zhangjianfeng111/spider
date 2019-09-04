import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhang
 * @Date: 2019-05-18 17:46
 * @Description:
 */
public class test {


    public static void main(String[] args) throws IOException {
        System.out.println(DateUtil.offset(new Date(), DateField.MONTH, 3));
    }
}
