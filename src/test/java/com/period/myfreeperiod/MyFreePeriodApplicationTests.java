package com.period.myfreeperiod;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyFreePeriodApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void testDatabaseConnection(){
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource("jdbc:mysql://localhost:3306/my_free_period_db");
        try {
            Connection connection = dataSource.getConnection("Martha", "DrDanladi@@2020");
            System.out.println(connection);
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
