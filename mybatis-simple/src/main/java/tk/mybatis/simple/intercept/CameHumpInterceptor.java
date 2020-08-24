package tk.mybatis.simple.intercept;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

/**
 * 下划线转驼峰命名插件
 *
 * @author xianglin
 */
@Intercepts({
        @Signature(
                type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class}
        )
})
@SuppressWarnings("unchecked")
public class CameHumpInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 先执行查询，得到结果
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object obj : list) {
            if (obj instanceof Map) {
                processMap((Map<String, Object>) obj);
            } else {
                break;
            }
        }
        return list;
    }

    private void processMap(Map<String, Object> map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for (String key : keySet) {
            if (key.charAt(0) >= 'A' && key.charAt(0) <= 'Z' || key.contains("_")) {
                Object value = map.remove(key);
                map.put(underLineToCamel(key), value);
            }
        }
    }

    private String underLineToCamel(String key) {
        StringBuilder keySb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c == '_') {
                if (keySb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    keySb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    keySb.append(Character.toLowerCase(c));
                }
            }
        }
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties.toString());
    }
}
