package me.coding.rocketmq;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

public class DbUtils {

    private static JdbcTemplate jdbcTemplate;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.66.41:3406/test?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("123456");
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(10);
        DataSource dataSource = new HikariDataSource(config);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static Map query(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

    private static void update(String sql, Object... obj) {
        jdbcTemplate.update(sql, obj);
    }

    public static void main(String[] args) {
        System.out.println(query("select * from t_order where id = 64"));

    }
}
