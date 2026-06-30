package com.boot.security.server.model;

/**
 * EDI 210 Excel 模板列序定义（共 131 列：Region + 130 列业务字段）
 *
 * <p>三份原始 Excel（AMR / APAC / EMEIA）结构完全相同，统一加 Region 列合并为一份模板。
 * 导入和导出共用此常量。
 *
 * <p>列索引从 0 开始，对应 POI XSSFRow.getCell(index)
 */
public final class EDI210Schema {

    private EDI210Schema() {}

    // ===== 列索引（0-based）=====
    public static final int COL_REGION = 0;

    // Bill-To Information (10 列, 1~10)
    public static final int COL_BT_NAME1   = 1;
    public static final int COL_BT_NAME2   = 2;
    public static final int COL_BT_ADDR1   = 3;
    public static final int COL_BT_ADDR2   = 4;
    public static final int COL_BT_CITY    = 5;
    public static final int COL_BT_COUNTRY = 6;
    public static final int COL_BT_POSTAL  = 7;
    public static final int COL_BT_STATE   = 8;
    public static final int COL_BT_ACCOUNT = 9;
    public static final int COL_BT_LOCATION = 10;

    // Bill-From Information (5 列, 11~15)
    public static final int COL_BF_NAME    = 11;
    public static final int COL_BF_ADDRESS = 12;
    public static final int COL_BF_CITY    = 13;
    public static final int COL_BF_POSTAL  = 14;
    public static final int COL_BF_COUNTRY = 15;

    // Shipper Information (10 列, 16~25)
    public static final int COL_SH_NAME1   = 16;
    public static final int COL_SH_NAME2   = 17;
    public static final int COL_SH_ADDR1   = 18;
    public static final int COL_SH_ADDR2   = 19;
    public static final int COL_SH_CITY    = 20;
    public static final int COL_SH_COUNTRY = 21;
    public static final int COL_SH_POSTAL  = 22;
    public static final int COL_SH_STATE   = 23;
    public static final int COL_SH_ACCOUNT = 24;
    public static final int COL_SH_LOCATION = 25;

    // Consignee Information (10 列, 26~35)
    public static final int COL_CN_NAME1   = 26;
    public static final int COL_CN_NAME2   = 27;
    public static final int COL_CN_ADDR1   = 28;
    public static final int COL_CN_ADDR2   = 29;
    public static final int COL_CN_CITY    = 30;
    public static final int COL_CN_COUNTRY = 31;
    public static final int COL_CN_POSTAL  = 32;
    public static final int COL_CN_STATE   = 33;
    public static final int COL_CN_ACCOUNT = 34;
    public static final int COL_CN_LOCATION = 35;

    // Billing Basic Information (19 列, 36~54)
    public static final int COL_SCAC          = 36;
    public static final int COL_INV_NO        = 37;
    public static final int COL_INV_DATE      = 38;
    public static final int COL_PAYMENT_TERMS = 39;
    public static final int COL_CUR           = 40;
    public static final int COL_INV_AMOUNT    = 41;
    public static final int COL_TT_CHARGE     = 42;
    public static final int COL_PICKUP_DATE   = 43;
    public static final int COL_PICKUP_TIME   = 44;
    public static final int COL_POD_DATE      = 45;
    public static final int COL_POD_TIME      = 46;
    public static final int COL_POD_NAME      = 47;
    public static final int COL_GROSS_WEIGHT  = 48;
    public static final int COL_GW_QUALIFIER  = 49;
    public static final int COL_GW_UNIT       = 50;
    public static final int COL_BILL_WEIGHT   = 51;
    public static final int COL_BW_QUALIFIER  = 52;
    public static final int COL_BW_UNIT       = 53;
    public static final int COL_BOL           = 54;

    // Billing Details (8 列, 55~62)
    public static final int COL_DESCRIPTION       = 55;
    public static final int COL_RATE              = 56;
    public static final int COL_RATE_QUAL         = 57;
    public static final int COL_RATED_CURRENCY    = 58;
    public static final int COL_EXCHANGE_RATE     = 59;
    public static final int COL_EXPRESSED_CURRENCY = 60;
    public static final int COL_CHARGE            = 61;
    public static final int COL_WEIGHT            = 62;

    // Rated as Weight (3 列, 63~65)
    public static final int COL_WEIGHT_QUAL = 63;
    public static final int COL_WEIGHT_TYPE = 64;
    public static final int COL_SP_CHARGE_CODE = 65;

    // Additional Information (6 列, 66~71)
    public static final int COL_SP_CHARGE_DESC  = 66;
    public static final int COL_EQUIPMENT_CODE  = 67;
    public static final int COL_EQUIPMENT_TYPE  = 68;
    public static final int COL_TOTAL_WEIGHT    = 69;
    public static final int COL_TOTAL_WEIGHT_QUAL = 70;
    public static final int COL_SERVICE_CODE    = 71;

    // Rated as Volume (2 列, 72~73)
    public static final int COL_VOLUME       = 72;
    public static final int COL_VOLUME_QUAL  = 73;

    // Rated as Quantity (2 列, 74~75)
    public static final int COL_RATED_QUANTITY     = 74;
    public static final int COL_RATED_PACKAGE_TYPE = 75;

    // Billed as Quantity (2 列, 76~77)
    public static final int COL_BILLED_QUANTITY  = 76;
    public static final int COL_BILLED_QUALIFIER = 77;

