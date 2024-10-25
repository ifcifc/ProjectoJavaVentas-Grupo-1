package com.ventas.data;

import com.ventas.models.BaseModel;
import com.ventas.utils.FileRead;
//import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class DataBase {
    /*public static final String DB_PATH = "VentasDB.sqlite";
    private final SQLiteDataSource dataSource;

    public DataBase() {
        this.dataSource = new SQLiteDataSource();

        boolean dbFile = new File(DB_PATH).exists();

        this.dataSource.setUrl("jdbc:sqlite:" + DB_PATH);

        System.out.println("Conexión con DataSource establecida.");

        if (!dbFile){
            this.secureTransaction(stmt->{
                try {
                    stmt.executeUpdate(FileRead.read("VentasDB.sql"));
                } catch (SQLException | URISyntaxException | IOException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    return false;
                }
                return true;
            });
        }

    }

    public boolean secureTransaction(Function<PreparedStatement, Boolean> exec, String sql){
        try (Connection conn = this.dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                Boolean apply = exec.apply(stmt);
                if(apply){
                    conn.commit();
                }else{
                    conn.rollback();
                }

                return apply;
            }catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean secureTransaction(Function<Statement, Boolean> exec){
        try (Connection conn = this.dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try(Statement stmt = conn.createStatement()){
                Boolean apply = exec.apply(stmt);
                if(apply){
                    conn.commit();
                }else{
                    conn.rollback();
                }
                return apply;
            }catch (Exception e) {
                conn.rollback();
                e.printStackTrace();

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public SQLiteDataSource getDataSource() {
        return dataSource;
    }

    public HashMap<String, Object> parseMap(ResultSet resultSet) throws SQLException {
        HashMap<String, Object> parse = new HashMap<>();

        ResultSetMetaData metaData = resultSet.getMetaData();
        for(int i=1;i<metaData.getColumnCount();i++){
            parse.put(metaData.getColumnName(i),
                    this.getValue(resultSet, i));
        }

        return parse;
    }


    public ArrayList<HashMap<String, Object>> parseMaps(ResultSet resultSet) throws SQLException {
        ArrayList<HashMap<String, Object>> parse = new ArrayList<>();

        while ( resultSet.next() ) {
            parse.add(this.parseMap(resultSet));
        }

        return parse;
    }

    public <T extends BaseModel> ArrayList<T> parseResults(ResultSet resultSet, Class<T> modelClass) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        ArrayList<T> ret = new ArrayList<>();
        while ( resultSet.next() ) {
            ret.add(this.parseResult(resultSet, modelClass));
        }

        return ret;
    }

    public <T extends BaseModel> T parseResult(ResultSet resultSet, Class<T> modelClass) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        T ret = modelClass.newInstance();

        Field[] declaredFields = modelClass.getDeclaredFields();
        for(int i=1;i<metaData.getColumnCount();i++){
            String columnName = metaData.getColumnName(i);
            boolean existField = Arrays.stream(declaredFields).anyMatch(field -> field.getName().equals(columnName));
            if(!existField)continue;
            Field declaredField = modelClass.getDeclaredField(columnName);
            declaredField.setAccessible(true);
            declaredField.set(ret, this.getValue(resultSet, i));
        }

        return ret;
    }

    private Object getValue(ResultSet resultSet, int column) throws SQLException {
        String columnTypeName = resultSet.getMetaData().getColumnTypeName(column);

        return switch (columnTypeName) {
            case "BOOLEAN" -> resultSet.getBoolean(column);
            default -> resultSet.getObject(column);
        };
    }*/

}
