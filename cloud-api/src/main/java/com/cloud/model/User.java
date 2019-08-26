package com.cloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: wujid
 * @Date: 2019/8/24 10:35
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class User implements Serializable {

    private String id;
    private String age;
    private String name;
    private String dbSource;
}