    // Measurement (4 列, 78~81)
    public static final int COL_LENGTH           = 78;
    public static final int COL_WIDTH            = 79;
    public static final int COL_HEIGHT           = 80;
    public static final int COL_MEASUREMENT_UNIT = 81;

    // Other Information - Charge 明细级（5 列, 82~86）
    public static final int COL_DECLARED_VALUE    = 82;
    public static final int COL_COMMODITY_CLASS   = 83;
    public static final int COL_COMMODITY_CODE    = 84;
    public static final int COL_CUSTOMER_REFERENCE = 85; // 归 tax 表
    public static final int COL_CORRECTION_INDICATOR = 86;

    // Charge 明细继续（5 列, 87~91）
    public static final int COL_CARRIERS_DESCRIPTION = 87;
    public static final int COL_CARRIERS_QUANTITY    = 88;
    public static final int COL_TRANSPORT_SERVICE    = 89;
    public static final int COL_ORIGIN_ZONE          = 90;
    public static final int COL_DESTINATION_ZONE     = 91;

    // Tax/Reference 类（19 列, 92~110）
    public static final int COL_PO                       = 92;
    public static final int COL_DN                       = 93;
    public static final int COL_RMA                      = 94;
    public static final int COL_HAWB                     = 95;
    public static final int COL_SO                       = 96;
    public static final int COL_BILL_OF_LADING_NUMBER    = 97;
    public static final int COL_TRACKING_NUMBER          = 98;
    public static final int COL_CUSTOMER_VAT_REG         = 99;
    public static final int COL_CARRIER_VAT_REG          = 100;
    public static final int COL_BILLING_ACCOUNT          = 101;
    public static final int COL_DIVISION_IDENTIFIER      = 102;
    public static final int COL_BILL_TO_ACCOUNT          = 103;
    public static final int COL_INVOICE_STANDARD_CODE    = 104;
    public static final int COL_CARRIER_NAME             = 105;
    public static final int COL_BUSINESS_UNIT            = 106;
    public static final int COL_FREIGHT_BILL_NUMBER      = 107;
    public static final int COL_CARRIER_TAX_IDENTITY     = 108;
    public static final int COL_SENDER_TAX_IDENTITY      = 109;
    public static final int COL_RECIPIENT_TAX_IDENTITY   = 110;
    public static final int COL_TAX_BUREAU_NAME          = 111;
    public static final int COL_TAX_BUREAU_CODE          = 112;

    public static final int TOTAL_COLUMNS = 113;

    /**
     * 完整列头（与列索引一一对应）。
     * 与原始 3 份 Excel 表头保持一致，仅在第 0 列前置 Region。
     */
    public static final String[] HEADERS = new String[]{
            "Region",
            // Bill-To
            "Bill-To Name1", "Bill-To Name2", "Bill-To Address1", "Bill-To Address2",
            "Bill-To City", "Bill-To Country", "Bill-To Postal Code", "Bill-To State or Province",
            "Bill-To Account", "Bill-To Location",
            // Bill-From
            "Bill-From Name", "Bill-From Address", "Bill-From City", "Bill-From Postal Code", "Bill-From Country",
            // Shipper
            "Shipper Name1", "Shipper Name2", "Shipper Address1", "Shipper Address2",
            "Shipper City", "Shipper Country", "Shipper Postal Code", "Shipper State or Province",
            "Shipper Account", "Shipper Location",
            // Consignee
            "Consignee Name1", "Consignee Name2", "Consignee Address1", "Consignee Address2",
            "Consignee City", "Consignee Country", "Consignee Postal Code", "Consignee State or Province",
            "Consignee Account", "Consignee Location",
            // Billing Basic
            "SCAC", "INV#", "INV date", "Payment Terms", "CUR", "INV Amount", "TT Charge",
            "Pickup Date", "Pickup Time", "POD Date", "POD Time", "POD Name",
            "Gross Weight", "GW Weight Qualifier", "GW Weight Unit",
            "Bill Weight", "BW Weight Qualifier", "BW Weight Unit", "BOL",
            // Billing Details
            "Description", "Rate", "Rate Qual", "Rated Currency", "Exchange Rate",
            "Expressed Currency", "Charge", "Weight",
            // Rated as Weight
            "Weight Qual", "Weight Type", "SP Charge Code",
            // Additional
            "SP Charge Description", "Equipment Code", "Equipment Type",
            "Total Weight", "Total Weight Qual", "Service Code",
            // Volume
            "Volume", "Volume Qual",
            // Rated as Quantity
            "Rated Quantity", "Rated Package Type",
            // Billed as Quantity
            "Billed Quantity", "Billed Qualifier",
            // Measurement
            "Length", "Width", "Height", "Measurement Unit",
            // Other - charge 级
            "Declared Value", "Commodity Class", "Commodity Code",
            "Customer Reference", "Correction Indicator",
            "Carrier's Description", "Carrier's Quantity", "Transport Service",
            "Origin Zone", "Destination Zone",
            // Tax/Reference
            "PO", "DN", "RMA", "HAWB", "SO", "Bill of Lading Number", "Tracking Number",
            "Customer VAT Registration", "Carrier VAT Registration",
            "Billing Account", "Division Identifier", "Bill to Account",
            "Invoice Standard Code", "Carrier Name", "Business Unit", "Freight Bill Number",
            "Carrier Tax Identity", "Sender Tax Identity", "Recipient Tax Identity",
            "Tax Bureau Name", "Tax Bureau Code"
    };
}
