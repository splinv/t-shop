package com.tsmc.demo.shop.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private long id;

    private Timestamp creationTime;

    private String name;
}
