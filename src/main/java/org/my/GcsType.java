package org.my;

import lombok.Getter;

@Getter
public enum GcsType {
    LATITUDE(-90,90), LONGITUDE(-180,180);

    private int left;
    private int right;

    GcsType(int left, int right){
        this.left=left;
        this.right=right;
    }

}