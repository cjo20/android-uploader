package com.nightscout.android.dexcom.records;

import android.util.Log;

import com.nightscout.android.TimeConstants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CalRecord extends GenericTimestampRecord {
    private static final String TAG = CalRecord.class.getSimpleName();
    private double slope;
    private double intercept;
    private double scale;
    private int[] unk = new int[3];
    private double decay;
    private int  numRecords;
    private CalSubrecord[] calSubrecords = new CalSubrecord[12];
    private int SUB_LEN = 17;

    public CalRecord(byte[] packet) {
        super(packet);
        slope = ByteBuffer.wrap(packet).order(ByteOrder.LITTLE_ENDIAN).getDouble(8);
        intercept = ByteBuffer.wrap(packet).order(ByteOrder.LITTLE_ENDIAN).getDouble(16);
        scale = ByteBuffer.wrap(packet).order(ByteOrder.LITTLE_ENDIAN).getDouble(24);
        unk[0] = packet[32];
        unk[1] = packet[33];
        unk[2] = packet[34];
        decay = ByteBuffer.wrap(packet).order(ByteOrder.LITTLE_ENDIAN).getDouble(35);
        numRecords = packet[43];
        Log.d("CalDebug", "Slope: "+slope);
        Log.d("CalDebug", "Intercept: "+intercept);
        Log.d("CalDebug", "Scale: "+scale);
        Log.d("CalDebug", "Decay: "+decay);
        Log.d("CalDebug", "Number of records: "+numRecords);
        long displayTimeOffset = (getDisplayTime().getTime() - getSystemTime().getTime()) / TimeConstants.SEC_TO_MS;
        int start = 44;
        for (int i = 0; i < numRecords; i++) {
            Log.d("CalDebug","Loop #"+i);
            byte[] temp = new byte[SUB_LEN];
            System.arraycopy(packet, start, temp, 0, temp.length);
            calSubrecords[i] = new CalSubrecord(temp, displayTimeOffset);
            start += SUB_LEN;
        }
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getScale() {
        return scale;
    }

    public int[] getUnk() {
        return unk;
    }

    public double getDecay() {
        return decay;
    }

    public int getNumRecords() {
        return numRecords;
    }

    public CalSubrecord[] getCalSubrecords() {
        return calSubrecords;
    }
}
