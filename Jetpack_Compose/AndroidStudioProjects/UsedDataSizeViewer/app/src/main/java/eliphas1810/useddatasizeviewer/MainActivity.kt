package eliphas1810.useddatasizeviewer


import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import eliphas1810.useddatasizeviewer.ui.theme.UsedDataSizeViewerTheme

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.app.AppOpsManager

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import android.annotation.SuppressLint


class MainActivity : ComponentActivity() {


    @SuppressLint("BinderGetCallingInMainThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                UsedDataSizeViewerTheme {
                    BuildView()
                }
            }
        } catch (exception: Exception) {
            Toast.makeText(applicationContext, exception.toString(), Toast.LENGTH_LONG).show()
            throw exception
        }
    }
}


@Composable
fun BuildView() {
    val context = LocalContext.current

    //普通のアプリの権限確認
    //if (ContextCompat.checkSelfPermission(context, Manifest.permission.XXX_XXX) == PackageManager.PERMISSION_DENIED) {

    //使用状況へのアクセスの権限確認
    var isGranted = true
    val appOpsManager: AppOpsManager? = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
    val mode = appOpsManager?.checkOp(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.getPackageName())
    if (mode == AppOpsManager.MODE_DEFAULT) {
        if (context.checkPermission(Manifest.permission.PACKAGE_USAGE_STATS, android.os.Process.myPid(), android.os.Process.myUid()) == PackageManager.PERMISSION_DENIED) {
            isGranted = false
        }
    } else if (mode != AppOpsManager.MODE_ALLOWED) {
        isGranted = false
    }
    if (isGranted == false) {

        val notPermittedMessage = context.getString(R.string.denied_to_access_usage_status)

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.size(20.dp))
                Text(text = notPermittedMessage)
            }
        }

    } else {

        val calendar: Calendar = Calendar.getInstance()

        val nowCalendar = calendar.clone() as Calendar

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val todayCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val oneDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val twoDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val threeDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val fourDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val fiveDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val sixDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val sevenDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val eightDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val nineDayBeforeCalendar = calendar.clone() as Calendar

        calendar.add(Calendar.DATE, -1)
        val tenDayBeforeCalendar = calendar.clone() as Calendar

        val networkStatsManager: NetworkStatsManager? = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

        val todayMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, todayCalendar.time.time, nowCalendar.time.time)
        val oneDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, oneDayBeforeCalendar.time.time, todayCalendar.time.time)
        val twoDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, twoDayBeforeCalendar.time.time, oneDayBeforeCalendar.time.time)
        val threeDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, threeDayBeforeCalendar.time.time, twoDayBeforeCalendar.time.time)
        val fourDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, fourDayBeforeCalendar.time.time, threeDayBeforeCalendar.time.time)
        val fiveDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, fiveDayBeforeCalendar.time.time, fourDayBeforeCalendar.time.time)
        val sixDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, sixDayBeforeCalendar.time.time, fiveDayBeforeCalendar.time.time)
        val sevenDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, sevenDayBeforeCalendar.time.time, sixDayBeforeCalendar.time.time)
        val eightDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, eightDayBeforeCalendar.time.time, sevenDayBeforeCalendar.time.time)
        val nineDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, nineDayBeforeCalendar.time.time, eightDayBeforeCalendar.time.time)
        val tenDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, tenDayBeforeCalendar.time.time, nineDayBeforeCalendar.time.time)

        val todayMobileGB = ((todayMobileBucket?.txBytes ?: 0L) + (todayMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val oneDayBeforeMobileGB = ((oneDayBeforeMobileBucket?.txBytes ?: 0L) + (oneDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val twoDayBeforeMobileGB = ((twoDayBeforeMobileBucket?.txBytes ?: 0L) + (twoDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val threeDayBeforeMobileGB = ((threeDayBeforeMobileBucket?.txBytes ?: 0L) + (threeDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val fourDayBeforeMobileGB = ((fourDayBeforeMobileBucket?.txBytes ?: 0L) + (fourDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val fiveDayBeforeMobileGB = ((fiveDayBeforeMobileBucket?.txBytes ?: 0L) + (fiveDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val sixDayBeforeMobileGB = ((sixDayBeforeMobileBucket?.txBytes ?: 0L) + (sixDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val sevenDayBeforeMobileGB = ((sevenDayBeforeMobileBucket?.txBytes ?: 0L) + (sevenDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val eightDayBeforeMobileGB = ((eightDayBeforeMobileBucket?.txBytes ?: 0L) + (eightDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val nineDayBeforeMobileGB = ((nineDayBeforeMobileBucket?.txBytes ?: 0L) + (nineDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val tenDayBeforeMobileGB = ((tenDayBeforeMobileBucket?.txBytes ?: 0L) + (tenDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0

        val todayWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, todayCalendar.time.time, nowCalendar.time.time)
        val oneDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, oneDayBeforeCalendar.time.time, todayCalendar.time.time)
        val twoDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, twoDayBeforeCalendar.time.time, oneDayBeforeCalendar.time.time)
        val threeDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, threeDayBeforeCalendar.time.time, twoDayBeforeCalendar.time.time)
        val fourDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, fourDayBeforeCalendar.time.time, threeDayBeforeCalendar.time.time)
        val fiveDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, fiveDayBeforeCalendar.time.time, fourDayBeforeCalendar.time.time)
        val sixDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, sixDayBeforeCalendar.time.time, fiveDayBeforeCalendar.time.time)
        val sevenDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, sevenDayBeforeCalendar.time.time, sixDayBeforeCalendar.time.time)
        val eightDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, eightDayBeforeCalendar.time.time, sevenDayBeforeCalendar.time.time)
        val nineDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, nineDayBeforeCalendar.time.time, eightDayBeforeCalendar.time.time)
        val tenDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, tenDayBeforeCalendar.time.time, nineDayBeforeCalendar.time.time)

        val todayWifiGB = ((todayWifiBucket?.txBytes ?: 0L) + (todayWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val oneDayBeforeWifiGB = ((oneDayBeforeWifiBucket?.txBytes ?: 0L) + (oneDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val twoDayBeforeWifiGB = ((twoDayBeforeWifiBucket?.txBytes ?: 0L) + (twoDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val threeDayBeforeWifiGB = ((threeDayBeforeWifiBucket?.txBytes ?: 0L) + (threeDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val fourDayBeforeWifiGB = ((fourDayBeforeWifiBucket?.txBytes ?: 0L) + (fourDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val fiveDayBeforeWifiGB = ((fiveDayBeforeWifiBucket?.txBytes ?: 0L) + (fiveDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val sixDayBeforeWifiGB = ((sixDayBeforeWifiBucket?.txBytes ?: 0L) + (sixDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val sevenDayBeforeWifiGB = ((sevenDayBeforeWifiBucket?.txBytes ?: 0L) + (sevenDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val eightDayBeforeWifiGB = ((eightDayBeforeWifiBucket?.txBytes ?: 0L) + (eightDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val nineDayBeforeWifiGB = ((nineDayBeforeWifiBucket?.txBytes ?: 0L) + (nineDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
        val tenDayBeforeWifiGB = ((tenDayBeforeWifiBucket?.txBytes ?: 0L) + (tenDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0

        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")

        var today by rememberSaveable { mutableStateOf(simpleDateFormat.format(todayCalendar.time)) }
        var oneDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(oneDayBeforeCalendar.time)) }
        var twoDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(twoDayBeforeCalendar.time)) }
        var threeDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(threeDayBeforeCalendar.time)) }
        var fourDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(fourDayBeforeCalendar.time)) }
        var fiveDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(fiveDayBeforeCalendar.time)) }
        var sixDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(sixDayBeforeCalendar.time)) }
        var sevenDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(sevenDayBeforeCalendar.time)) }
        var eightDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(eightDayBeforeCalendar.time)) }
        var nineDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(nineDayBeforeCalendar.time)) }
        var tenDayBefore by rememberSaveable { mutableStateOf(simpleDateFormat.format(tenDayBeforeCalendar.time)) }

        val decimalFormat = DecimalFormat("0.000")
        decimalFormat.roundingMode = RoundingMode.UP

        var todayMobile by rememberSaveable { mutableStateOf(decimalFormat.format(todayMobileGB)) }
        var oneDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(oneDayBeforeMobileGB)) }
        var twoDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(twoDayBeforeMobileGB)) }
        var threeDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(threeDayBeforeMobileGB)) }
        var fourDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(fourDayBeforeMobileGB)) }
        var fiveDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(fiveDayBeforeMobileGB)) }
        var sixDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(sixDayBeforeMobileGB)) }
        var sevenDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(sevenDayBeforeMobileGB)) }
        var eightDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(eightDayBeforeMobileGB)) }
        var nineDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(nineDayBeforeMobileGB)) }
        var tenDayBeforeMobile by rememberSaveable { mutableStateOf(decimalFormat.format(tenDayBeforeMobileGB)) }

        var todayWifi by rememberSaveable { mutableStateOf(decimalFormat.format(todayWifiGB)) }
        var oneDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(oneDayBeforeWifiGB)) }
        var twoDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(twoDayBeforeWifiGB)) }
        var threeDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(threeDayBeforeWifiGB)) }
        var fourDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(fourDayBeforeWifiGB)) }
        var fiveDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(fiveDayBeforeWifiGB)) }
        var sixDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(sixDayBeforeWifiGB)) }
        var sevenDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(sevenDayBeforeWifiGB)) }
        var eightDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(eightDayBeforeWifiGB)) }
        var nineDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(nineDayBeforeWifiGB)) }
        var tenDayBeforeWifi by rememberSaveable { mutableStateOf(decimalFormat.format(tenDayBeforeWifiGB)) }

        val updateLabel = context.getString(R.string.update)

        val mobileLabel = context.getString(R.string.mobile)
        val wifiLabel = context.getString(R.string.wifi)

        val gigaBytesLabel = context.getString(R.string.giga_bytes)

        val license = context.getString(R.string.license)

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.size(20.dp))
                Button(
                    onClick = {
                        val calendar: Calendar = Calendar.getInstance()

                        val nowCalendar = calendar.clone() as Calendar

                        calendar.set(Calendar.HOUR_OF_DAY, 0)
                        calendar.set(Calendar.MINUTE, 0)
                        calendar.set(Calendar.SECOND, 0)
                        calendar.set(Calendar.MILLISECOND, 0)
                        val todayCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val oneDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val twoDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val threeDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val fourDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val fiveDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val sixDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val sevenDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val eightDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val nineDayBeforeCalendar = calendar.clone() as Calendar

                        calendar.add(Calendar.DATE, -1)
                        val tenDayBeforeCalendar = calendar.clone() as Calendar

                        val networkStatsManager: NetworkStatsManager? = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

                        val todayMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, todayCalendar.time.time, nowCalendar.time.time)
                        val oneDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, oneDayBeforeCalendar.time.time, todayCalendar.time.time)
                        val twoDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, twoDayBeforeCalendar.time.time, oneDayBeforeCalendar.time.time)
                        val threeDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, threeDayBeforeCalendar.time.time, twoDayBeforeCalendar.time.time)
                        val fourDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, fourDayBeforeCalendar.time.time, threeDayBeforeCalendar.time.time)
                        val fiveDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, fiveDayBeforeCalendar.time.time, fourDayBeforeCalendar.time.time)
                        val sixDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, sixDayBeforeCalendar.time.time, fiveDayBeforeCalendar.time.time)
                        val sevenDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, sevenDayBeforeCalendar.time.time, sixDayBeforeCalendar.time.time)
                        val eightDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, eightDayBeforeCalendar.time.time, sevenDayBeforeCalendar.time.time)
                        val nineDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, nineDayBeforeCalendar.time.time, eightDayBeforeCalendar.time.time)
                        val tenDayBeforeMobileBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_MOBILE, null, tenDayBeforeCalendar.time.time, nineDayBeforeCalendar.time.time)

                        val todayMobileGB = ((todayMobileBucket?.txBytes ?: 0L) + (todayMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val oneDayBeforeMobileGB = ((oneDayBeforeMobileBucket?.txBytes ?: 0L) + (oneDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val twoDayBeforeMobileGB = ((twoDayBeforeMobileBucket?.txBytes ?: 0L) + (twoDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val threeDayBeforeMobileGB = ((threeDayBeforeMobileBucket?.txBytes ?: 0L) + (threeDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val fourDayBeforeMobileGB = ((fourDayBeforeMobileBucket?.txBytes ?: 0L) + (fourDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val fiveDayBeforeMobileGB = ((fiveDayBeforeMobileBucket?.txBytes ?: 0L) + (fiveDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val sixDayBeforeMobileGB = ((sixDayBeforeMobileBucket?.txBytes ?: 0L) + (sixDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val sevenDayBeforeMobileGB = ((sevenDayBeforeMobileBucket?.txBytes ?: 0L) + (sevenDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val eightDayBeforeMobileGB = ((eightDayBeforeMobileBucket?.txBytes ?: 0L) + (eightDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val nineDayBeforeMobileGB = ((nineDayBeforeMobileBucket?.txBytes ?: 0L) + (nineDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val tenDayBeforeMobileGB = ((tenDayBeforeMobileBucket?.txBytes ?: 0L) + (tenDayBeforeMobileBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0

                        val todayWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, todayCalendar.time.time, nowCalendar.time.time)
                        val oneDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, oneDayBeforeCalendar.time.time, todayCalendar.time.time)
                        val twoDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, twoDayBeforeCalendar.time.time, oneDayBeforeCalendar.time.time)
                        val threeDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, threeDayBeforeCalendar.time.time, twoDayBeforeCalendar.time.time)
                        val fourDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, fourDayBeforeCalendar.time.time, threeDayBeforeCalendar.time.time)
                        val fiveDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, fiveDayBeforeCalendar.time.time, fourDayBeforeCalendar.time.time)
                        val sixDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, sixDayBeforeCalendar.time.time, fiveDayBeforeCalendar.time.time)
                        val sevenDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, sevenDayBeforeCalendar.time.time, sixDayBeforeCalendar.time.time)
                        val eightDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, eightDayBeforeCalendar.time.time, sevenDayBeforeCalendar.time.time)
                        val nineDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, nineDayBeforeCalendar.time.time, eightDayBeforeCalendar.time.time)
                        val tenDayBeforeWifiBucket: NetworkStats.Bucket? = networkStatsManager?.querySummaryForDevice(ConnectivityManager.TYPE_WIFI, null, tenDayBeforeCalendar.time.time, nineDayBeforeCalendar.time.time)

                        val todayWifiGB = ((todayWifiBucket?.txBytes ?: 0L) + (todayWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val oneDayBeforeWifiGB = ((oneDayBeforeWifiBucket?.txBytes ?: 0L) + (oneDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val twoDayBeforeWifiGB = ((twoDayBeforeWifiBucket?.txBytes ?: 0L) + (twoDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val threeDayBeforeWifiGB = ((threeDayBeforeWifiBucket?.txBytes ?: 0L) + (threeDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val fourDayBeforeWifiGB = ((fourDayBeforeWifiBucket?.txBytes ?: 0L) + (fourDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val fiveDayBeforeWifiGB = ((fiveDayBeforeWifiBucket?.txBytes ?: 0L) + (fiveDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val sixDayBeforeWifiGB = ((sixDayBeforeWifiBucket?.txBytes ?: 0L) + (sixDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val sevenDayBeforeWifiGB = ((sevenDayBeforeWifiBucket?.txBytes ?: 0L) + (sevenDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val eightDayBeforeWifiGB = ((eightDayBeforeWifiBucket?.txBytes ?: 0L) + (eightDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val nineDayBeforeWifiGB = ((nineDayBeforeWifiBucket?.txBytes ?: 0L) + (nineDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0
                        val tenDayBeforeWifiGB = ((tenDayBeforeWifiBucket?.txBytes ?: 0L) + (tenDayBeforeWifiBucket?.rxBytes ?: 0L)) / 1024.0 / 1024.0 / 1024.0

                        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")

                        today = simpleDateFormat.format(todayCalendar.time)
                        oneDayBefore = simpleDateFormat.format(oneDayBeforeCalendar.time)
                        twoDayBefore = simpleDateFormat.format(twoDayBeforeCalendar.time)
                        threeDayBefore = simpleDateFormat.format(threeDayBeforeCalendar.time)
                        fourDayBefore = simpleDateFormat.format(fourDayBeforeCalendar.time)
                        fiveDayBefore = simpleDateFormat.format(fiveDayBeforeCalendar.time)
                        sixDayBefore = simpleDateFormat.format(sixDayBeforeCalendar.time)
                        sevenDayBefore = simpleDateFormat.format(sevenDayBeforeCalendar.time)
                        eightDayBefore = simpleDateFormat.format(eightDayBeforeCalendar.time)
                        nineDayBefore = simpleDateFormat.format(nineDayBeforeCalendar.time)
                        tenDayBefore = simpleDateFormat.format(tenDayBeforeCalendar.time)

                        val decimalFormat = DecimalFormat("0.000")
                        decimalFormat.roundingMode = RoundingMode.UP

                        todayMobile = decimalFormat.format(todayMobileGB)
                        oneDayBeforeMobile = decimalFormat.format(oneDayBeforeMobileGB)
                        twoDayBeforeMobile = decimalFormat.format(twoDayBeforeMobileGB)
                        threeDayBeforeMobile = decimalFormat.format(threeDayBeforeMobileGB)
                        fourDayBeforeMobile = decimalFormat.format(fourDayBeforeMobileGB)
                        fiveDayBeforeMobile = decimalFormat.format(fiveDayBeforeMobileGB)
                        sixDayBeforeMobile = decimalFormat.format(sixDayBeforeMobileGB)
                        sevenDayBeforeMobile = decimalFormat.format(sevenDayBeforeMobileGB)
                        eightDayBeforeMobile = decimalFormat.format(eightDayBeforeMobileGB)
                        nineDayBeforeMobile = decimalFormat.format(nineDayBeforeMobileGB)
                        tenDayBeforeMobile = decimalFormat.format(tenDayBeforeMobileGB)

                        todayWifi = decimalFormat.format(todayWifiGB)
                        oneDayBeforeWifi = decimalFormat.format(oneDayBeforeWifiGB)
                        twoDayBeforeWifi = decimalFormat.format(twoDayBeforeWifiGB)
                        threeDayBeforeWifi = decimalFormat.format(threeDayBeforeWifiGB)
                        fourDayBeforeWifi = decimalFormat.format(fourDayBeforeWifiGB)
                        fiveDayBeforeWifi = decimalFormat.format(fiveDayBeforeWifiGB)
                        sixDayBeforeWifi = decimalFormat.format(sixDayBeforeWifiGB)
                        sevenDayBeforeWifi = decimalFormat.format(sevenDayBeforeWifiGB)
                        eightDayBeforeWifi = decimalFormat.format(eightDayBeforeWifiGB)
                        nineDayBeforeWifi = decimalFormat.format(nineDayBeforeWifiGB)
                        tenDayBeforeWifi = decimalFormat.format(tenDayBeforeWifiGB)
                    }
                ) {
                    Text(updateLabel)
                }
                Spacer(Modifier.size(10.dp))
                Row {
                    Text(text = today)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = todayMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = oneDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = oneDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = twoDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = twoDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = threeDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = threeDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = fourDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = fourDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = fiveDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = fiveDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = sixDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = sixDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = sevenDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = sevenDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = eightDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = eightDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = nineDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = nineDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = tenDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = mobileLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = tenDayBeforeMobile)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Spacer(Modifier.size(20.dp))
                Row {
                    Text(text = today)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = todayWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = oneDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = oneDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = twoDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = twoDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = threeDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = threeDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = fourDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = fourDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = fiveDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = fiveDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = sixDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = sixDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = sevenDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = sevenDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = eightDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = eightDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = nineDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = nineDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Row {
                    Text(text = tenDayBefore)
                    Spacer(Modifier.size(10.dp))
                    Text(text = wifiLabel)
                    Spacer(Modifier.size(10.dp))
                    Text(text = tenDayBeforeWifi)
                    Spacer(Modifier.size(10.dp))
                    Text(text = gigaBytesLabel)
                }
                Spacer(Modifier.size(10.dp))
                Text(text = license)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBuildView() {
    UsedDataSizeViewerTheme {
        BuildView()
    }
}
