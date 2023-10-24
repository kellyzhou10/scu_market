package com.project.scu_market;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping(value = "/user/getUser",produces = "application/json")
    public ResponseEntity<User> getUser(@RequestHeader("Cookie") String ticket) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        User user = findUser(ticket);
        if (user != null) {
            headers.set("msg", "success");
            status = HttpStatus.OK;
        }
        else{
            headers.set("msg","item does not exist");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<User>(user, headers, status);
    }

    @PostMapping(value = "/user/register",produces = "application/json")
    public ResponseEntity<User> createUser(
            @RequestHeader("Content-Type") String contentType,
            @RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if ("application/json".equals(contentType)) {
            if (findUser(user.getTicket()) == null){
                registerUser(user);
                headers.set("msg", "success");
                status = HttpStatus.OK;
            }
            else{
                headers.set("msg", "username already exists");
                status = HttpStatus.CONFLICT;
            }
        }
        else {
            headers.set("msg", "unsupported file");
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }
        return new ResponseEntity<User>(user, headers, status);
    }

    @GetMapping(value = "/getItem/",produces = "application/json")
    public ResponseEntity<Product[]> getProducts(
            @RequestParam("search_key") String keyword,
            @RequestParam(value = "by_likes", defaultValue = "0") int rankBy) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        Product[] products = getItem(keyword,rankBy);
        headers.set("msg", "success");
        status = HttpStatus.OK;
        if error {
            headers.set("msg","invalid query");
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Product[]>(products, headers, status);
    }

    @GetMapping(value = "/getDetail",produces = "application/json")
    public ResponseEntity<Product> getDetail(@RequestParam("id") int productId) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        Product product = findProduct(productId);;
        if (product != null) {
            headers.set("msg", "success");
            status = HttpStatus.OK;
        }
        else{
            headers.set("msg","item does not exist");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Product>(product, headers, status);
    }

    @PostMapping(value = "/addItem",produces = "application/json")
    public ResponseEntity<Product> createProduct(
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("Cookie") String ticket,
            @RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if ("application/json".equals(contentType)) {
            if (findProduct(product.getTicket()) == null){
                addProduct(product);
                headers.set("msg", "success");
                status = HttpStatus.OK;
            }
            else{
                headers.set("msg", "username already exists");
                status = HttpStatus.CONFLICT;
            }
        }
        else {
            headers.set("msg", "unsupported file");
            status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }
        return new ResponseEntity<Product>(product, headers, status);
    }

    @GetMapping(value = "/addLike",produces = "application/json")
    public ResponseEntity<Product> updateLikes(
            @RequestHeader("Cookie") String ticket,
            @RequestParam("id") int productId) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        Product product = findProduct(productId);
        if (product != null) {
            product.addLike();
            updateProduct();
            headers.set("msg", "success");
            status = HttpStatus.OK;
        }
        else{
            headers.set("msg","item does not exist");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Product>(product, headers, status);
    }

//    @PostMapping(value = "/addComment",produces = "application/json")
//    public ResponseEntity<String> addComment(
//            @RequestHeader("Content-Type") String contentType,
//            @RequestHeader("Cookie") String ticket,
//            @RequestBody Product product) {
//
//    }

}