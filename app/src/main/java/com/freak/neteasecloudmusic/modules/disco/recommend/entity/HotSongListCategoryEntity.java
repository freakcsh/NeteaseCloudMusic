package com.freak.neteasecloudmusic.modules.disco.recommend.entity;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2019/3/18
 */

public class HotSongListCategoryEntity {

    /**
     * tags : [{"playlistTag":{"id":5001,"name":"华语","category":0,"usedCount":4716503,"type":0,"position":1,"createTime":1378707544870,"highQuality":1,"highQualityPos":1,"officialPos":1},"activity":false,"category":0,"position":1,"usedCount":4716503,"createTime":1378707544870,"hot":true,"name":"华语","id":5001,"type":1},{"playlistTag":{"id":1,"name":"流行","category":1,"usedCount":4157846,"type":0,"position":2,"createTime":1378707567870,"highQuality":1,"highQualityPos":10,"officialPos":1},"activity":false,"category":1,"position":2,"usedCount":4157846,"createTime":1378707567870,"hot":true,"name":"流行","id":1,"type":1},{"playlistTag":{"id":2,"name":"摇滚","category":1,"usedCount":2111242,"type":0,"position":3,"createTime":1378707568870,"highQuality":1,"highQualityPos":11,"officialPos":2},"activity":false,"category":1,"position":3,"usedCount":2111242,"createTime":1378707568870,"hot":true,"name":"摇滚","id":2,"type":1},{"playlistTag":{"id":1001,"name":"民谣","category":1,"usedCount":2240667,"type":0,"position":4,"createTime":1378707569870,"highQuality":1,"highQualityPos":14,"officialPos":3},"activity":false,"category":1,"position":4,"usedCount":2240667,"createTime":1378707569870,"hot":true,"name":"民谣","id":1001,"type":1},{"playlistTag":{"id":2004,"name":"电子","category":1,"usedCount":2841885,"type":0,"position":5,"createTime":1378707570870,"highQuality":1,"highQualityPos":16,"officialPos":4},"activity":false,"category":1,"position":5,"usedCount":2841885,"createTime":1378707570870,"hot":true,"name":"电子","id":2004,"type":1},{"playlistTag":{"id":2008,"name":"轻音乐","category":1,"usedCount":2397597,"type":0,"position":6,"createTime":1378707572870,"highQuality":1,"highQualityPos":15,"officialPos":7},"activity":false,"category":1,"position":6,"usedCount":2397597,"createTime":1378707572870,"hot":true,"name":"轻音乐","id":2008,"type":1},{"playlistTag":{"id":9001,"name":"影视原声","category":4,"usedCount":1785615,"type":0,"position":7,"createTime":1378707598870,"highQuality":1,"highQualityPos":9,"officialPos":1},"activity":false,"category":4,"position":7,"usedCount":1785615,"createTime":1378707598870,"hot":true,"name":"影视原声","id":9001,"type":1},{"playlistTag":{"id":11002,"name":"ACG","category":4,"usedCount":1903339,"type":0,"position":8,"createTime":1387779676260,"highQuality":1,"highQualityPos":8,"officialPos":2},"activity":false,"category":4,"position":8,"usedCount":1903339,"createTime":1387779676260,"hot":true,"name":"ACG","id":11002,"type":1},{"playlistTag":{"id":1031,"name":"怀旧","category":3,"usedCount":2116927,"type":0,"position":9,"createTime":1378707582870,"highQuality":0,"highQualityPos":0,"officialPos":1},"activity":false,"category":3,"position":9,"usedCount":2116927,"createTime":1378707582870,"hot":true,"name":"怀旧","id":1031,"type":1},{"playlistTag":{"id":1032,"name":"治愈","category":3,"usedCount":2471407,"type":0,"position":10,"createTime":1378707587870,"highQuality":0,"highQualityPos":0,"officialPos":6},"activity":false,"category":3,"position":10,"usedCount":2471407,"createTime":1378707587870,"hot":true,"name":"治愈","id":1032,"type":1}]
     * code : 200
     */

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

    public static class TagsBean {
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
        private int category;
        private int position;
        private int usedCount;
        private long createTime;
        private boolean hot;
        private String name;
        private int id;
        private int type;

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

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(int usedCount) {
            this.usedCount = usedCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
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

            private int id;
            private String name;
            private int category;
            private int usedCount;
            private int type;
            private int position;
            private long createTime;
            private int highQuality;
            private int highQualityPos;
            private int officialPos;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public int getUsedCount() {
                return usedCount;
            }

            public void setUsedCount(int usedCount) {
                this.usedCount = usedCount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getHighQuality() {
                return highQuality;
            }

            public void setHighQuality(int highQuality) {
                this.highQuality = highQuality;
            }

            public int getHighQualityPos() {
                return highQualityPos;
            }

            public void setHighQualityPos(int highQualityPos) {
                this.highQualityPos = highQualityPos;
            }

            public int getOfficialPos() {
                return officialPos;
            }

            public void setOfficialPos(int officialPos) {
                this.officialPos = officialPos;
            }
        }
    }
}
