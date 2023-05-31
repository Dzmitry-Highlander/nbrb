package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDTO {
    @JsonProperty("Cur_ID")
    private int curID;
    @JsonProperty("Cur_Code")
    private int curCode;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty("Cur_Name")
    private String curName;
    @JsonProperty("Cur_Name_Bel")
    private String curNameBel;
    @JsonProperty("Cur_Name_Eng")
    private String curNameEng;
    @JsonProperty("Cur_QuotName")
    private String curQuotName;
    @JsonProperty("Cur_QuotName_Bel")
    private String curQuotNameBel;
    @JsonProperty("Cur_QuotName_Eng")
    private String curQuotNameEng;
    @JsonProperty("Cur_Scale")
    private int curScale;

    public CurrencyDTO() {
    }

    public CurrencyDTO(int curID, int curCode, String curAbbreviation, String curName, String curNameBel,
                       String curNameEng, String curQuotName, String curQuotNameBel, String curQuotNameEng,
                       int curScale) {
        this.curID = curID;
        this.curCode = curCode;
        this.curAbbreviation = curAbbreviation;
        this.curName = curName;
        this.curNameBel = curNameBel;
        this.curNameEng = curNameEng;
        this.curQuotName = curQuotName;
        this.curQuotNameBel = curQuotNameBel;
        this.curQuotNameEng = curQuotNameEng;
        this.curScale = curScale;
    }

    public int getCurID() {
        return curID;
    }

    public void setCurID(int curID) {
        this.curID = curID;
    }

    public int getCurCode() {
        return curCode;
    }

    public void setCurCode(int curCode) {
        this.curCode = curCode;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getCurNameBel() {
        return curNameBel;
    }

    public void setCurNameBel(String curNameBel) {
        this.curNameBel = curNameBel;
    }

    public String getCurNameEng() {
        return curNameEng;
    }

    public void setCurNameEng(String curNameEng) {
        this.curNameEng = curNameEng;
    }

    public String getCurQuotName() {
        return curQuotName;
    }

    public void setCurQuotName(String curQuotName) {
        this.curQuotName = curQuotName;
    }

    public String getCurQuotNameBel() {
        return curQuotNameBel;
    }

    public void setCurQuotNameBel(String curQuotNameBel) {
        this.curQuotNameBel = curQuotNameBel;
    }

    public String getCurQuotNameEng() {
        return curQuotNameEng;
    }

    public void setCurQuotNameEng(String curQuotNameEng) {
        this.curQuotNameEng = curQuotNameEng;
    }

    public int getCurScale() {
        return curScale;
    }

    public void setCurScale(int curScale) {
        this.curScale = curScale;
    }
}
