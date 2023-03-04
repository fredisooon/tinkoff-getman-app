//package com.example.getmanapp.config;
//
//import com.example.getmanapp.converter.HttpHeadersReaderConverter;
//import com.example.getmanapp.converter.HttpHeadersWriterConverter;
//import io.r2dbc.spi.ConnectionFactories;
//import io.r2dbc.spi.ConnectionFactory;
//import io.r2dbc.spi.ConnectionFactoryOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Configuration
//public class R2dbcConfig extends AbstractR2dbcConfiguration {
//
//    @Autowired
//    private HttpHeadersReaderConverter httpHeadersReaderConverter;
//
//    @Autowired
//    private HttpHeadersWriterConverter httpHeadersWriterConverter;
//
//
//    @Override
//    public ConnectionFactory connectionFactory() {
//        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
//                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
//                .option(ConnectionFactoryOptions.HOST, "localhost")
//                .option(ConnectionFactoryOptions.PORT, 5432)
//                .option(ConnectionFactoryOptions.USER, "postgres")
//                .option(ConnectionFactoryOptions.PASSWORD, "password")
//                .option(ConnectionFactoryOptions.DATABASE, "mydb")
//                .build();
//
//        return ConnectionFactories.get(options);
//    }
//
//    @Override
//    public R2dbcCustomConversions r2dbcCustomConversions() {
//        List<Converter<?, ?>> converters = new ArrayList<>();
//        converters.add(httpHeadersReaderConverter);
//        converters.add(httpHeadersWriterConverter);
//        return new R2dbcCustomConversions(getStoreConversions(), converters);
//    }
//}