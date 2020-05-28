package com.woniuxy.springcloud.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("学生实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @ApiModelProperty("学号")
    private Integer sid;
    private String sname;
    private Integer age;
}
