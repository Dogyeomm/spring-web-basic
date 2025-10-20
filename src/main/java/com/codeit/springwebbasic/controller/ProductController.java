package com.codeit.springwebbasic.controller;

import com.codeit.springwebbasic.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@Controller -> 타임리프 같은 뷰를 직접 핸들링하는 컨트롤러
@RestController // JSON을 리턴하는 컨트롤러
@RequestMapping("/products") // 공통 url 매핑
public class ProductController {

    // DB가 없으니 가상의 메모리 상품 저장소 선언
    private Map<Long, Product> productMap = new HashMap<>();

    // 상품의 시리얼넘버를 순차 생성
    private long nextId = 1;

    public ProductController() {
        productMap.put(nextId, new Product(nextId, "에어컨", 1000000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "세탁기", 1500000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "공기청정기", 300000));
        nextId++;
    }

    // 1. 쿼리스트링 읽기 -> url?name=value&name=value&name=value....
    // 데이터가 url에 노출되어도 크게 문제없는 방식 (조회 -> 검색어, 게시물 조회에서 글 번호 등...)
    // ?id=???&price=???

//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    @GetMapping("/products")
//    public Product getProduct(HttpServletRequest request) {
//        // HttpServletRequest: 요청 관련 정보를 담은 객체
//        String id = request.getParameter("id");
//        String price = request.getParameter("price");
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return productMap.get(Long.parseLong(id));
//    }

    // localhost:8081/products?id=???&price=???
    @GetMapping
    public Product getProduct(
            @RequestParam("id") Long id,
            @RequestParam(value = "price", required = false, defaultValue = "5000") int price) {
        System.out.println("id = " + id);
        System.out.println("price = " + price);
        return productMap.get(id);
    }

    // localhost:8081/products/34 -> 1번 상품 조회 (url 경로상에 데이터가 존재하는 경우)
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        System.out.println("id = " + id);
        return productMap.get(id);
    }


}