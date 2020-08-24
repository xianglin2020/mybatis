package tk.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MBG
 *
 * @author linxiang
 */
public class Generator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        // 保存警告信息
        List<String> warningList = new ArrayList<>();
        // 覆盖重复配置
        boolean override = true;
        // 读取MBG配置文件
        InputStream stream = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser parser = new ConfigurationParser(warningList);
        Configuration configuration = parser.parseConfiguration(stream);
        stream.close();

        // 创建MBG
        DefaultShellCallback callback = new DefaultShellCallback(override);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warningList);
        // 执行生成代码
        generator.generate(null);

        // 输出警告信息
        for (String s : warningList) {
            System.out.println(s);
        }
    }
}
