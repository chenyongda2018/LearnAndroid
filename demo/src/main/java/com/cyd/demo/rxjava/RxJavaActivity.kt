package com.cyd.demo.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cyd.demo.databinding.ActivityRxJavaBinding
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.math.max

class RxJavaActivity : AppCompatActivity() {

    val TAG = "RxJavaActivity->"

    var mDisposable: Disposable? = null

    private var _binding: ActivityRxJavaBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRxJavaBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        test1()
//        test2()
//        test3()
        testRxViewBuffer()
        testRxViewDebounce()
    }

    var maxClickCnt = 0
    /**
     * 每3秒，显示点击btn的次数
     */
    private fun testRxViewBuffer() {
        RxView.clicks(binding.bufferBtn)
            .map { 1 }
            .buffer(3,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableObserver<List<Int>>() {
                override fun onNext(value: List<Int>?) {
                    value?.let {
                        if(it.isEmpty()) return@let
                        Log.d(TAG,"onNext,bufferBtn")
                        maxClickCnt = max(it.size,maxClickCnt)

                        binding.bufferText.text = String.format("Received %d taps in 3 secs\n" +
                                "Maximum of %d taps received in this session", it.size,maxClickCnt)
                    }
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }

            })
    }

    private fun testRxViewDebounce() {
        RxTextView.textChanges(binding.searchEt)
            .debounce(500,TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .filter { it.toString().trim().isNotEmpty() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<CharSequence> {
                override fun onSubscribe(d: Disposable?) {
                }
                override fun onNext(value: CharSequence?) {
                    Log.d(TAG,"debounce,$value")
                    binding.searchResTv.text = value
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }

            })
    }

    /**
     * Just
     */
    private fun test1() {
        val observable = Observable.just(
            "Ant", "Ape",
            "Bat", "Bee", "Bear", "Butterfly",
            "Cat", "Crab", "Cod"
        )
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
                mDisposable = d
            }

            override fun onNext(t: String) {
                Log.d(TAG, "Name: $t");
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "All items are emitted!")
            }
        }

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { str ->
                str.lowercase().startsWith("b")
            }
            .subscribe(observer)
    }

    /**
     * 操作符
     * 1.用于创建Observable:create, just, fromArray, range
     * 2.过滤Observable发出的数据:debounce, filter, skip, last
     * 3.创建新的Observable来转换原始Observable发出的数据:
     *  buffer, map, flatMap, switchMap, compose
     */
    private fun test2() {
        Observable.range(1, 20)
//        Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
//            11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it % 2 == 0 }
            .map { "$it is even num" }
            .subscribe(object : DisposableObserver<String>() {
                override fun onNext(t: String) {
                    Log.d(TAG, "Number: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                    Log.d(TAG, "Number: onComplete")
                }
            })
    }

    /**
     * Buffer
     * Buffer gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time.
     * 将数据集合，n个一组发送
     */
    private fun test3() {
        Observable.just(1, 2, 3, 4,
            5, 6, 7, 8, 9)
            .buffer(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<List<Int>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<Int>) {
                    Log.d(TAG,"onNext,${t.size}")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }
}