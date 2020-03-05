package com.freak.neteasecloudmusic.modules.find.recommend.entity;

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

        private String imageUrl;
        private String targetId;
        private String adid;
        private String targetType;
        private String titleColor;
        private String typeTitle;
        private String url;
        private boolean exclusive;
        private String monitorImpress;
        private String monitorClick;
        private String monitorType;
        private String monitorImpressList;
        private String monitorClickList;
        private String monitorBlackList;
        private String extMonitor;
        private String extMonitorInfo;
        private String adSource;
        private String adLocation;
        private String adDispatchJson;
        private String encodeId;
        private String program;
        private String event;
        private String video;
        private String song;
        private String scm;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getAdid() {
            return adid;
        }

        public void setAdid(String adid) {
            this.adid = adid;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isExclusive() {
            return exclusive;
        }

        public void setExclusive(boolean exclusive) {
            this.exclusive = exclusive;
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

        public String getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(String monitorType) {
            this.monitorType = monitorType;
        }

        public String getMonitorImpressList() {
            return monitorImpressList;
        }

        public void setMonitorImpressList(String monitorImpressList) {
            this.monitorImpressList = monitorImpressList;
        }

        public String getMonitorClickList() {
            return monitorClickList;
        }

        public void setMonitorClickList(String monitorClickList) {
            this.monitorClickList = monitorClickList;
        }

        public String getMonitorBlackList() {
            return monitorBlackList;
        }

        public void setMonitorBlackList(String monitorBlackList) {
            this.monitorBlackList = monitorBlackList;
        }

        public String getExtMonitor() {
            return extMonitor;
        }

        public void setExtMonitor(String extMonitor) {
            this.extMonitor = extMonitor;
        }

        public String getExtMonitorInfo() {
            return extMonitorInfo;
        }

        public void setExtMonitorInfo(String extMonitorInfo) {
            this.extMonitorInfo = extMonitorInfo;
        }

        public String getAdSource() {
            return adSource;
        }

        public void setAdSource(String adSource) {
            this.adSource = adSource;
        }

        public String getAdLocation() {
            return adLocation;
        }

        public void setAdLocation(String adLocation) {
            this.adLocation = adLocation;
        }

        public String getAdDispatchJson() {
            return adDispatchJson;
        }

        public void setAdDispatchJson(String adDispatchJson) {
            this.adDispatchJson = adDispatchJson;
        }

        public String getEncodeId() {
            return encodeId;
        }

        public void setEncodeId(String encodeId) {
            this.encodeId = encodeId;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getSong() {
            return song;
        }

        public void setSong(String song) {
            this.song = song;
        }

        public String getScm() {
            return scm;
        }

        public void setScm(String scm) {
            this.scm = scm;
        }



        @Override
        public String toString() {
            return "BannersBean{" +
                    "imageUrl='" + imageUrl + '\'' +
                    ", targetId='" + targetId + '\'' +
                    ", adid='" + adid + '\'' +
                    ", targetType='" + targetType + '\'' +
                    ", titleColor='" + titleColor + '\'' +
                    ", typeTitle='" + typeTitle + '\'' +
                    ", url='" + url + '\'' +
                    ", exclusive=" + exclusive +
                    ", monitorImpress='" + monitorImpress + '\'' +
                    ", monitorClick='" + monitorClick + '\'' +
                    ", monitorType='" + monitorType + '\'' +
                    ", monitorImpressList='" + monitorImpressList + '\'' +
                    ", monitorClickList='" + monitorClickList + '\'' +
                    ", monitorBlackList='" + monitorBlackList + '\'' +
                    ", extMonitor='" + extMonitor + '\'' +
                    ", extMonitorInfo='" + extMonitorInfo + '\'' +
                    ", adSource='" + adSource + '\'' +
                    ", adLocation='" + adLocation + '\'' +
                    ", adDispatchJson='" + adDispatchJson + '\'' +
                    ", encodeId='" + encodeId + '\'' +
                    ", program='" + program + '\'' +
                    ", event='" + event + '\'' +
                    ", video='" + video + '\'' +
                    ", song='" + song + '\'' +
                    ", scm='" + scm + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BannerEntity{" +
                "code=" + code +
                ", banners=" + banners +
                '}';
    }
}
