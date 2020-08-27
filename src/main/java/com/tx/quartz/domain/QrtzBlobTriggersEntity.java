package com.tx.quartz.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QrtzBlobTriggersEntity {
    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private byte[] blobData;

}