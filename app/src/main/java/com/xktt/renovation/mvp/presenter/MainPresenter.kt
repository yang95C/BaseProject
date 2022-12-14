package com.xktt.renovation.mvp.presenter

import com.xktt.renovation.baselibs.ext.ss
import com.xktt.renovation.baselibs.ext.sss
import com.xktt.renovation.baselibs.http.HttpStatus
import com.xktt.renovation.baselibs.http.exception.ExceptionHandle
import com.xktt.renovation.baselibs.mvp.BasePresenter
import com.xktt.renovation.baselibs.rx.SchedulerUtils
import com.xktt.renovation.mvp.contract.MainContract
import com.xktt.renovation.mvp.model.MainModel

/**
 * @author admin
 * @date 2018/11/20
 * @desc
 */
class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {

    override fun createModel(): MainContract.Model? = MainModel()

    override fun getBanner() {
        mView?.showLoading()
        addDisposable(mModel?.getBanners()
                ?.compose(SchedulerUtils.ioToMain())
                ?.subscribe({
                    mView?.hideLoading()
                    when {
                        it.errorCode == HttpStatus.SUCCESS -> mView?.showBanners(it.data)
                        it.errorCode == HttpStatus.TOKEN_INVALID -> {
                            // Token 过期，重新登录
                        }
                        else -> mView?.showError(it.errorMsg)
                    }
                }, {
                    mView?.hideLoading()
                    mView?.showDefaultMsg(ExceptionHandle.handleException(it))
                })
        )
    }

    override fun getBanner2() {
        mModel?.getBanners()?.ss(mModel, mView, onSuccess = {
            mView?.showBanners(it.data)
        })
    }

    override fun getBanner3() {
        addDisposable(mModel?.getBanners()?.sss(mView, onSuccess = {
            mView?.showBanners(it.data)
        }))
    }

    override fun login(username: String, password: String) {
        mModel?.login(username, password)?.ss(mModel, mView, onSuccess = {
            mView?.loginSuccess()
        })
    }

    override fun getBannerList() {
        mModel?.getBannerList()?.ss(mModel, mView, onSuccess = {
            mView?.showBannerList(it.data)
        })
    }

    override fun getCollectList(page: Int) {
        mModel?.getCollectList(page)?.ss(mModel, mView, onSuccess = {
            mView?.showCollectList(it.data)
        })
    }

    override fun logout() {
        mModel?.logout()?.ss(mModel, mView, onSuccess = {
            mView?.logoutSuccess()
        })
    }

    override fun getSubscribeList(token: String, page: Int, pageSize: Int) {
        mModel?.getSubscribeList(token,page,pageSize)?.ss(mModel,mView,onSuccess = {
            mView?.logoutSuccess()
        })
    }

}