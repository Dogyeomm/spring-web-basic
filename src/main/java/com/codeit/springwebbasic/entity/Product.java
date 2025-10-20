package com.codeit.springwebbasic.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long serialNo; // 상품 시리얼번호
    private String name; // 상품명
    private int price; // 상품 가격

}