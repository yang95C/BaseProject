package com.xktt.renovation.baselibs.config

import android.text.TextUtils
import com.blankj.utilcode.util.SPUtils

class UserManager private constructor(){
    //用户基本信息
    private val USER_NICKNAME = "nickname"
    private val USER_TOKEN = "usertoken"
    private val USER_HEAD = "userhead"
    private val USER_MOBILE = "usermobile"

    //用户地理位置
    private val USER_CITY = "usercity"
    private val USER_LOCATION = "userlocation"
    private val USER_LATITUDE = "userlatitude"
    private val USER_LONGTITUDE = "userlongtitude"

    private var spUtil:SPUtils

    companion object{
        private var instance: UserManager?=null
        get() {
            if (field == null){
                field = UserManager()
            }
            return field
        }
        fun get(): UserManager {
            return instance!!
        }
    }

    init {
        spUtil = SPUtils.getInstance("user_info")
    }

    fun getNickName():String{
        return spUtil!!.getString(USER_NICKNAME,"")
    }

    fun setNickName(nickname: String){
        spUtil!!.put(USER_NICKNAME,if (TextUtils.isEmpty(nickname)) "" else nickname)
    }

    fun getUserToken():String{
        return spUtil!!.getString(USER_TOKEN,"")
    }

    fun setUserToken(token: String){
        spUtil!!.put(USER_TOKEN,if (TextUtils.isEmpty(token)) "" else token)
    }

    fun getUserHead():String{
        return spUtil!!.getString(USER_HEAD,"")
    }

    fun setUserHead(head: String){
        spUtil!!.put(USER_HEAD,if (TextUtils.isEmpty(head)) "" else head)
    }

    fun getUserMobile():String{
        return spUtil!!.getString(USER_MOBILE,"")
    }

    fun setUserMobile(mobile: String){
        spUtil!!.put(USER_MOBILE,if (TextUtils.isEmpty(mobile)) "" else mobile)
    }

    fun getUserCity():String{
        return spUtil!!.getString(USER_CITY,"")
    }

    fun setUserCity(city: String){
        spUtil!!.put(USER_CITY,city)
    }

    fun getUserLocation():String{
        return spUtil!!.getString(USER_LOCATION,"")
    }

    fun setUserLocation(location: String){
        spUtil!!.put(USER_LOCATION,location)
    }

    fun getUserLatitude():String{
        return spUtil!!.getString(USER_LATITUDE,"")
    }

    fun setUserLatitude(latitude: String){
        spUtil!!.put(USER_LATITUDE,latitude)
    }

    fun getUserLongtitude():String{
        return spUtil!!.getString(USER_LONGTITUDE,"")
    }

    fun setUserLongtitude(longtitude: String){
        spUtil!!.put(USER_LONGTITUDE,longtitude)
    }

    fun clearUserInfo(){
        spUtil!!.remove(USER_NICKNAME)
        spUtil!!.remove(USER_TOKEN)
        spUtil!!.remove(USER_HEAD)
        spUtil!!.remove(USER_MOBILE)
    }
}