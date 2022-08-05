/**
 * Copyright 2022 bejson.com
 */
package com.boot.security.server.dto.gps;

import java.util.List;

/**
 * Auto-generated: 2022-08-02 20:9:43
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Result {

    private FirstVcl firstVcl;
    private List<String> others;

    public void setFirstVcl(FirstVcl firstVcl) {
        this.firstVcl = firstVcl;
    }

    public FirstVcl getFirstVcl() {
        return firstVcl;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public List<String> getOthers() {
        return others;
    }

}