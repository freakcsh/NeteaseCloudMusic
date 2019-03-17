package com.freak.neteasecloudmusic.modules.disco.recommend.entity;

import java.util.List;

public class BannerEntity {

    /**
     * code : 200
     * banners : [{"picUrl":"http://p1.music.126.net/ZDHyTOLsBRF9HgbK_qa0Dg==/109951163926454973.jpg","url":"/album?id=75761380","targetId":"75761380","backgroundUrl":"http://p1.music.126.net/3IINy-7srSWbwAnuemL86A==/109951163926461245.jpg","targetType":"10","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/xc7t7BG1aIr8e9KolB4ZVA==/109951163930577858.jpg","url":"/song?id=1348823065","targetId":"1348823065","backgroundUrl":"http://p1.music.126.net/YB59qpU70vMKlW-CwAuq6A==/109951163930575921.jpg","targetType":"1","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/x63KJnV2xGgc_fPfGIZk_g==/109951163930580999.jpg","url":"https://music.163.com/m/at/5c8777ed82bad068c137df4f","targetId":"0","backgroundUrl":"http://p1.music.126.net/OLBRjOSPcjyDB3YK-L5OnQ==/109951163930571322.jpg","targetType":"3000","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/kQmXAcz1X-R6kbSD22dUXA==/109951163930584286.jpg","url":"/album?id=75832450","targetId":"75832450","backgroundUrl":"http://p1.music.126.net/tiP0S4_lmjgm1LO3fNl1fQ==/109951163930580494.jpg","targetType":"10","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/U7-xN1gx5pHCz2waBwnt7w==/109951163931384920.jpg","url":"https://music.163.com/m/at/5c8cf3ec38066ae3d6916027","targetId":"0","backgroundUrl":"http://p1.music.126.net/wYVw3FtqcGDyMOtgeoMIXA==/109951163931395517.jpg","targetType":"3000","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/Ti155ZmxeJQBmeobZ18_6Q==/109951163930599925.jpg","url":"/song?id=1351664505","targetId":"1351664505","backgroundUrl":"http://p1.music.126.net/zRk-B2LNq0BPOTbX2zOB_Q==/109951163930610616.jpg","targetType":"1","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/L4hjN12lXw1MvjxH-vZU1g==/109951163930606888.jpg","url":"/song?id=1351985329","targetId":"1351985329","backgroundUrl":"http://p1.music.126.net/SREm6lWfdUI2RvxpoDpAlQ==/109951163930610267.jpg","targetType":"1","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/u-MQ98BbT63pxDM9ve2oFw==/109951163930616174.jpg","url":"/mv?id=10857532","targetId":"10857532","backgroundUrl":"http://p1.music.126.net/bJvl2Ts5-J0vQmtgBxBJrA==/109951163930621538.jpg","targetType":"1004","monitorType":"","monitorImpress":"","monitorClick":""},{"picUrl":"http://p1.music.126.net/tpdQnvNAGfTE7oVNB2GEQQ==/109951163930615822.jpg","url":"/album?id=75781937","targetId":"75781937","backgroundUrl":"http://p1.music.126.net/RnNu75UdLkESjWC_paXuXQ==/109951163930624557.jpg","targetType":"10","monitorType":"","monitorImpress":"","monitorClick":""}]
     */

    private int code;
    private List<BannersBean> banners;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean {
        /**
         * picUrl : http://p1.music.126.net/ZDHyTOLsBRF9HgbK_qa0Dg==/109951163926454973.jpg
         * url : /album?id=75761380
         * targetId : 75761380
         * backgroundUrl : http://p1.music.126.net/3IINy-7srSWbwAnuemL86A==/109951163926461245.jpg
         * targetType : 10
         * monitorType :
         * monitorImpress :
         * monitorClick :
         */

        private String picUrl;
        private String url;
        private String targetId;
        private String backgroundUrl;
        private String targetType;
        private String monitorType;
        private String monitorImpress;
        private String monitorClick;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(String monitorType) {
            this.monitorType = monitorType;
        }

        public String getMonitorImpress() {
            return monitorImpress;
        }

        public void setMonitorImpress(String monitorImpress) {
            this.monitorImpress = monitorImpress;
        }

        public String getMonitorClick() {
            return monitorClick;
        }

        public void setMonitorClick(String monitorClick) {
            this.monitorClick = monitorClick;
        }
    }
}
