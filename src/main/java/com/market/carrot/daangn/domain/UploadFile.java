package com.market.carrot.daangn.domain;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class UploadFile {

//    @ManyToOne(@JoinColumn )
    private String uploadFileName;//고객이 업로드한 파일명
    private String storeFileName;//서버에 저장하는 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
