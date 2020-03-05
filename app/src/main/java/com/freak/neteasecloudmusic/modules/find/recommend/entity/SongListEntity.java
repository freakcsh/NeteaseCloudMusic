package com.freak.neteasecloudmusic.modules.find.recommend.entity;

import java.util.List;

/**
 * @author Administrator
 */
public class SongListEntity {

    private String msg;
    private int total;
    private int code;
    private boolean more;
    private String cat;
    private List<PlaylistsBean> playlists;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<PlaylistsBean> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistsBean> playlists) {
        this.playlists = playlists;
    }


    public static class PlaylistsBean extends MultipleItem {
        /**
         * name : 「关于爱情」收集心动的每一个瞬间
         * id : 2708920272
         * trackNumberUpdateTime : 1552647518260
         * status : 0
         * userId : 201586
         * createTime : 1552554659226
         * updateTime : 1552647529944
         * subscribedCount : 1443
         * trackCount : 38
         * cloudTrackCount : 0
         * coverImgUrl : http://p1.music.126.net/795F-yVcTRFdvnE5j5GlWg==/109951163925125294.jpg
         * coverImgId : 109951163925125300
         * description : 爱你这个小秘密，一不小心就被你看穿。
         * tags : ["华语","流行","浪漫"]
         * playCount : 273196
         * trackUpdateTime : 1552878386723
         * specialType : 0
         * totalDuration : 0
         * creator : {"defaultAvatar":false,"province":440000,"authStatus":1,"followed":false,"avatarUrl":"http://p1.music.126.net/1Fk20Hohlarq8gw77En9nQ==/109951163879489294.jpg","accountStatus":0,"gender":2,"city":440100,"birthday":1347033600000,"userId":201586,"userType":4,"nickname":"原创君","signature":"网易云音乐是6亿人都在使用的音乐平台，致力于帮助音乐爱好者发现音乐惊喜，帮助音乐人实现梦想。\n客服在线时间：9:00 - 22:00，如您在使用过程中遇到任何问题，欢迎私信咨询，我们会尽快回复。\n如果仍然不能解决您的问题，请邮件我们：\n用户：ncm5990@163.com\n音乐人：yyr599@163.com","description":"","detailDescription":"","avatarImgId":109951163879489300,"backgroundImgId":109951163521987360,"backgroundUrl":"http://p1.music.126.net/6WOd8M2uPlfuu9TCiBCSMw==/109951163521987363.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":11,"remarkName":null,"backgroundImgIdStr":"109951163521987363","avatarImgIdStr":"109951163879489294","avatarImgId_str":"109951163879489294"}
         * tracks : null
         * subscribers : [{"defaultAvatar":false,"province":650000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/DLnxUXqZXi6n2eOn2s3ifg==/109951163638946746.jpg","accountStatus":0,"gender":1,"city":650100,"birthday":-2209017600000,"userId":1659134222,"userType":0,"nickname":"老猫VA","signature":"","description":"","detailDescription":"","avatarImgId":109951163638946750,"backgroundImgId":109951162868126480,"backgroundUrl":"http://p1.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":0,"vipType":0,"remarkName":null,"backgroundImgIdStr":"109951162868126486","avatarImgIdStr":"109951163638946746","avatarImgId_str":"109951163638946746"}]
         * subscribed : false
         * commentThreadId : A_PL_0_2708920272
         * newImported : false
         * adType : 0
         * highQuality : false
         * privacy : 0
         * ordered : true
         * anonimous : false
         * shareCount : 2
         * coverImgId_str : 109951163925125294
         * commentCount : 20
         * alg : alg_sq_featured
         */

        private String name;
        private String id;
        private String trackNumberUpdateTime;
        private String status;
        private String userId;
        private String createTime;
        private String updateTime;
        private String subscribedCount;
        private String trackCount;
        private String cloudTrackCount;
        private String coverImgUrl;
        private String coverImgId;
        private String description;
        private String playCount;
        private String trackUpdateTime;
        private String specialType;
        private String totalDuration;
        private CreatorBean creator;
        private Object tracks;
        private boolean subscribed;
        private String commentThreadId;
        private boolean newImported;
        private String adType;
        private boolean highQuality;
        private String privacy;
        private boolean ordered;
        private boolean anonimous;
        private String shareCount;
        private String coverImgId_str;
        private String commentCount;
        private String alg;
        private List<String> tags;
        private List<SubscribersBean> subscribers;

        public PlaylistsBean(int itemType, int spanSize, Object multiItemEntity) {
            super(itemType, spanSize, multiItemEntity);
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

        public String getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(String trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(String subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public String getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(String trackCount) {
            this.trackCount = trackCount;
        }

        public String getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(String cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(String coverImgId) {
            this.coverImgId = coverImgId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPlayCount() {
            return playCount;
        }

        public void setPlayCount(String playCount) {
            this.playCount = playCount;
        }

        public String getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(String trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public String getSpecialType() {
            return specialType;
        }

        public void setSpecialType(String specialType) {
            this.specialType = specialType;
        }

        public String getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(String totalDuration) {
            this.totalDuration = totalDuration;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public Object getTracks() {
            return tracks;
        }

        public void setTracks(Object tracks) {
            this.tracks = tracks;
        }

        public boolean isSubscribed() {
            return subscribed;
        }

        public void setSubscribed(boolean subscribed) {
            this.subscribed = subscribed;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public boolean isNewImported() {
            return newImported;
        }

        public void setNewImported(boolean newImported) {
            this.newImported = newImported;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public boolean isHighQuality() {
            return highQuality;
        }

        public void setHighQuality(boolean highQuality) {
            this.highQuality = highQuality;
        }

        public String getPrivacy() {
            return privacy;
        }

        public void setPrivacy(String privacy) {
            this.privacy = privacy;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public boolean isAnonimous() {
            return anonimous;
        }

        public void setAnonimous(boolean anonimous) {
            this.anonimous = anonimous;
        }

        public String getShareCount() {
            return shareCount;
        }

        public void setShareCount(String shareCount) {
            this.shareCount = shareCount;
        }

        public String getCoverImgId_str() {
            return coverImgId_str;
        }

        public void setCoverImgId_str(String coverImgId_str) {
            this.coverImgId_str = coverImgId_str;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<SubscribersBean> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<SubscribersBean> subscribers) {
            this.subscribers = subscribers;
        }

        @Override
        public int getItemType() {
            return MultipleItem.RECOMMEND_SONG_LIST;
        }


        public static class CreatorBean {
            /**
             * defaultAvatar : false
             * province : 440000
             * authStatus : 1
             * followed : false
             * avatarUrl : http://p1.music.126.net/1Fk20Hohlarq8gw77En9nQ==/109951163879489294.jpg
             * accountStatus : 0
             * gender : 2
             * city : 440100
             * birthday : 1347033600000
             * userId : 201586
             * userType : 4
             * nickname : 原创君
             * signature : 网易云音乐是6亿人都在使用的音乐平台，致力于帮助音乐爱好者发现音乐惊喜，帮助音乐人实现梦想。
             * 客服在线时间：9:00 - 22:00，如您在使用过程中遇到任何问题，欢迎私信咨询，我们会尽快回复。
             * 如果仍然不能解决您的问题，请邮件我们：
             * 用户：ncm5990@163.com
             * 音乐人：yyr599@163.com
             * description :
             * detailDescription :
             * avatarImgId : 109951163879489300
             * backgroundImgId : 109951163521987360
             * backgroundUrl : http://p1.music.126.net/6WOd8M2uPlfuu9TCiBCSMw==/109951163521987363.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 10
             * vipType : 11
             * remarkName : null
             * backgroundImgIdStr : 109951163521987363
             * avatarImgIdStr : 109951163879489294
             * avatarImgId_str : 109951163879489294
             */

            private boolean defaultAvatar;
            private String province;
            private String authStatus;
            private boolean followed;
            private String avatarUrl;
            private String accountStatus;
            private String gender;
            private String city;
            private String birthday;
            private String userId;
            private String userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private String avatarImgId;
            private String backgroundImgId;
            private String backgroundUrl;
            private String authority;
            private boolean mutual;
            private Object expertTags;
            private Object experts;
            private String djStatus;
            private String vipType;
            private Object remarkName;
            private String backgroundImgIdStr;
            private String avatarImgIdStr;
            private String avatarImgId_str;

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(String authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(String accountStatus) {
                this.accountStatus = accountStatus;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public String getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(String avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public String getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(String backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public Object getExperts() {
                return experts;
            }

            public void setExperts(Object experts) {
                this.experts = experts;
            }

            public String getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(String djStatus) {
                this.djStatus = djStatus;
            }

            public String getVipType() {
                return vipType;
            }

            public void setVipType(String vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }

            @Override
            public String toString() {
                return "CreatorBean{" +
                        "defaultAvatar=" + defaultAvatar +
                        ", province=" + province +
                        ", authStatus=" + authStatus +
                        ", followed=" + followed +
                        ", avatarUrl='" + avatarUrl + '\'' +
                        ", accountStatus=" + accountStatus +
                        ", gender=" + gender +
                        ", city=" + city +
                        ", birthday=" + birthday +
                        ", userId=" + userId +
                        ", userType=" + userType +
                        ", nickname='" + nickname + '\'' +
                        ", signature='" + signature + '\'' +
                        ", description='" + description + '\'' +
                        ", detailDescription='" + detailDescription + '\'' +
                        ", avatarImgId=" + avatarImgId +
                        ", backgroundImgId=" + backgroundImgId +
                        ", backgroundUrl='" + backgroundUrl + '\'' +
                        ", authority=" + authority +
                        ", mutual=" + mutual +
                        ", expertTags=" + expertTags +
                        ", experts=" + experts +
                        ", djStatus=" + djStatus +
                        ", vipType=" + vipType +
                        ", remarkName=" + remarkName +
                        ", backgroundImgIdStr='" + backgroundImgIdStr + '\'' +
                        ", avatarImgIdStr='" + avatarImgIdStr + '\'' +
                        ", avatarImgId_str='" + avatarImgId_str + '\'' +
                        '}';
            }
        }

        public static class SubscribersBean {
            /**
             * defaultAvatar : false
             * province : 650000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/DLnxUXqZXi6n2eOn2s3ifg==/109951163638946746.jpg
             * accountStatus : 0
             * gender : 1
             * city : 650100
             * birthday : -2209017600000
             * userId : 1659134222
             * userType : 0
             * nickname : 老猫VA
             * signature :
             * description :
             * detailDescription :
             * avatarImgId : 109951163638946750
             * backgroundImgId : 109951162868126480
             * backgroundUrl : http://p1.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 0
             * vipType : 0
             * remarkName : null
             * backgroundImgIdStr : 109951162868126486
             * avatarImgIdStr : 109951163638946746
             * avatarImgId_str : 109951163638946746
             */

            private boolean defaultAvatar;
            private String province;
            private String authStatus;
            private boolean followed;
            private String avatarUrl;
            private String accountStatus;
            private String gender;
            private String city;
            private String birthday;
            private String userId;
            private String userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private String avatarImgId;
            private String backgroundImgId;
            private String backgroundUrl;
            private String authority;
            private boolean mutual;
            private Object expertTags;
            private Object experts;
            private String djStatus;
            private String vipType;
            private Object remarkName;
            private String backgroundImgIdStr;
            private String avatarImgIdStr;
            private String avatarImgId_str;

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(String authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(String accountStatus) {
                this.accountStatus = accountStatus;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public String getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(String avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public String getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(String backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public Object getExperts() {
                return experts;
            }

            public void setExperts(Object experts) {
                this.experts = experts;
            }

            public String getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(String djStatus) {
                this.djStatus = djStatus;
            }

            public String getVipType() {
                return vipType;
            }

            public void setVipType(String vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }
        }

        @Override
        public String toString() {
            return "PlaylistsBean{" +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    ", trackNumberUpdateTime=" + trackNumberUpdateTime +
                    ", status=" + status +
                    ", userId=" + userId +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", subscribedCount=" + subscribedCount +
                    ", trackCount=" + trackCount +
                    ", cloudTrackCount=" + cloudTrackCount +
                    ", coverImgUrl='" + coverImgUrl + '\'' +
                    ", coverImgId=" + coverImgId +
                    ", description='" + description + '\'' +
                    ", playCount=" + playCount +
                    ", trackUpdateTime=" + trackUpdateTime +
                    ", specialType=" + specialType +
                    ", totalDuration=" + totalDuration +
                    ", creator=" + creator +
                    ", tracks=" + tracks +
                    ", subscribed=" + subscribed +
                    ", commentThreadId='" + commentThreadId + '\'' +
                    ", newImported=" + newImported +
                    ", adType=" + adType +
                    ", highQuality=" + highQuality +
                    ", privacy=" + privacy +
                    ", ordered=" + ordered +
                    ", anonimous=" + anonimous +
                    ", shareCount=" + shareCount +
                    ", coverImgId_str='" + coverImgId_str + '\'' +
                    ", commentCount=" + commentCount +
                    ", alg='" + alg + '\'' +
                    ", tags=" + tags +
                    ", subscribers=" + subscribers +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SongListEntity{" +
                "total=" + total +
                ", code=" + code +
                ", more=" + more +
                ", cat='" + cat + '\'' +
                ", playlists=" + playlists +
                '}';
    }
}
