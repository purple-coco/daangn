package com.market.carrot.daangn.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class UploadFile {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

//    @OneToMany(mappedBy = "uploadFile")
//    private List<UploadFile> uploadFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String uploadFileName;//고객이 업로드한 파일명

    private String storeFileName;//서버에 저장하는 파일명

    private String url;

    private Boolean deleted = false;

    public UploadFile(String url, String uploadFileName, String storeFileName) {
        this.url = url;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
