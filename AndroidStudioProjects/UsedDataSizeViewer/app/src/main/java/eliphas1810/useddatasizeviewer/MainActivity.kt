package eliphas1810.useddatasizeviewer

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.AppOpsManager
import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {


    private fun updateUsedDataSize() {
        try {
            val networkStatsManager = getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")

            val decimalFormat: DecimalFormat = DecimalFormat("###0.000")

            val nowCalendar: Calendar = Calendar.getInstance()

            val todayCalendar: Calendar = nowCalendar.clone() as Calendar
            todayCalendar.set(Calendar.HOUR_OF_DAY, 0)
            todayCalendar.set(Calendar.MINUTE, 0)
            todayCalendar.set(Calendar.SECOND, 0)
            todayCalendar.set(Calendar.MILLISECOND, 0)

            val oneDayBeforeCalendar = todayCalendar.clone() as Calendar
            oneDayBeforeCalendar.add(Calendar.DATE, -1)

            val twoDayBeforeCalendar = todayCalendar.clone() as Calendar
            twoDayBeforeCalendar.add(Calendar.DATE, -2)

            val threeDayBeforeCalendar = todayCalendar.clone() as Calendar
            threeDayBeforeCalendar.add(Calendar.DATE, -3)

            val fourDayBeforeCalendar = todayCalendar.clone() as Calendar
            fourDayBeforeCalendar.add(Calendar.DATE, -4)

            val fiveDayBeforeCalendar = todayCalendar.clone() as Calendar
            fiveDayBeforeCalendar.add(Calendar.DATE, -5)

            val sixDayBeforeCalendar = todayCalendar.clone() as Calendar
            sixDayBeforeCalendar.add(Calendar.DATE, -6)

            val sevenDayBeforeCalendar = todayCalendar.clone() as Calendar
            sevenDayBeforeCalendar.add(Calendar.DATE, -7)

            val eightDayBeforeCalendar = todayCalendar.clone() as Calendar
            eightDayBeforeCalendar.add(Calendar.DATE, -8)

            val nineDayBeforeCalendar = todayCalendar.clone() as Calendar
            nineDayBeforeCalendar.add(Calendar.DATE, -9)

            val tenDayBeforeCalendar = todayCalendar.clone() as Calendar
            tenDayBeforeCalendar.add(Calendar.DATE, -10)

            val todayMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, todayCalendar.time.time, nowCalendar.time.time)
            val oneDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, oneDayBeforeCalendar.time.time, todayCalendar.time.time)
            val twoDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, twoDayBeforeCalendar.time.time, oneDayBeforeCalendar.time.time)
            val threeDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, threeDayBeforeCalendar.time.time, twoDayBeforeCalendar.time.time)
            val fourDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, fourDayBeforeCalendar.time.time, threeDayBeforeCalendar.time.time)
            val fiveDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, fiveDayBeforeCalendar.time.time, fourDayBeforeCalendar.time.time)
            val sixDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, sixDayBeforeCalendar.time.time, fiveDayBeforeCalendar.time.time)
            val sevenDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, sevenDayBeforeCalendar.time.time, sixDayBeforeCalendar.time.time)
            val eightDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, eightDayBeforeCalendar.time.time, sevenDayBeforeCalendar.time.time)
            val nineDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, nineDayBeforeCalendar.time.time, eightDayBeforeCalendar.time.time)
            val tenDayBeforeMobileBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, tenDayBeforeCalendar.time.time, nineDayBeforeCalendar.time.time)

            val todayMobileBytes = todayMobileBucket.txBytes + todayMobileBucket.rxBytes
            val oneDayBeforeMobileBytes = oneDayBeforeMobileBucket.txBytes + oneDayBeforeMobileBucket.rxBytes
            val twoDayBeforeMobileBytes = twoDayBeforeMobileBucket.txBytes + twoDayBeforeMobileBucket.rxBytes
            val threeDayBeforeMobileBytes = threeDayBeforeMobileBucket.txBytes + threeDayBeforeMobileBucket.rxBytes
            val fourDayBeforeMobileBytes = fourDayBeforeMobileBucket.txBytes + fourDayBeforeMobileBucket.rxBytes
            val fiveDayBeforeMobileBytes = fiveDayBeforeMobileBucket.txBytes + fiveDayBeforeMobileBucket.rxBytes
            val sixDayBeforeMobileBytes = sixDayBeforeMobileBucket.txBytes + sixDayBeforeMobileBucket.rxBytes
            val sevenDayBeforeMobileBytes = sevenDayBeforeMobileBucket.txBytes + sevenDayBeforeMobileBucket.rxBytes
            val eightDayBeforeMobileBytes = eightDayBeforeMobileBucket.txBytes + eightDayBeforeMobileBucket.rxBytes
            val nineDayBeforeMobileBytes = nineDayBeforeMobileBucket.txBytes + nineDayBeforeMobileBucket.rxBytes
            val tenDayBeforeMobileBytes = tenDayBeforeMobileBucket.txBytes + tenDayBeforeMobileBucket.rxBytes

            findViewById<TextView>(R.id.todayMobileUsedDataSize)?.text = simpleDateFormat.format(todayCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(todayMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.oneDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(oneDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(oneDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.twoDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(twoDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(twoDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.threeDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(threeDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(threeDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.fourDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(fourDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(fourDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.fiveDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(fiveDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(fiveDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.sixDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(sixDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(sixDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.sevenDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(sevenDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(sevenDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.eightDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(eightDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(eightDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.nineDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(nineDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(nineDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.tenDayBeforeMobileUsedDataSize)?.text = simpleDateFormat.format(tenDayBeforeCalendar.time) + "    " + getString(R.string.mobile) + "    " + decimalFormat.format(tenDayBeforeMobileBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)

            val todayWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, todayCalendar.time.time, nowCalendar.time.time)
            val oneDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, oneDayBeforeCalendar.time.time, todayCalendar.time.time)
            val twoDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, twoDayBeforeCalendar.time.time, oneDayBeforeCalendar.time.time)
            val threeDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, threeDayBeforeCalendar.time.time, twoDayBeforeCalendar.time.time)
            val fourDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, fourDayBeforeCalendar.time.time, threeDayBeforeCalendar.time.time)
            val fiveDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, fiveDayBeforeCalendar.time.time, fourDayBeforeCalendar.time.time)
            val sixDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, sixDayBeforeCalendar.time.time, fiveDayBeforeCalendar.time.time)
            val sevenDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, sevenDayBeforeCalendar.time.time, sixDayBeforeCalendar.time.time)
            val eightDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, eightDayBeforeCalendar.time.time, sevenDayBeforeCalendar.time.time)
            val nineDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, nineDayBeforeCalendar.time.time, eightDayBeforeCalendar.time.time)
            val tenDayBeforeWifiBucket: NetworkStats.Bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, tenDayBeforeCalendar.time.time, nineDayBeforeCalendar.time.time)

            val todayWifiBytes = todayWifiBucket.txBytes + todayWifiBucket.rxBytes
            val oneDayBeforeWifiBytes = oneDayBeforeWifiBucket.txBytes + oneDayBeforeWifiBucket.rxBytes
            val twoDayBeforeWifiBytes = twoDayBeforeWifiBucket.txBytes + twoDayBeforeWifiBucket.rxBytes
            val threeDayBeforeWifiBytes = threeDayBeforeWifiBucket.txBytes + threeDayBeforeWifiBucket.rxBytes
            val fourDayBeforeWifiBytes = fourDayBeforeWifiBucket.txBytes + fourDayBeforeWifiBucket.rxBytes
            val fiveDayBeforeWifiBytes = fiveDayBeforeWifiBucket.txBytes + fiveDayBeforeWifiBucket.rxBytes
            val sixDayBeforeWifiBytes = sixDayBeforeWifiBucket.txBytes + sixDayBeforeWifiBucket.rxBytes
            val sevenDayBeforeWifiBytes = sevenDayBeforeWifiBucket.txBytes + sevenDayBeforeWifiBucket.rxBytes
            val eightDayBeforeWifiBytes = eightDayBeforeWifiBucket.txBytes + eightDayBeforeWifiBucket.rxBytes
            val nineDayBeforeWifiBytes = nineDayBeforeWifiBucket.txBytes + nineDayBeforeWifiBucket.rxBytes
            val tenDayBeforeWifiBytes = tenDayBeforeWifiBucket.txBytes + tenDayBeforeWifiBucket.rxBytes

            findViewById<TextView>(R.id.todayWifiUsedDataSize)?.text = simpleDateFormat.format(todayCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(todayWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.oneDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(oneDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(oneDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.twoDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(twoDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(twoDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.threeDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(threeDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(threeDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.fourDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(fourDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(fourDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.fiveDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(fiveDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(fiveDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.sixDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(sixDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(sixDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.sevenDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(sevenDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(sevenDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.eightDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(eightDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(eightDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.nineDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(nineDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(nineDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)
            findViewById<TextView>(R.id.tenDayBeforeWifiUsedDataSize)?.text = simpleDateFormat.format(tenDayBeforeCalendar.time) + "    " + getString(R.string.wifi) + "    " + decimalFormat.format(tenDayBeforeWifiBytes / 1024.0 / 1024.0 / 1024.0).padStart(8, ' ') + getString(R.string.giga_bytes)

        } catch (exception: Exception) {
            Toast.makeText(applicationContext, exception.toString(), Toast.LENGTH_LONG).show()
            throw exception
        }
    }


    //メモリー上に作成される時にのみ呼ばれます。
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val appOpsManager = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

            //使用状況へのアクセス権限の許可が無い場合
            if (appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Binder.getCallingUid(), application.packageName) != AppOpsManager.MODE_ALLOWED) {

                //使用状況へのアクセス権限の許可が無いので、当アプリを実行できない事を説明して、処理を終了
                AlertDialog.Builder(this)
                    .setMessage(getString(R.string.denied_to_access_usage_status))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    }
                    .create()
                    .show()

                    return
            }

            //更新ボタンが押された時の処理
            findViewById<Button>(R.id.update).setOnClickListener { view ->
                try {
                    updateUsedDataSize()

                } catch (exception: Exception) {
                    Toast.makeText(view.context.applicationContext, exception.toString(), Toast.LENGTH_LONG).show()
                    throw exception
                }
            }

            updateUsedDataSize()

        } catch (exception: Exception) {
            Toast.makeText(applicationContext, exception.toString(), Toast.LENGTH_LONG).show()
            throw exception
        }
    }
}
