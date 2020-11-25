package com.example.socketexam

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Message
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.logging.Handler

class ServerThread: Thread() {
    private lateinit var mContext: Context
    private lateinit var mMainHandler: Handler

    ServerThread(context: Context,mainHandler: Handler) {
        mContext = context
        mMainHandler = mainHandler
    }

    override fun run() {
        var servSock: ServerSocket? = null
        try {
            servSock = ServerSocket(9000)
            var wifiMgr = mContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            var wifiInfo = wifiMgr.connectionInfo
            var serverip = 1
            if(wifiMgr.isWifiEnabled) {
                serverip = wifiInfo.ipAddress
            }
            else {
                serverip = 0x0100007F
            }
            doPrintln(">> 서버 시작! " + ipv4ToString(serverip) + "/" + servSock.localPort)

            while (true) {
                var sock:Socket = servSock.accept()
                var ip = sock.inetAddress.hostAddress
                var port = sock.port
                doPrintln(">> 클라이언트 접속 : " +  ip + "/" + port)

                var ins : InputStream = sock.getInputStream()
                var outs : OutputStream = sock.getOutputStream()

                var buf = byteArrayOf()

                while (true) {
                    try {
                        var nBytes = ins.read(buf)
                        if(nBytes>0) {
                            var s:String = String(buf, 0, nBytes)
                            doPrintln("[" + ip + "/" + port + "]" + s)
                            outs.write(buf, 0, nBytes)
                        }
                        else {
                            doPrintln(">> 클라이언트 종료 : " +  ip + "/" + port)
                            break
                        }
                    }
                    catch (e:IOException) {
                        doPrintln(">> 클라이언트 종료 : " +  ip + "/" + port)
                        break
                    }
                }
                sock.close()
            }

        }
        catch (e: IOException) {
            doPrintln(e.message)
        }
        finally {
            try {
                if(servSock != null) {
                    servSock.close()
                }
                doPrintln(">> 서버종료")
            } catch (e:IOException) {doPrintln(e.message)}
        }
    } // end of run

    fun doPrintln(str:String) {
        var msg:Message = Message.obtain()
        msg.what = 1
        msg.obj = str + "\n"
        mMainHandler.sendMessage(msg)
    }
}