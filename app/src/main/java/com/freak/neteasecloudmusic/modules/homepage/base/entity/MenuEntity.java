package com.freak.neteasecloudmusic.modules.homepage.base.entity;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MenuEntity {
    private int id;
    private String title;
    private int icon;
    private boolean isShowSmallView;
    private boolean isShowBigView;
    private boolean isShowArrows;

    public MenuEntity(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isShowSmallView() {
        return isShowSmallView;
    }

    public void setShowSmallView(boolean showSmallView) {
        isShowSmallView = showSmallView;
    }

    public boolean isShowBigView() {
        return isShowBigView;
    }

    public void setShowBigView(boolean showBigView) {
        isShowBigView = showBigView;
    }

    public boolean isShowArrows() {
        return isShowArrows;
    }

    public void setShowArrows(boolean showArrows) {
        isShowArrows = showArrows;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon=" + icon +
                ", isShowSmallView=" + isShowSmallView +
                ", isShowBigView=" + isShowBigView +
                ", isShowArrows=" + isShowArrows +
                '}';
    }
}
