package com.thanatos.network;

/**
 * 接口地址
 */
public class APIAddressConstants {
    static String BASE_CLOUD = getBaseUrl();

    private static class AutoMiniAPIAddress {

        /**
         * 正式地址
         */
        static final String OFFICIAL_IP = "";
        /**
         * 测试地址
         */
        static final String TEST_IP = "";
        /**
         * 开发地址
         */
        static final String DEVELOP_IP = "";
    }

    /**
     * 获取基础地址，此地址是最主要的接口地址
     *
     * @return 不同环境的接口地址
     */
    private static String getBaseUrl() {
        int requestAddress = 1;

        if (requestAddress == 1) {
            //生产
            return AutoMiniAPIAddress.OFFICIAL_IP;
        } else if (requestAddress == 2) {
            //测试
            return AutoMiniAPIAddress.TEST_IP;
        } else if (requestAddress == 3) {
            //开发
            return AutoMiniAPIAddress.DEVELOP_IP;
        } else {
            return AutoMiniAPIAddress.OFFICIAL_IP;
        }
    }

    public interface Order {

    }
}
