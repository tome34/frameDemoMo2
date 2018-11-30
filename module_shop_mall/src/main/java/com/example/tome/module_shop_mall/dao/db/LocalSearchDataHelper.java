package com.example.tome.module_shop_mall.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.tome.core.constants.BaseApplication;
import com.example.tome.module_shop_mall.bean.SearchHistoryBean;
import com.example.tome.module_shop_mall.dao.DaoMaster;
import com.example.tome.module_shop_mall.dao.DaoSession;
import com.example.tome.module_shop_mall.dao.SearchHistoryBeanDao;
import java.util.Iterator;
import java.util.List;

/**
 * @author tome
 * @date 2018/7/11  15:16
 * @describe ${本地搜索增删改查}
 */
public class LocalSearchDataHelper implements DbHelper{

    private static LocalSearchDataHelper helper;
    private DaoSession mDaoSession;
    static final String DB_NAME = "frameData.db";

    public static LocalSearchDataHelper getInstance() {
        if (helper == null) {
            helper = new LocalSearchDataHelper();
        }
        return helper;
    }

    private LocalSearchDataHelper() {

    }

    private void initGreenDao(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null){
            initGreenDao(BaseApplication.getAppContext());
        }
        return mDaoSession;
    }
    @Override
    public List<SearchHistoryBean> addHistoryData(String keyword) {
        SearchHistoryBeanDao historyDataDao = getDaoSession().getSearchHistoryBeanDao();
        List<SearchHistoryBean> historyDataList = historyDataDao.loadAll();
        SearchHistoryBean historyData = new SearchHistoryBean();
        historyData.set_id(System.currentTimeMillis());
        historyData.setKeyword(keyword);
        //重复搜索时进行历史记录前移
        Iterator<SearchHistoryBean> iterator = historyDataList.iterator();
        //不要在foreach循环中进行元素的remove、add操作，使用Iterator模式
        while (iterator.hasNext()) {
            SearchHistoryBean historyData1 = iterator.next();
            if (historyData1.getKeyword().equals(keyword)) {
                historyDataList.remove(historyData1);
                historyDataList.add(historyData);
                historyDataDao.deleteAll();
                historyDataDao.insertInTx(historyDataList);
                return historyDataList;
            }
        }
        if (historyDataList.size() < 10) {
            historyDataDao.insert(historyData);
        } else {
            historyDataList.remove(0);
            historyDataList.add(historyData);
            historyDataDao.deleteAll();
            historyDataDao.insertInTx(historyDataList);
        }
        return historyDataList;
    }

    @Override
    public void clearHistoryData() {
        SearchHistoryBeanDao historyDataDao = getDaoSession().getSearchHistoryBeanDao();
        historyDataDao.deleteAll();
    }

    @Override
    public List<SearchHistoryBean> loadAllHistoryData() {
        SearchHistoryBeanDao historyDataDao = getDaoSession().getSearchHistoryBeanDao();
        return historyDataDao.loadAll();
    }

}


