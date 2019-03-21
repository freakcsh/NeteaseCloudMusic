package com.freak.neteasecloudmusic.modules.disco.recommend.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotSongListEntity {


    private String msg;
    private int code;
    private boolean more;
    private String lasttime;
    private int total;
    private List<PlaylistsBean> playlists;

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

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PlaylistsBean> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistsBean> playlists) {
        this.playlists = playlists;
    }

    public static class PlaylistsBean {


        private String name;
        private String id;
        private String trackNumberUpdateTime;
        private String status;
        private String userId;
        private String createTime;
        private long updateTime;
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
        private String copywriter;
        private String tag;
        private List<String> tags;
        private List<SubscribersBean> subscribers;

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

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
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

        public String getCopywriter() {
            return copywriter;
        }

        public void setCopywriter(String copywriter) {
            this.copywriter = copywriter;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
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

        public static class CreatorBean {
            /**
             * defaultAvatar : false
             * province : 1000000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/Jrj86gDsmjxYaWG0VuIpNQ==/109951163446003588.jpg
             * accountStatus : 0
             * gender : 0
             * city : 1010000
             * birthday : 746121600000
             * userId : 2445590
             * userType : 200
             * nickname : 下一颗巧克力
             * signature : 你从陌生走来，又走向了陌生。 ｜新浪微博:下一颗巧克力ZRY
             * description :
             * detailDescription :
             * avatarImgId : 109951163446003580
             * backgroundImgId : 109951163515806930
             * backgroundUrl : http://p1.music.126.net/sKHUTgFskCqt6VISFcBcJA==/109951163515806932.jpg
             * authority : 0
             * mutual : false
             * expertTags : ["影视原声","另类/独立","欧美"]
             * experts : {"2":"资讯(影视、欧美音乐)"}
             * djStatus : 0
             * vipType : 11
             * remarkName : null
             * avatarImgIdStr : 109951163446003588
             * backgroundImgIdStr : 109951163515806932
             * avatarImgId_str : 109951163446003588
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
            private ExpertsBean experts;
            private String djStatus;
            private String vipType;
            private Object remarkName;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private String avatarImgId_str;
            private List<String> expertTags;

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

            public ExpertsBean getExperts() {
                return experts;
            }

            public void setExperts(ExpertsBean experts) {
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

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }

            public List<String> getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(List<String> expertTags) {
                this.expertTags = expertTags;
            }

            public static class ExpertsBean {
                /**
                 * 2 : 资讯(影视、欧美音乐)
                 */

                @SerializedName("2")
                private String _$2;

                public String get_$2() {
                    return _$2;
                }

                public void set_$2(String _$2) {
                    this._$2 = _$2;
                }
            }
        }

        public static class SubscribersBean {
            /**
             * defaultAvatar : false
             * province : 440000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/EOcrCuH2jypujwlwHk07LQ==/109951163695157501.jpg
             * accountStatus : 0
             * gender : 0
             * city : 440300
             * birthday : -2209017600000
             * userId : 1686134375
             * userType : 0
             * nickname : ZhangBaiBin_
             * signature :
             * description :
             * detailDescription :
             * avatarImgId : 109951163695157500
             * backgroundImgId : 109951162868128400
             * backgroundUrl : http://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 0
             * vipType : 0
             * remarkName : null
             * avatarImgIdStr : 109951163695157501
             * backgroundImgIdStr : 109951162868128395
             * avatarImgId_str : 109951163695157501
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
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
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

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }
        }
    }
}
