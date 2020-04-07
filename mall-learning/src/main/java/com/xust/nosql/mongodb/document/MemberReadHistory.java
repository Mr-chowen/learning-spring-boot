package com.xust.nosql.mongodb.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class MemberReadHistory {
    @Id
    private String id;
    @Indexed
    private Long memberId;

    private String memberNickname;

    private String memberIcon;
    @Indexed
    private Long poductId;

    private String productName;

    private String productPic;

    private String productSubTitle;

    private Date createTime;

}
