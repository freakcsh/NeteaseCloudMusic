package com.freak.neteasecloudmusic.modules.disco.recommend.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 */

public class SongListDetailEntity {

    private String msg;
    private PlaylistBean playlist;
    private int code;
    private List<PrivilegesBean> privileges;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PlaylistBean getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistBean playlist) {
        this.playlist = playlist;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<PrivilegesBean> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegesBean> privileges) {
        this.privileges = privileges;
    }

    public static class PlaylistBean {
        private boolean subscribed;
        private CreatorBean creator;
        private String privacy;
        private String specialType;
        private String updateTime;
        private String commentThreadId;
        private boolean newImported;
        private String userId;
        private String trackUpdateTime;
        private String trackCount;
        private String coverImgId;
        private String createTime;
        private boolean highQuality;
        private boolean ordered;
        private String status;
        private String coverImgUrl;
        private String playCount;
        private String description;
        private String subscribedCount;
        private String adType;
        private String trackNumberUpdateTime;
        private String cloudTrackCount;
        private String name;
        private String id;
        private String shareCount;
        private String coverImgId_str;
        private String commentCount;
        private List<SubscribersBean> subscribers;
        private List<TracksBean> tracks;
        private List<TrackIdsBean> trackIds;
        private List<String> tags;

        public boolean isSubscribed() {
            return subscribed;
        }

        public void setSubscribed(boolean subscribed) {
            this.subscribed = subscribed;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getPrivacy() {
            return privacy;
        }

        public void setPrivacy(String privacy) {
            this.privacy = privacy;
        }

        public String getSpecialType() {
            return specialType;
        }

        public void setSpecialType(String specialType) {
            this.specialType = specialType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(String trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public String getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(String trackCount) {
            this.trackCount = trackCount;
        }

        public String getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(String coverImgId) {
            this.coverImgId = coverImgId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isHighQuality() {
            return highQuality;
        }

        public void setHighQuality(boolean highQuality) {
            this.highQuality = highQuality;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getPlayCount() {
            return playCount;
        }

        public void setPlayCount(String playCount) {
            this.playCount = playCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(String subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(String trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public String getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(String cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
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

        public List<SubscribersBean> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<SubscribersBean> subscribers) {
            this.subscribers = subscribers;
        }

        public List<TracksBean> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksBean> tracks) {
            this.tracks = tracks;
        }

        public List<TrackIdsBean> getTrackIds() {
            return trackIds;
        }

        public void setTrackIds(List<TrackIdsBean> trackIds) {
            this.trackIds = trackIds;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public static class CreatorBean {
            /**
             * defaultAvatar : false
             * province : 1000000
             * authStatus : 1
             * followed : false
             * avatarUrl : http://p1.music.126.net/RPOxoNJfdoPXdK8_WEfUqQ==/7897792022441262.jpg
             * accountStatus : 0
             * gender : 0
             * city : 1010000
             * birthday : 730742400000
             * userId : 5832286
             * userType : 4
             * nickname : 夜莺与玫瑰
             * signature : 音乐与摄影：Still life | landscape 風が吹けば，枯葉が落ちる。枯葉が落ちれば,土が肥える。土が肥えれば,果実が実る。こつこつ,ゆつくり。人生､フル__ツ
             * description :
             * detailDescription :
             * avatarImgId : 7897792022441262
             * backgroundImgId : 18509178743571388
             * backgroundUrl : http://p1.music.126.net/utIhySPsVka6HAKU_enp3Q==/18509178743571388.jpg
             * authority : 0
             * mutual : false
             * expertTags : ["日语","轻音乐","古典"]
             * experts : null
             * djStatus : 10
             * vipType : 0
             * remarkName : null
             * avatarImgIdStr : 7897792022441262
             * backgroundImgIdStr : 18509178743571388
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
            private Object experts;
            private String djStatus;
            private String vipType;
            private Object remarkName;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
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

            public List<String> getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(List<String> expertTags) {
                this.expertTags = expertTags;
            }
        }

        public static class SubscribersBean {
            /**
             * defaultAvatar : false
             * province : 410000
             * authStatus : 0
             * followed : false
             * avatarUrl : http://p1.music.126.net/8rN3d4fFSe6k6HCc4nAa8A==/109951163864618120.jpg
             * accountStatus : 0
             * gender : 2
             * city : 410100
             * birthday : -2209017600000
             * userId : 1431455762
             * userType : 0
             * nickname : 夏北啊-
             * signature :
             * description :
             * detailDescription :
             * avatarImgId : 109951163864618110
             * backgroundImgId : 109951163864661140
             * backgroundUrl : http://p1.music.126.net/RMvO1N88_gJnAnN6a7SI2A==/109951163864661132.jpg
             * authority : 0
             * mutual : false
             * expertTags : null
             * experts : null
             * djStatus : 0
             * vipType : 0
             * remarkName : null
             * avatarImgIdStr : 109951163864618120
             * backgroundImgIdStr : 109951163864661132
             * avatarImgId_str : 109951163864618120
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

        public static class TracksBean {
            /**
             * name : L'inverno:II. Largo
             * id : 28357886
             * pst : 0
             * t : 0
             * ar : [{"id":28510,"name":"Antonio Vivaldi","tns":[],"alias":[]}]
             * alia : []
             * pop : 100
             * st : 0
             * rt :
             * fee : 0
             * v : 5
             * crbt : null
             * cf :
             * al : {"id":2779145,"name":"The Four Seasons","picUrl":"http://p2.music.126.net/dygsHWQGnwnsflJ8iHQwDQ==/8890651022525585.jpg","tns":[],"pic":8890651022525585}
             * dt : 141000
             * h : {"br":320000,"fid":0,"size":5677112,"vd":78415}
             * m : {"br":192000,"fid":0,"size":3406340,"vd":81414}
             * l : {"br":128000,"fid":0,"size":2270954,"vd":81903}
             * a : null
             * cd : 1
             * no : 11
             * rtUrl : null
             * ftype : 0
             * rtUrls : []
             * djId : 0
             * copyright : 2
             * s_id : 0
             * mst : 9
             * cp : 0
             * mv : 0
             * rtype : 0
             * rurl : null
             * publishTime : 1389110400007
             */

            private String name;
            private String id;
            private String pst;
            private String t;
            private String pop;
            private String st;
            private String rt;
            private String fee;
            private String v;
            private Object crbt;
            private String cf;
            private AlBean al;
            private String dt;
            private Object a;
            private String cd;
            private String no;
            private Object rtUrl;
            private String ftype;
            private String djId;
            private String copyright;
            private String s_id;
            private String mst;
            private String cp;
            private String mv;
            private String rtype;
            private Object rurl;
            private String publishTime;
            private List<ArBean> ar;
            private List<?> alia;
            private List<?> rtUrls;

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

            public String getPst() {
                return pst;
            }

            public void setPst(String pst) {
                this.pst = pst;
            }

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getSt() {
                return st;
            }

            public void setSt(String st) {
                this.st = st;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }

            public Object getCrbt() {
                return crbt;
            }

            public void setCrbt(Object crbt) {
                this.crbt = crbt;
            }

            public String getCf() {
                return cf;
            }

            public void setCf(String cf) {
                this.cf = cf;
            }

            public AlBean getAl() {
                return al;
            }

            public void setAl(AlBean al) {
                this.al = al;
            }

            public String getDt() {
                return dt;
            }

            public void setDt(String dt) {
                this.dt = dt;
            }



            public Object getA() {
                return a;
            }

            public void setA(Object a) {
                this.a = a;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public Object getRtUrl() {
                return rtUrl;
            }

            public void setRtUrl(Object rtUrl) {
                this.rtUrl = rtUrl;
            }

            public String getFtype() {
                return ftype;
            }

            public void setFtype(String ftype) {
                this.ftype = ftype;
            }

            public String getDjId() {
                return djId;
            }

            public void setDjId(String djId) {
                this.djId = djId;
            }

            public String getCopyright() {
                return copyright;
            }

            public void setCopyright(String copyright) {
                this.copyright = copyright;
            }

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
                this.s_id = s_id;
            }

            public String getMst() {
                return mst;
            }

            public void setMst(String mst) {
                this.mst = mst;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getMv() {
                return mv;
            }

            public void setMv(String mv) {
                this.mv = mv;
            }

            public String getRtype() {
                return rtype;
            }

            public void setRtype(String rtype) {
                this.rtype = rtype;
            }

            public Object getRurl() {
                return rurl;
            }

            public void setRurl(Object rurl) {
                this.rurl = rurl;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public List<ArBean> getAr() {
                return ar;
            }

            public void setAr(List<ArBean> ar) {
                this.ar = ar;
            }

            public List<?> getAlia() {
                return alia;
            }

            public void setAlia(List<?> alia) {
                this.alia = alia;
            }

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
            }

            public static class AlBean {
                /**
                 * id : 2779145
                 * name : The Four Seasons
                 * picUrl : http://p2.music.126.net/dygsHWQGnwnsflJ8iHQwDQ==/8890651022525585.jpg
                 * tns : []
                 * pic : 8890651022525585
                 */

                private String id;
                private String name;
                private String picUrl;
                private String pic;
                private List<?> tns;

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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }
            }



            public static class ArBean {
                /**
                 * id : 28510
                 * name : Antonio Vivaldi
                 * tns : []
                 * alias : []
                 */

                private String id;
                private String name;
                private List<?> tns;
                private List<?> alias;

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

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }

        public static class TrackIdsBean {
            /**
             * id : 28357886
             * v : 5
             */

            private String id;
            private String v;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }
        }
    }

    public static class PrivilegesBean {
        /**
         * id : 28357886
         * fee : 0
         * payed : 0
         * st : 0
         * pl : 320000
         * dl : 320000
         * sp : 7
         * cp : 1
         * subp : 1
         * cs : false
         * maxbr : 999000
         * fl : 320000
         * toast : false
         * flag : 128
         * preSell : false
         */

        private String id;
        private String fee;
        private String payed;
        private String st;
        private String pl;
        private String dl;
        private String sp;
        private String cp;
        private String subp;
        private boolean cs;
        private String maxbr;
        private String fl;
        private boolean toast;
        private String flag;
        private boolean preSell;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getPayed() {
            return payed;
        }

        public void setPayed(String payed) {
            this.payed = payed;
        }

        public String getSt() {
            return st;
        }

        public void setSt(String st) {
            this.st = st;
        }

        public String getPl() {
            return pl;
        }

        public void setPl(String pl) {
            this.pl = pl;
        }

        public String getDl() {
            return dl;
        }

        public void setDl(String dl) {
            this.dl = dl;
        }

        public String getSp() {
            return sp;
        }

        public void setSp(String sp) {
            this.sp = sp;
        }

        public String getCp() {
            return cp;
        }

        public void setCp(String cp) {
            this.cp = cp;
        }

        public String getSubp() {
            return subp;
        }

        public void setSubp(String subp) {
            this.subp = subp;
        }

        public boolean isCs() {
            return cs;
        }

        public void setCs(boolean cs) {
            this.cs = cs;
        }

        public String getMaxbr() {
            return maxbr;
        }

        public void setMaxbr(String maxbr) {
            this.maxbr = maxbr;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public boolean isToast() {
            return toast;
        }

        public void setToast(boolean toast) {
            this.toast = toast;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public boolean isPreSell() {
            return preSell;
        }

        public void setPreSell(boolean preSell) {
            this.preSell = preSell;
        }
    }
}
