package com.jn.agileway.jdbc.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jn.agileway.jdbc.datasource.DataSourceProperties;
import com.jn.langx.util.Maths;

import javax.sql.DataSource;
import java.util.Properties;

import static com.alibaba.druid.pool.DruidDataSourceFactory.*;

public class AlibabaDruidDataSources {
    private AlibabaDruidDataSources() {
    }

    public static DataSource createDataSource(DataSourceProperties properties) throws Exception {
        Properties props = properties.getDriverProps();
        if (props == null) {
            props = new Properties();
        }

        String username = properties.getUsername();
        if (username != null) {
            props.setProperty(PROP_USERNAME, username);
        }

        String password = properties.getPassword();
        if (password != null) {
            props.setProperty(PROP_PASSWORD, password);
        }

        String url = properties.getUrl();
        if (url != null) {
            props.setProperty(PROP_URL, url);
        }

        String driverClassName = properties.getDriverClassName();
        if (driverClassName != null) {
            props.setProperty(PROP_DRIVERCLASSNAME, driverClassName);
        }

        props.setProperty(PROP_DEFAULTAUTOCOMMIT, "" + properties.isAutoCommit());
        props.setProperty(PROP_DEFAULTREADONLY, "" + properties.isReadOnly());
        props.setProperty(PROP_DEFAULTTRANSACTIONISOLATION, properties.getTransactionIsolationName());

        String catalog = properties.getCatalog();
        if (catalog != null) {
            props.setProperty(PROP_DEFAULTCATALOG, catalog);
        }

        props.setProperty(PROP_INITIALSIZE, "" + properties.getInitialSize());
        props.setProperty(PROP_MINIDLE, "" + properties.getMinIdle());
        props.setProperty(PROP_MAXIDLE, "" + Maths.max(8, properties.getMinIdle()));
        props.setProperty(PROP_MAXACTIVE, "" + Maths.max(8, properties.getMaxPoolSize()));


        String validationQuery = properties.getValidationQuery();
        if (validationQuery != null) {
            props.setProperty(PROP_VALIDATIONQUERY, validationQuery);
        }
        return DruidDataSourceFactory.createDataSource(props);
    }
}
