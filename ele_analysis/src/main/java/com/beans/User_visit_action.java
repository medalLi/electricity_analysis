package com.beans;

/**
 * @author medal
 * @create 2019-11-04 21:38
 **/
public class User_visit_action {

    private String dateTime;
    private long user_id;
    private String session_id;
    private long page_id;
    private String action_time;
    private String search_keyword;
    private long click_category_id;
    private long click_product_id;
    private String order_category_ids;
    private String order_product_ids;
    private String pay_category_ids;
    private String pay_product_ids;
    private long city_id;

    public User_visit_action(String dateTime, long user_id, String session_id, long page_id, String action_time, String search_keyword, long click_category_id, long click_product_id, String order_category_ids, String order_product_ids, String pay_category_ids, String pay_product_ids, long city_id) {
        this.dateTime = dateTime;
        this.user_id = user_id;
        this.session_id = session_id;
        this.page_id = page_id;
        this.action_time = action_time;
        this.search_keyword = search_keyword;
        this.click_category_id = click_category_id;
        this.click_product_id = click_product_id;
        this.order_category_ids = order_category_ids;
        this.order_product_ids = order_product_ids;
        this.pay_category_ids = pay_category_ids;
        this.pay_product_ids = pay_product_ids;
        this.city_id = city_id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public long getPage_id() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id = page_id;
    }

    public String getAction_time() {
        return action_time;
    }

    public void setAction_time(String action_time) {
        this.action_time = action_time;
    }

    public String getSearch_keyword() {
        return search_keyword;
    }

    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    public long getClick_category_id() {
        return click_category_id;
    }

    public void setClick_category_id(long click_category_id) {
        this.click_category_id = click_category_id;
    }

    public long getClick_product_id() {
        return click_product_id;
    }

    public void setClick_product_id(long click_product_id) {
        this.click_product_id = click_product_id;
    }

    public String getOrder_category_ids() {
        return order_category_ids;
    }

    public void setOrder_category_ids(String order_category_ids) {
        this.order_category_ids = order_category_ids;
    }

    public String getOrder_product_ids() {
        return order_product_ids;
    }

    public void setOrder_product_ids(String order_product_ids) {
        this.order_product_ids = order_product_ids;
    }

    public String getPay_category_ids() {
        return pay_category_ids;
    }

    public void setPay_category_ids(String pay_category_ids) {
        this.pay_category_ids = pay_category_ids;
    }

    public String getPay_product_ids() {
        return pay_product_ids;
    }

    public void setPay_product_ids(String pay_product_ids) {
        this.pay_product_ids = pay_product_ids;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        return "User_visit_action{" +
                "dateTime='" + dateTime + '\'' +
                ", user_id=" + user_id +
                ", session_id='" + session_id + '\'' +
                ", page_id=" + page_id +
                ", action_time='" + action_time + '\'' +
                ", search_keyword='" + search_keyword + '\'' +
                ", click_category_id=" + click_category_id +
                ", click_product_id=" + click_product_id +
                ", order_category_ids='" + order_category_ids + '\'' +
                ", order_product_ids='" + order_product_ids + '\'' +
                ", pay_category_ids='" + pay_category_ids + '\'' +
                ", pay_product_ids='" + pay_product_ids + '\'' +
                ", city_id=" + city_id +
                '}';
    }
}
