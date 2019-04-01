package m.vk.k010_handlertimer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mHandler : Handler
    companion object {
        val countMax : Int = 20
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTimer()
        tvStartTimer.setOnClickListener {
            onStartTime()
        }
        tvStopTimer.setOnClickListener {
            onStopTime()
        }
    }

    private fun initTimer() {
        mHandler = object : Handler(){
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                tvTimer.text = String.format("%02d",msg!!.arg1)

                if (msg!!.arg1 > 0){
                    var msg2 = Message()
                    msg2.arg1 =  msg.arg1- 1
                    mHandler.sendMessageDelayed(msg2,1000)
                }else{
                    Toast.makeText(this@MainActivity,"นับเสร็จสิ้น",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun onStartTime(){
        var msg = Message()
        msg.arg1 = countMax
        mHandler.sendMessage(msg)
    }
    private fun onStopTime(){
        tvTimer.text = countMax.toString()
        mHandler.removeCallbacksAndMessages(null)
    }
}
