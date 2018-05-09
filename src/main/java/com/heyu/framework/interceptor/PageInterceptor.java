package com.heyu.framework.interceptor;

import com.heyu.framework.entity.Page;
import com.heyu.framework.utils.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Properties;


@Intercepts(@Signature(type = Executor.class,method = "query",
        args = {MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class}))
public class PageInterceptor implements Interceptor {

    private String dialectClass;//驱动类
    private String dataBaseType;

    @Override
    public Object intercept(final Invocation invocation) throws Exception {
        final Executor executor = (Executor) invocation.getTarget();//获取动态代理目标类对象
        final Object[] queryArgs = invocation.getArgs();//获取动态代理的参数列表
        final MappedStatement mapped = (MappedStatement) queryArgs[0];
        final Object parameter = queryArgs[1];
        RowBounds rowBounds = (RowBounds) queryArgs[2];//RowBounds有分页语句的limit和offset值；limit标识取几条；offset表示从第几条开始：默认第0条

        //如果不做分页操作
        if (rowBounds.getLimit() == RowBounds.NO_ROW_LIMIT && rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET) {
            return invocation.proceed();
        }

        if (StringUtils.isEmpty(dialectClass)) {
            //判断数据源，支持MySQL。Oracle、SQL server、postgresql
            Connection connection = executor.getTransaction().getConnection();

            DatabaseMetaData databaseMetaData = null;
            if (connection != null) {
                databaseMetaData = connection.getMetaData();
            }

            String databaseProductName = databaseMetaData.getDatabaseProductName();
            if (StringUtils.isEmpty(dataBaseType)) {
                dataBaseType = databaseProductName;
            }

            if (!StringUtils.isEmpty(databaseProductName) && !StringUtils.isEmpty(dataBaseType)) {
                dialectClass = dataBaseType;
            }
            setDialectClass(dialectClass);

        }

        Class clazz = Class.forName(dialectClass);
        Constructor constructor = clazz.getConstructor(new Class[]{MappedStatement.class, Object.class, RowBounds.class});
        constructor.newInstance(new Object[]{mapped, parameter, rowBounds});

        BoundSql boundSql = mapped.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();

        Page<Object> page = null;

        return null;
    }


    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public String getDialectClass() {
        return dialectClass;
    }

    public void setDialectClass(String dialectClass) {
        this.dialectClass = dialectClass;
    }

    public String getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }
}
