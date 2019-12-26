package com.tensquare.tensquare_base.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 表名注解 @TableName
 * 主键注解 @TableId
 * 字段注解 @TableField(exist属性:是否为数据库表字段:默认true存在，false不存在)
 */
@TableName(value = "tb_label")
public class Label {

    @TableId
    private String id;
    //标签名称
    private String labelname;
    //状态
    private String state;
    //使用数量
    private Long count;
    //关注数
    private Long fans;
    //是否推荐
    private String recommend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
