package main.java.com.datas;



import com.sun.rowset.internal.Row;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author medal
 * @create 2019-11-04 21:23
 *
 * 模拟数据生成，数据直接进hive表
 *
 * **/
public class MockData {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                create_user_visit_action();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
               create_user_info();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                create_product_info();
            }
        }).start();
    }

    public static void create_user_visit_action(){
        List<Row> rows = new ArrayList<Row>();

        String[] searchKeywords = new String[] {"火锅", "蛋糕", "重庆辣子鸡", "重庆小面",
                "呷哺呷哺", "新辣道鱼火锅", "国贸大厦", "太古商场", "日本料理", "温泉"};
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:HH").format(new Date());
        String[] actions = new String[]{"search", "click", "order", "pay"};
        Random random = new Random();

        for(int i = 0; i < 100; i++) {
            long userid = random.nextInt(100);

            for (int j = 0; j < 10; j++) {
                // 用户sessionid
                String sessionid = UUID.randomUUID().toString().replace("-", "");
                // 基本时间
                String baseActionTime = date + " " + random.nextInt(23);

                // 点击品类id
                Long clickCategoryId = null;

                for (int k = 0; k < random.nextInt(100); k++) {
                    // 页面id
                    long pageid = random.nextInt(10);
                    // 操作时间
                    String actionTime = baseActionTime + ":" + StringUtils.fulfuill(String.valueOf(random.nextInt(59))) + ":" + StringUtils.fulfuill(String.valueOf(random.nextInt(59)));
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
                    } else if ("pay".equals(action)) {
                        payCategoryIds = String.valueOf(random.nextInt(100));
                        payProductIds = String.valueOf(random.nextInt(100));
                    }
                }
            }
        }
    }

    public static void create_user_info(){
        String[] sexes = new String[]{"male", "female"};
        for(int i = 0; i < 100; i ++) {
            long userid = i;
            String username = "user" + i;
            String name = "name" + i;
            int age = random.nextInt(60);
            String professional = "professional" + random.nextInt(100);
            String city = "city" + random.nextInt(100);
            String sex = sexes[random.nextInt(2)];

            Row row = RowFactory.create(userid, username, name, age,
                    professional, city, sex);
            rows.add(row);
        }
    }

    public static void create_product_info(){
        int[] productStatus = new int[]{0, 1};

        for(int i = 0; i < 100; i ++) {
            long productId = i;
            String productName = "product" + i;
            String extendInfo = "{\"product_status\": " + productStatus[random.nextInt(2)] + "}";

            Row row = RowFactory.create(productId, productName, extendInfo);
            rows.add(row);
        }
    }
}
