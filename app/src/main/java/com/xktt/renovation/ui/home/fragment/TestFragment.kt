package com.xktt.renovation.ui.home.fragment

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xktt.renovation.baselibs.base.BaseMvpFragment
import com.xktt.renovation.R
import com.xktt.renovation.baselibs.utils.ToastUtils
import com.xktt.renovation.baselibs.widget.LoadingDialog
import com.xktt.renovation.mvp.contract.TestContract
import com.xktt.renovation.mvp.presenter.TestPresenter
import com.xktt.renovation.ui.home.adapter.MainAdapter
import com.xktt.renovation.ui.home.bean.MainBean
import org.jetbrains.anko.support.v4.find

/**
 * @author chenxz
 * @date 2018/11/30
 * @desc
 */
class TestFragment : BaseMvpFragment<TestContract.View, TestContract.Presenter>(), TestContract.View {
    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMDAxMDYzMiIsInN1YiI6IjEwMDEwNjMyIiwicHdkIjoiMDRkN2EwNzJjMWUzNTczMTFjMTA5OGNiNTg0N2ViNmUiLCJleHAiOjE2MzA4MjUzOTYsImlhdCI6MTYyOTM1NDE2NywianRpIjoiZTI2NWM1MzQtYWY1YS00ZjhhLWFmMmYtZmE3MzhlNjg4ZDdiIn0.bv4swd32qjMpoPB_FJgNfTzAf8T8tX_FmTi_2Yk7jJE"
    private val page = 1
    private val pageSize = 10

    private val mRefresh by lazy { find<SwipeRefreshLayout>(R.id.mRefresh) }
    private val mRecycler by lazy { find<RecyclerView>(R.id.mRecycler) }
//    lateinit var adapter: MainMultiItemAdapter
    lateinit var adapter: MainAdapter
    var mData = ArrayList<MainBean>()
    lateinit var loadingDialog : LoadingDialog

    override fun createPresenter(): TestContract.Presenter = TestPresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_test

    override fun lazyLoad() {
        loadData()
        mRecycler.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(R.layout.item_main_list)
        adapter.data = mData
        mRecycler.adapter = adapter
        mRefresh.setOnRefreshListener {
            loadData()
            adapter.notifyDataSetChanged()
            mRefresh.isRefreshing = false
        }
        adapter.addChildClickViewIds(R.id.tv_name,R.id.tv_age)
        adapter.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showToast("?????????$position")
        }

        adapter.setOnItemChildClickListener { adapter, view, position ->
            var bean = adapter.getItem(position) as MainBean
            if (view.id == R.id.tv_name){
                ToastUtils.showToast("?????????" + bean.name)
            } else if (view.id == R.id.tv_age){
                ToastUtils.showToast("?????????" + bean.age)
            }
        }
    }

    private fun loadData() {
        //????????????
        arguments?.getString("position")?.let { Log.d("fragment", it) }

        context?.let {
          loadingDialog = LoadingDialog.showDialog(it,"?????????...",false)!!
        }
        var b1 = MainBean(1)
        b1.name = "?????????"
        b1.age = 23
        mData.add(b1)

        var b2 = MainBean(1)
        b2.name = "?????????"
        b2.age = 25
        mData.add(b2)

        var b3 = MainBean(1)
        b3.name = "?????????"
        b3.age = 30
        mData.add(b3)
        mPresenter?.getRecords(token,page,pageSize)
    }

    override fun getRecordsSuccess(any: Any) {
        ToastUtils.showToast("?????????")
        loadingDialog.dismiss()
    }

}