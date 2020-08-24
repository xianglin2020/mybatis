package tk.mybatis.simple.util;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author xianglin
 */
public class PrivilegeProvider {
    /**
     * 提供给tk.mybatis.simple.mapper.PrivilegeMapper#selectById(java.lang.Long)用的SQL
     *
     * @param id id
     * @return 将要执行的SQL
     */
    public String selectById(final Long id) {
        return new SQL() {{
            SELECT("id,privilege_name,privilege_url");
            FROM("sys_privilege");
            WHERE("id = #{id}");
        }}.toString();
    }

}
