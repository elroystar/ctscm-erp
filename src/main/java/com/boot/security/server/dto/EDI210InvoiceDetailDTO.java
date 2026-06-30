package com.boot.security.server.dto;

import com.boot.security.server.model.EDI210Charge;
import com.boot.security.server.model.EDI210Invoice;
import com.boot.security.server.model.EDI210Party;
import com.boot.security.server.model.EDI210Tax;

import java.util.List;

/**
 * EDI 210 发票详情聚合 DTO（主表 + 4 个 Party + N 条 Charge + Tax）
 */
public class EDI210InvoiceDetailDTO {
    private EDI210Invoice invoice;
    private EDI210Party billTo;
    private EDI210Party billFrom;
    private EDI210Party shipper;
    private EDI210Party consignee;
    private List<EDI210Charge> charges;
    private EDI210Tax tax;

    public EDI210Invoice getInvoice() { return invoice; }
    public void setInvoice(EDI210Invoice invoice) { this.invoice = invoice; }
    public EDI210Party getBillTo() { return billTo; }
    public void setBillTo(EDI210Party billTo) { this.billTo = billTo; }
    public EDI210Party getBillFrom() { return billFrom; }
    public void setBillFrom(EDI210Party billFrom) { this.billFrom = billFrom; }
    public EDI210Party getShipper() { return shipper; }
    public void setShipper(EDI210Party shipper) { this.shipper = shipper; }
    public EDI210Party getConsignee() { return consignee; }
    public void setConsignee(EDI210Party consignee) { this.consignee = consignee; }
    public List<EDI210Charge> getCharges() { return charges; }
    public void setCharges(List<EDI210Charge> charges) { this.charges = charges; }
    public EDI210Tax getTax() { return tax; }
    public void setTax(EDI210Tax tax) { this.tax = tax; }
}
