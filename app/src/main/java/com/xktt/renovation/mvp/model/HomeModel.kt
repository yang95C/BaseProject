package com.xktt.renovation.mvp.model

import com.xktt.renovation.baselibs.mvp.BaseModel
import com.xktt.renovation.bean.HttpResult
import com.xktt.renovation.http.MainRetrofit
import com.xktt.renovation.mvp.contract.HomeContract
import com.xktt.renovation.mvp.contract.TestContract
import io.reactivex.rxjava3.core.Observable

/**
 * @author chenxz
 * @date 2018/12/1
 * @desc
 */
class HomeModel : BaseModel(), HomeContract.Model {
    override fun getRecords(token: String, page: Int, pageSize: Int): Observable<HttpResult<Any>> {
       return MainRetrofit.service.getRecords(token,page,pageSize,"android","fotmkt_az","com.cd.yqty","2021081801")
    }

}