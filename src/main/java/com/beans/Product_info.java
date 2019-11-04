package main.java.com.beans;

/**
 * @author medal
 * @create 2019-11-04 21:49
 **/
public class Product_info {

    private long product_id;
    private String product_name;
    private String extend_info;

    public Product_info(long product_id, String product_name, String extend_info) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.extend_info = extend_info;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getExtend_info() {
        return extend_info;
    }

    public void setExtend_info(String extend_info) {
        this.extend_info = extend_info;
    }

    @Override
    public String toString() {
        return "Product_info{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", extend_info='" + extend_info + '\'' +
                '}';
    }
}
