package com.example.tome.module_shop_mall.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author tome
 * @date 2018/3/5
 *  @Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类;
 *  @nameInDb：在数据库中的名字，如不写则为实体中类名；
 *  @Id：选择一个long / Long属性作为实体ID。 在数据库方面，它是主键。 参数autoincrement是设置ID值自增；
 *  @NotNull：使该属性在数据库端成为“NOT NULL”列。 通常使用@NotNull标记原始类型（long，int，short，byte）是有意义的；
 *  @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化。
 *  点击AndroidStudio中的MakeProject 生成代码
 */

@Entity
public class SearchHistoryBean {

    @Id(autoincrement = true)
    private long _id;
    private String keyword;
    @Generated(hash = 300580982)
    public SearchHistoryBean(long _id, String keyword) {
        this._id = _id;
        this.keyword = keyword;
    }
    @Generated(hash = 1570282321)
    public SearchHistoryBean() {
    }
    public long get_id() {
        return this._id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public String getKeyword() {
        return this.keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
