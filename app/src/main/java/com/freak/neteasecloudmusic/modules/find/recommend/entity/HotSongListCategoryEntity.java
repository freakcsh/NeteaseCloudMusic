package com.freak.neteasecloudmusic.modules.find.recommend.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/3/18
 */

public class HotSongListCategoryEntity implements Serializable {


    private String msg;
    private int code;
    private List<TagsBean> tags;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class TagsBean implements Serializable {
        /**
         * playlistTag : {"id":5001,"name":"华语","category":0,"usedCount":4716503,"type":0,"position":1,"createTime":1378707544870,"highQuality":1,"highQualityPos":1,"officialPos":1}
         * activity : false
         * category : 0
         * position : 1
         * usedCount : 4716503
         * createTime : 1378707544870
         * hot : true
         * name : 华语
         * id : 5001
         * type : 1
         */

        private PlaylistTagBean playlistTag;
        private boolean activity;
        private String category;
        private String position;
        private String usedCount;
        private String createTime;
        private boolean hot;
        private String name;
        private String id;
        private String type;

        public PlaylistTagBean getPlaylistTag() {
            return playlistTag;
        }

        public void setPlaylistTag(PlaylistTagBean playlistTag) {
            this.playlistTag = playlistTag;
        }

        public boolean isActivity() {
            return activity;
        }

        public void setActivity(boolean activity) {
            this.activity = activity;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(String usedCount) {
            this.usedCount = usedCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class PlaylistTagBean {
            /**
             * id : 5001
             * name : 华语
             * category : 0
             * usedCount : 4716503
             * type : 0
             * position : 1
             * createTime : 1378707544870
             * highQuality : 1
             * highQualityPos : 1
             * officialPos : 1
             */

            private String id;
            private String name;
            private String category;
            private String usedCount;
            private String type;
            private String position;
            private String createTime;
            private String highQuality;
            private String highQualityPos;
            private String officialPos;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getUsedCount() {
                return usedCount;
            }

            public void setUsedCount(String usedCount) {
                this.usedCount = usedCount;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getHighQuality() {
                return highQuality;
            }

            public void setHighQuality(String highQuality) {
                this.highQuality = highQuality;
            }

            public String getHighQualityPos() {
                return highQualityPos;
            }

            public void setHighQualityPos(String highQualityPos) {
                this.highQualityPos = highQualityPos;
            }

            public String getOfficialPos() {
                return officialPos;
            }

            public void setOfficialPos(String officialPos) {
                this.officialPos = officialPos;
            }
        }

        @Override
        public String toString() {
            return "TagsBean{" +
                    "playlistTag=" + playlistTag +
                    ", activity=" + activity +
                    ", category=" + category +
                    ", position=" + position +
                    ", usedCount=" + usedCount +
                    ", createTime=" + createTime +
                    ", hot=" + hot +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    ", type=" + type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HotSongListCategoryEntity{" +
                "code=" + code +
                ", tags=" + tags +
                '}';
    }
}
