package com.ss.server.dao.jdbc;

import com.ss.server.dao.SSUserDao;
import com.ss.server.entity.SSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository("ssUserDao")
public class SSUserDaoImpl implements SSUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(SSUser user) {
        String sql = "insert into user (user_name,email, pass,passwd,u,d,plan,"
                + "transfer_enable,port,reg_date, invite_num,money) "
                + " values(?,?,?,?,?,?,?,?, ?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                user.getUserName(),
                user.getEmail(),
                user.getPass(),
                user.getPassword(),
                user.getUsed(),
                0,
                "A",
                user.getTransferEnable(),
                user.getPort(),
                new Date(),
                0,
                0
        });
    }


    @Override
    public SSUser get(String email) {
        String sql = "select uid, user_name, email, pass, passwd, t, u, d, plan, "
                + "transfer_enable, port, switch, enable, type, reg_date, money"
                + " from user where email=?";

        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            SSUser ss = new SSUser();
            ss.setCreateTime(rs.getTimestamp("reg_date"));
            ss.setEmail(rs.getString("email"));
            ss.setEnable(rs.getBoolean("enable"));
            ss.setID(rs.getInt("uid"));
            ss.setPass(rs.getString("pass"));
            ss.setPassword(rs.getString("passwd"));
            ss.setPlan(rs.getString("plan"));
            ss.setPort(rs.getInt("port"));
            ss.setTransferEnable(rs.getFloat("transfer_enable"));
            ss.setUsed(rs.getFloat("u"));
            ss.setUserName(rs.getString("user_name"));
            return ss;
        });
    }

    @Override
    public int getMaxPort() {
        String sql = " select max(port) from user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public float getLastFlow(String mac) {
        String sql = "select uid, user_name, email, pass, passwd, t, u, d, plan, "
                + "transfer_enable, port, switch, enable, type, reg_date, money"
                + " from user where user_name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{mac}, (rs, rowNum) -> {
            float used1 = rs.getFloat("u");
            float used2 = rs.getFloat("d");
            float total = rs.getFloat("transfer_enable");
            return total - (used1 + used2);
        });
    }

    @Override
    public void updateFlow(String mac, long flow) {
        String sql = "update user set transfer_enable=transfer_enable + ?  where user_name = ?;";
        jdbcTemplate.update(sql, new Object[]{
                flow, mac
        });
    }


}
