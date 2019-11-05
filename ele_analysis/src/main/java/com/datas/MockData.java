package com.datas;

import com.beans.Product_info;
import com.beans.User_info;
import com.beans.User_visit_action;
import com.pool.KuduPool;
import com.sun.rowset.internal.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.datanucleus.NucleusContext.random;

/**
 * @author medal
 * @create 2019-11-04 21:23
 *
 * 模拟数据生成，数据直接进hive表

 * **/
public class MockData {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                create_user_visit_action();
            }
        }).start();

//        new Thread(new Runnable() {
//            public void run() {
//                create_user_info();
//            }
//        }).start();

//        new Thread(new Runnable() {
//            public void run() {
//                create_product_info();
//            }
//        }).start();
   }

    public static void create_user_visit_action () {
        String[] searchKeywords = new String[]{"火锅", "蛋糕", "重庆辣子鸡", "重庆小面",
                "呷哺呷哺", "新辣道鱼火锅", "国贸大厦", "太古商场", "日本料理", "温泉"};
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:HH").format(new Date());
        String[] actions = new String[]{"search", "click", "order", "pay"};
        Random random = new Random();
        User_visit_action user_visit_action = null;

        String sql = "insert into test.user_visit_action(date_time,user_id,session_id," +
                "page_id,action_time,search_keyword,click_category_id,click_product_id," +
                "order_category_ids,order_product_ids,pay_category_ids,pay_product_ids," +
                "city_id) values";
        for (int i = 0; i < 100; i++) {
            long userid = random.nextInt(100);
            Connection connection = KuduPool.getInstance().getConnection();
            StringBuffer sb = new StringBuffer("");
            for (int j = 0; j < 10; j++) {
                // 用户sessionid
                String sessionid = UUID.randomUUID().toString().replace("-", "");
                // 基本时间
            //    String baseActionTime = date + " " + random.nextInt(23);
                // 点击品类id
                Long clickCategoryId = null;
                for (int k = 0; k < random.nextInt(100); k++) {
                    // 页面id
                    long pageid = random.nextInt(10);
                    // 操作时间
                    String actionTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:HH")
                            .format(System.currentTimeMillis() + random.nextInt(200000) - 300000);
                    // 搜素关键词
                    String searchKeyword = null;
                    // 点击商品id
                    Long clickProductId = null;
                    // 订单品类id
                    String orderCategoryIds = null;
                    // 订单商品id
                    String orderProductIds = null;
                    // 支付品类id
                    String payCategoryIds = null;
                    // 支付商品id
                    String payProductIds = null;
                    // 城市id
                    long cityId = random.nextInt(100);
                    String action = actions[random.nextInt(4)];

                    if ("search".equals(action)) {
                        searchKeyword = searchKeywords[random.nextInt(10)];
                    } else if ("click".equals(action)) {
                        if (clickCategoryId == null) {
                            clickCategoryId = Long.valueOf(String.valueOf(random.nextInt(100)));
                        }
                        clickProductId = Long.valueOf(String.valueOf(random.nextInt(100)));
                    } else if ("order".equals(action)) {
                        orderCategoryIds = String.valueOf(random.nextInt(100));
                        orderProductIds = String.valueOf(random.nextInt(100));
                    } else {
                        payCategoryIds = String.valueOf(random.nextInt(100));
                        payProductIds = String.valueOf(random.nextInt(100));
                    }
//                    if(searchKeyword == null){
//                        searchKeyword = "";
//                    }
//                    user_visit_action = new User_visit_action(date, userid, sessionid, pageid,
//                            actionTime, searchKeyword, clickCategoryId, clickProductId,
//                            orderCategoryIds, orderProductIds, payCategoryIds, payProductIds, cityId);
//                    System.out.println(user_visit_action);
                    sb.append("('" + date + "'," + userid + ",'"
                            + sessionid + "'," + pageid + ",'"
                            + actionTime + "','" + searchKeyword
                            + "','" + clickCategoryId + "','" + clickProductId
                            + "','" + orderCategoryIds + "','" + orderProductIds
                            + "','" +payCategoryIds + "','" + payProductIds
                            + "'," + cityId + "),");
                }
            }
            try {
                PreparedStatement ps = connection.prepareStatement(sql + sb.substring(0, sb.length() - 1));
                ps.execute();
                sb.delete(0, sb.length());
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void create_user_info(){
        String[] sexes = new String[]{"male", "female"};
        User_info user_info = null;
        String sql = "insert into test.user_info(user_id,username,name,age," +
                "professional,city,sex) values";
        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i < 100; i ++) {
            long userid = i;
            String username = "user" + i;
            String name = "name" + i;
            int age = random.nextInt(60);
            String professional = "professional" + random.nextInt(100);
            String city = "city" + random.nextInt(100);
            String sex = sexes[random.nextInt(2)];
            user_info = new User_info(userid,username,name,age,professional,city,sex);
            sb.append("("+user_info.getUser_id()+",'"+user_info.getUserName()+"','"
                    +user_info.getName()+"',"+user_info.getAge()+",'"
                    +user_info.getProfessional()+"','"+user_info.getCity()+"','"+user_info.getSex()+"'),");
        }
        Connection connection = KuduPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql+sb.substring(0,sb.length()-1));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void create_product_info(){
        int[] productStatus = new int[]{0, 1};
        Product_info product_info = null;
        String sql = "insert into test.product_info(product_id,product_name," +
                "extend_info) values";
        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i < 100; i ++) {
            long productId = i;
            String productName = "product" + i;
            String extendInfo = "{\"product_status\": " + productStatus[random.nextInt(2)] + "}";
            product_info = new Product_info(productId,productName,extendInfo);
           sb.append("("+product_info.getProduct_id()+",'"+product_info.getProduct_name()+"','"+product_info.getExtend_info()+"'),");
        }
        Connection connection = KuduPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql+sb.substring(0,sb.length()-1));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
