package com.example.pertemuan3.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import com.example.pertemuan3.MessageListener

class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val data = intent?.extras
        val pdus = data!!["pdus"] as Array<Any>?
        if (pdus != null){
            for(i in pdus){
                val smsMessage: SmsMessage = SmsMessage.createFromPdu(i as ByteArray)
                val message = "Sender : " + smsMessage.displayOriginatingAddress
                    .toString() + "Email From: " + smsMessage.emailFrom
                    .toString() + "Email Body: " + smsMessage.emailBody
                    .toString() + "Display message body: " + smsMessage.displayMessageBody
                    .toString() + "Time in millisecond: " + smsMessage.timestampMillis
                    .toString() + "Message " + smsMessage.messageBody
                nlistener!!.messageReceived(message)
            }
        }
    }

    companion object {
        private var nlistener: MessageListener? = null
        fun bindListener(listener: MessageListener?){
            nlistener = listener
        }
    }
}