package cn.edu.scnu.util;

public class ExpireTime {
    /**
     *
     * @return expireMillis
     */
    public static long calculate(int maxLiveTimeS, long createTimeMillis) {
        long maxLiveTimeMillis = maxLiveTimeS * 1000L;

        long nowTimeMillis = System.currentTimeMillis();
        long intervalTimeMillis = nowTimeMillis - createTimeMillis;
        if (intervalTimeMillis < maxLiveTimeMillis) {
            return maxLiveTimeMillis - intervalTimeMillis;
        } else {
            return 0;
        }
    }
}
