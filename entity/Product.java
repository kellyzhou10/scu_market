package entity;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.lang.Object;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


public class Product {

    Integer product_id;
    Integer owner_id;
    String title;
    String cover;
    String description;
    float price;
    Integer stock;
    Integer likedBy;
    String[] pics_path;
    Timestamp timestamp;
    Comment[]  comments;

    public Product(){

    }

    //when getItem, use this constructor
    public Product(Integer owner_id, String title, String cover, String description, float price, Timestamp timestamp) {
        this.owner_id = owner_id;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
    }

    //when getDetail, use this constructor
    public Product(Integer owner_id, String title, String description, float price, Integer stock, Integer likedBy, String[] pics_path, Timestamp timestamp, Comment[] comments) {
        this.owner_id = owner_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.likedBy = likedBy;
        this.pics_path = pics_path;
        this.timestamp = timestamp;
        this.comments = comments;
    }

    //when adding a product, use the following constructor
    public Product(Integer owner_id, String title, String cover, String description, float price, Integer stock, String[] pics_path) {
        this.owner_id = owner_id;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.pics_path = pics_path;
        timestamp = new Timestamp(System.currentTimeMillis());
    }


    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public void setLikedBy(Integer likedBy) {
        this.likedBy = likedBy;
    }

    public String[] getPics_path() {
        return pics_path;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getLikedBy() {
        return likedBy;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


}
