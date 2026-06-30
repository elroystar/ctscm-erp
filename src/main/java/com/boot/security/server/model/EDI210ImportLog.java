package com.boot.security.server.model;

import java.util.Date;

/**
 * EDI 210 导入批次日志
 */
public class EDI210ImportLog {
    private Long id;
    private String batchNo;
    private String region;
    private String fileName;
    private Integer totalRows;
    private Integer successRows;
    private Integer failRows;
    private String failMessage;
    private String createUser;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public Integer getTotalRows() { return totalRows; }
    public void setTotalRows(Integer totalRows) { this.totalRows = totalRows; }
    public Integer getSuccessRows() { return successRows; }
    public void setSuccessRows(Integer successRows) { this.successRows = successRows; }
    public Integer getFailRows() { return failRows; }
    public void setFailRows(Integer failRows) { this.failRows = failRows; }
    public String getFailMessage() { return failMessage; }
    public void setFailMessage(String failMessage) { this.failMessage = failMessage; }
    public String getCreateUser() { return createUser; }
    public void setCreateUser(String createUser) { this.createUser = createUser; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
