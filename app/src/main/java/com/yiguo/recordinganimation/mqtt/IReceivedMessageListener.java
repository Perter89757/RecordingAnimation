package com.yiguo.recordinganimation.mqtt;

public interface IReceivedMessageListener {

    void onMessageReceived(ReceivedMessage message);
}