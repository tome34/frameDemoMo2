package com.example.tome.module_shop_mall.dao.db;

import com.example.tome.module_shop_mall.bean.SearchHistoryBean;
import java.util.List;

/**
 * @author quchao
 * @date 2017/11/27
 */

public interface DbHelper {

    /**
     * 增加历史数据
     *
     * @param keyword  added string
     * @return  List<HistoryData>
     */
    List<SearchHistoryBean> addHistoryData(String keyword);

    /**
     * Clear search history data
     */
    void clearHistoryData();

    /**
     * Load all history data
     *
     * @return List<HistoryData>
     */
    List<SearchHistoryBean> loadAllHistoryData();

}
