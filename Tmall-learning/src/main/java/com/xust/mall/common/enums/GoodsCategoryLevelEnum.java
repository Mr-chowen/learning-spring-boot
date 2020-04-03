package com.xust.mall.common.enums;

/**
 * 分类级别
 */
public enum GoodsCategoryLevelEnum {

    DEFAULT(0,"ERROR"),
    LEVEL_ONE(1,"一级分类"),
    LEVEL_TWO(2,"二级分类"),
    LEVEL_THREE(3,"三级分类");


    private int level;

    private String name;

    GoodsCategoryLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public static GoodsCategoryLevelEnum getOrderStatusEnumByLevel(int level) {
        for (GoodsCategoryLevelEnum newBeeMallCategoryLevelEnum : GoodsCategoryLevelEnum.values()) {
            if (newBeeMallCategoryLevelEnum.getLevel() == level) {
                return newBeeMallCategoryLevelEnum;
            }
        }
        return DEFAULT;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
