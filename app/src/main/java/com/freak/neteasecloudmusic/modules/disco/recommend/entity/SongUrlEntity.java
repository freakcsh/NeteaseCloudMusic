package com.freak.neteasecloudmusic.modules.disco.recommend.entity;

import java.util.List;

public class SongUrlEntity {

    /**
     * data : [{"id":28357886,"url":"http://m10.music.126.net/20190321203747/4ddbbd2b725d97a656df4ae39c6ceeea/ymusic/a165/39a7/faa9/354b3871130499419944daee43a180c0.mp3","br":320000,"size":5677112,"md5":"354b3871130499419944daee43a180c0","code":200,"expi":1200,"type":"mp3","gain":7.8415,"fee":0,"uf":null,"payed":0,"flag":0,"canExtend":false,"freeTrialInfo":null,"level":"exhigh","encodeType":"mp3"}]
     * code : 200
     */
    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 28357886
         * url : http://m10.music.126.net/20190321203747/4ddbbd2b725d97a656df4ae39c6ceeea/ymusic/a165/39a7/faa9/354b3871130499419944daee43a180c0.mp3
         * br : 320000
         * size : 5677112
         * md5 : 354b3871130499419944daee43a180c0
         * code : 200
         * expi : 1200
         * type : mp3
         * gain : 7.8415
         * fee : 0
         * uf : null
         * payed : 0
         * flag : 0
         * canExtend : false
         * freeTrialInfo : null
         * level : exhigh
         * encodeType : mp3
         */

        private int id;
        private String url;
        private int br;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private double gain;
        private int fee;
        private Object uf;
        private int payed;
        private int flag;
        private boolean canExtend;
        private Object freeTrialInfo;
        private String level;
        private String encodeType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getBr() {
            return br;
        }

        public void setBr(int br) {
            this.br = br;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getExpi() {
            return expi;
        }

        public void setExpi(int expi) {
            this.expi = expi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getGain() {
            return gain;
        }

        public void setGain(double gain) {
            this.gain = gain;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public Object getUf() {
            return uf;
        }

        public void setUf(Object uf) {
            this.uf = uf;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isCanExtend() {
            return canExtend;
        }

        public void setCanExtend(boolean canExtend) {
            this.canExtend = canExtend;
        }

        public Object getFreeTrialInfo() {
            return freeTrialInfo;
        }

        public void setFreeTrialInfo(Object freeTrialInfo) {
            this.freeTrialInfo = freeTrialInfo;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getEncodeType() {
            return encodeType;
        }

        public void setEncodeType(String encodeType) {
            this.encodeType = encodeType;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", url='" + url + '\'' +
                    ", br=" + br +
                    ", size=" + size +
                    ", md5='" + md5 + '\'' +
                    ", code=" + code +
                    ", expi=" + expi +
                    ", type='" + type + '\'' +
                    ", gain=" + gain +
                    ", fee=" + fee +
                    ", uf=" + uf +
                    ", payed=" + payed +
                    ", flag=" + flag +
                    ", canExtend=" + canExtend +
                    ", freeTrialInfo=" + freeTrialInfo +
                    ", level='" + level + '\'' +
                    ", encodeType='" + encodeType + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SongUrlEntity{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
